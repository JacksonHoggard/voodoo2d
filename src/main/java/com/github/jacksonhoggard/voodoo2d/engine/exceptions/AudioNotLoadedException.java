package com.github.jacksonhoggard.voodoo2d.engine.exceptions;

public class AudioNotLoadedException extends RuntimeException {
    final String fileName;

    public AudioNotLoadedException(String filename) {
        this.fileName = filename;
    }

    @Override
    public String getMessage() {
        return "Audio file " + fileName + " has not been loaded.";
    }
    
}
