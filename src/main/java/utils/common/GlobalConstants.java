package utils.common;

public class GlobalConstants {

    public static enum AuthPageEnum {
        BY_PHONE,
        PASSWORD,
        BY_EMAIL,
        FORGOT_PASS,
        OTP
    }

    public static enum ErrorType {
        NOT_DISPLAY,
        INVALID,
        REQUIRED,
        MAX_LENGTH
    }
}
