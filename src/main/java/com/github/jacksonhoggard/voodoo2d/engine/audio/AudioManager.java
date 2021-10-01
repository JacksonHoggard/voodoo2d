package com.github.jacksonhoggard.voodoo2d.engine.audio;

import org.joml.Vector2f;
import org.lwjgl.openal.*;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.libc.LibCStdlib.*;

public class AudioManager {

    private static ArrayList<Integer> sources;
    private static ArrayList<Integer> buffers;

    private static String defaultDeviceName;
    private static long device;
    private static int[] attributes;
    private static long context;

    private static ALCCapabilities alcCapabilities;
    private static ALCapabilities alCapabilities;

    public static void init() {
        sources = new ArrayList<Integer>();
        buffers = new ArrayList<Integer>();
        defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        device = alcOpenDevice(defaultDeviceName);
        attributes = new int[]{0};
        context = alcCreateContext(device, attributes);
        alcMakeContextCurrent(context);

        alcCapabilities = ALC.createCapabilities(device);
        alCapabilities = AL.createCapabilities(alcCapabilities);
    }

    public static void setListenerData(float x, float y) {
        AL10.alListener3f(AL_POSITION, x, y, 0);
        AL10.alListener3f(AL_VELOCITY, 0, 0, 0);
    }

    public static void setListenerData(Vector2f position) {
        setListenerData(position.x, position.y);
    }

    protected static int loadSound(String filename, int sourcePointer) throws Exception {
        ShortBuffer rawAudioBuffer;

        int channels;
        int sampleRate;

        try (MemoryStack stack = stackPush()) {
            //Allocate space to store return information from the function
            IntBuffer channelsBuffer = stack.mallocInt(1);
            IntBuffer sampleRateBuffer = stack.mallocInt(1);

            rawAudioBuffer = stb_vorbis_decode_filename(filename, channelsBuffer, sampleRateBuffer);
            if (rawAudioBuffer == null) {
                throw new Exception("Audio file [" + filename  + "] not loaded");
            }

            //Retrieve the extra information that was stored in the buffers by the function
            channels = channelsBuffer.get(0);
            sampleRate = sampleRateBuffer.get(0);
        }

        //Find the correct OpenAL format
        int format = -1;
        if (channels == 1) {
            format = AL_FORMAT_MONO16;
        } else if (channels == 2) {
            format = AL_FORMAT_STEREO16;
        }

        //Request space for the buffer
        int bufferPointer = alGenBuffers();

        //Send the data to OpenAL
        alBufferData(bufferPointer, format, rawAudioBuffer, sampleRate);

        //Free the memory allocated by STB
        free(rawAudioBuffer);

        //Assign the sound we just loaded to the source
        alSourcei(sourcePointer, AL_BUFFER, bufferPointer);

        buffers.add(bufferPointer);
        sources.add(sourcePointer);

        return bufferPointer;
    }

    public static void cleanup() {
        //Terminate OpenAL
        for(int s : sources)
            alDeleteSources(s);
        for(int b : buffers)
            alDeleteBuffers(b);
        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}
