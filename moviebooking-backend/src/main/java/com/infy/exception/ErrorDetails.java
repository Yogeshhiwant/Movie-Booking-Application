package com.infy.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    /*this is class for custom error exception
     * ResponseEntityExceptionHandler is responsible all types of exception 
     * SpringMVC.. so we can extends and override the methods and we can create our own
     * exceptions*/

    private String errormessage;
    private Integer errorCode;
    private LocalDateTime timestamp;



    public String getErrormessage() {
        return errormessage;
    }
    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }
    public Integer getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public ErrorDetails(String errormessage, Integer errorCode, LocalDateTime timestamp) {
        super();
        this.errormessage = errormessage;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }

 

    
}


