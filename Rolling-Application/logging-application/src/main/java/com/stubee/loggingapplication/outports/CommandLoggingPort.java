package com.stubee.loggingapplication.outports;

public interface CommandLoggingPort<T> {

    T save(T logging);

}