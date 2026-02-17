package com.ai.friday.exceptions;

public class LoginFailedException extends Exception{
    @Override
    public String getMessage() {
        return "Login Failed";
    }
}
