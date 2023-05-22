package com.github.jacksonhoggard.voodoo2d.engine.audio;

public class AudioFileException extends Exception
{
    private final String fileName;

    public AudioFileException(String fileName)
    {
        this.fileName = fileName;
    }
    
    @Override
    public String toString()
    {
        return ("Audio file [" + fileName  + "] not loaded");
    }
}
