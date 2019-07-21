package com.rasol.training001.code;

public enum ErrorCodes {
    ACCESS_DENIED(Constants.ACCESS_DENIED),
    BOOK_NOT_FOUND_ERROR(Constants.BOOK_NOT_FOUND_ERROR),
    USER_PASSWORD_IS_WRONG_ERROR(Constants.USER_PASSWORD_IS_WRONG_ERROR),
    USER_NOT_FOUND_ERROR(Constants.USER_NOT_FOUND_ERROR),
    USER_ID_ALREADY_EXISTS_ERROR(Constants.USER_ID_ALREADY_EXISTS_ERROR),
    USER_ID_MANDATORY_ERROR(Constants.USER_ID_MANDATORY_ERROR),
    USER_PASSWORD_MANDATORY_ERROR(Constants.USER_PASSWORD_MANDATORY_ERROR),
    USER_ID_MAX_LENGTH_ERROR(Constants.USER_ID_MAX_LENGTH_ERROR);

    public static final class Constants{
        public static final String USER_PASSWORD_IS_WRONG_ERROR = "user password is wrong.";
        public static final String USER_ID_MANDATORY_ERROR = "user id is mandatory.";
        public static final String USER_PASSWORD_MANDATORY_ERROR = "user password is mandatory.";
        public static final String USER_ID_MAX_LENGTH_ERROR = "max length of user id is 255.";
        public static final String USER_ID_ALREADY_EXISTS_ERROR = "user already exists.";
        public static final String USER_NOT_FOUND_ERROR = "user not found";
        public static final String BOOK_NOT_FOUND_ERROR = "book not found";
        public static final String ACCESS_DENIED = "Access Denied";



    }

    public final String errorMessage;

    private ErrorCodes(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
