package com.rasol.training001.code;

public enum ErrorCodes {
    USER_PASSWORD_IS_WRONG_ERROR(Constants.USER_PASSWORD_IS_WRONG_ERROR),
    USER_ID_IS_NOT_EXISTS_ERROR(Constants.USER_ID_IS_NOT_EXISTS_ERROR),
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
        public static final String USER_ID_IS_NOT_EXISTS_ERROR = "user id is not exists.";

    }

    public final String errorMessage;

    private ErrorCodes(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
