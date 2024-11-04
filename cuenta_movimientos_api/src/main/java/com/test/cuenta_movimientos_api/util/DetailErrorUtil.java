package com.test.cuenta_movimientos_api.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public final class DetailErrorUtil {

    private DetailErrorUtil(){

    }

    public static String handleErrorCause(Exception e) {
        String message = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
        String logMessage = "Cause: " + message;
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            logMessage = logMessage.concat(" Method: " + stackTrace[0].getMethodName());
            logMessage = logMessage.concat(" Class: " + stackTrace[0].getClassName());
        }
        log.error(logMessage);
        return Optional.ofNullable(message).orElse("Error of type: " + e.getClass().getName());
    }

}
