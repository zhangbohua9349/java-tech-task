package com.rezdy.lunch.model;

public class ErrorResponse
{

    private String errorMessage;

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public ErrorResponse setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
        return this;
    }
}
