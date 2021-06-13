package com.supportportal.exception.domain;

public class FundsTooLowException extends Exception{
    public FundsTooLowException(String message) {
        super(message);
    }
}
