package com.mhkim.board.advice.exception;

public class CDataNotFoundException extends RuntimeException {

    public CDataNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CDataNotFoundException(String msg) {
        super(msg);
    }

    public CDataNotFoundException() {
        super();
    }

}
