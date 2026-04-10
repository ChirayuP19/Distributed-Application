package com.chirayu.exception;

public class DuplicateProductFound extends RuntimeException{

    public DuplicateProductFound(String message){
        super(message);
    }
}
