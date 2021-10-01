package com.github.jacksonhoggard.voodoo2d.engine.audio;

import org.joml.Vector2f;
import org.lwjgl.openal.AL10;

public class AudioSource {

    private final int sourceId;
    private final int buffer;
    private final Vector2f position;

    public AudioSource(String filename) throws Exception {
        sourceId = AL10.alGenSources();
        buffer = AudioManager.loadSound(filename, sourceId);
        position = new Vector2f();
    }

    public void play() {
        stop();
        AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
        AL10.alSourcePlay(sourceId);
    }

    public void pause() {
        AL10.alSourcePause(sourceId);
    }

    public void continuePlaying() {
        AL10.alSourcePlay(sourceId);
    }

    public void stop() {
        AL10.alSourceStop(sourceId);
    }

    public void setRolloffFactor(int rolloffFactor) {
        AL10.alSourcef(sourceId, AL10.AL_ROLLOFF_FACTOR, rolloffFactor);
    }

    public void setReferenceDistance(int referenceDistance) {
        AL10.alSourcef(sourceId, AL10.AL_REFERENCE_DISTANCE, referenceDistance);
    }

    public void setMaxDistance(int maxDistance) {
        AL10.alSourcef(sourceId, AL10.AL_MAX_DISTANCE, maxDistance);
    }

    public void setVelocity(float x, float y, float z) {
        AL10.alSource3f(sourceId, AL10.AL_VELOCITY, x, y, z);
    }

    public void setLooping(boolean looping) {
        AL10.alSourcei(sourceId, AL10.AL_LOOPING, looping ? AL10.AL_TRUE : AL10.AL_FALSE);
    }

    public boolean isPlaying() {
        return AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }

    public void setVolume(float volume) {
        AL10.alSourcef(sourceId, AL10.AL_GAIN, volume);
    }

    public void setPitch(float pitch) {
        AL10.alSourcef(sourceId, AL10.AL_PITCH, pitch);
    }

    public void setPosition(float x, float y) {
        AL10.alSource3f(sourceId, AL10.AL_POSITION, x, y, -2f);
        this.position.x = x;
        this.position.y = y;
    }

    public void setPosition(Vector2f position) {
        setPosition(position.x, position.y);
    }

    public Vector2f getPosition() {
        return position;
    }

}
