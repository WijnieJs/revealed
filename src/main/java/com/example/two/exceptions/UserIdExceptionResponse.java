package com.example.two.exceptions;



public class UserIdExceptionResponse {

    private String UserErrorFound;

    public UserIdExceptionResponse(String userExceptionErr) {
        UserErrorFound = userExceptionErr;
    }

    public String getUserErrorFound() {
        return UserErrorFound;
    }

    public void setUserErrorFound(String userErrorFound) {
        UserErrorFound = userErrorFound;
    }

//    public String getUserNotFoundException() {
//        return UserErrorFound;
//    }
//
//    public void setUserNotFoundException(String userExceptionErr) {
//        this.UserErrorFound = userExceptionErr;
//    }

    //    public UserIdExceptionResponse(String userIdException) {
//        this.userIdException = userIdException;
//    }
//
//    public String getUserIdException() {
//        return userIdException;
//    }
//
//    public void setUserIdException(String userIdException) (
//            userIdException = userIdException;
//    }

}