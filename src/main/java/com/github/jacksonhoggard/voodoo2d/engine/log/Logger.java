package com.github.jacksonhoggard.voodoo2d.engine.log;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.temporal.ChronoField.*;

public class Logger {
    public static final int TRACE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int ERR = 4;

    private final String name;
    private int priority;
    private DateTimeFormatter dateTimeFormat;
    private FileWriter logFile;

    public Logger(String name, int priority) {
        this.name = name;
        this.priority = priority;
        dateTimeFormat = new DateTimeFormatterBuilder()
                .appendLiteral('[')
                .appendLiteral("\u001B[1m")
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .appendLiteral("\u001B[0m")
                .appendLiteral(']')
                .toFormatter();
    }

    public void trace(String msg) {
        log(TRACE, msg);
    }

    public void debug(String msg) {
        log(DEBUG, msg);
    }

    public void info(String msg) {
        log(INFO, msg);
    }

    public void warn(String msg) {
        log(WARN, msg);
    }

    public void error(String msg) {
        log(ERR, msg);
    }

    public void enableOutput() {
        enableOutput("log.txt");
    }

    public void enableOutput(String path) {
        try {
            logFile = new FileWriter(path);
        } catch (IOException e) {
            log(ERR, e.getMessage());
        }
    }

    private void log(int msgPriority, String msg) {
        if(priority <= msgPriority) {
            String time = java.time.LocalTime.now().format(dateTimeFormat);
            String pStr = priorityToString(msgPriority);
            String logStr = "\u001B[0m" + time + pStr + "(\u001B[1m" + name + "\u001B[0m): " + getColor(msgPriority) + msg + "\u001B[0m";
            System.out.println(logStr);
            if(logFile != null) {
                try {
                    logFile.write(logStr + "\n");
                } catch (IOException e) {
                    logFile = null;
                    log(ERR, e.getMessage());
                }
            }
        }
    }

    private String priorityToString(int priority) {
        switch(priority) {
            case 0: return "[\u001B[1mTRACE\u001B[0m]";
            case 1: return "[\u001B[1mDEBUG\u001B[0m]";
            case 2: return "[\u001B[1m\u001B[32mINFO\u001B[0m]";
            case 3: return "[\u001B[1m\u001B[33mWARN\u001B[0m]";
            case 4: return "[\u001B[1m\u001B[31mERR\u001B[0m]";
            default: return "[PRIORITY] ";
        }
    }

    private String getColor(int priority) {
        switch(priority) {
            case 3: return "\u001B[33m";
            case 4: return "\u001B[31m";
            default: return "\u001B[0m";
        }
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDateTimeFormat(DateTimeFormatter dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public void cleanup() {
        if(logFile != null) {
            try {
                logFile.close();
            } catch (IOException e) {
                logFile = null;
                log(ERR, e.getMessage());
            }
        }
    }
}
