package dev.ducnguyen.notification.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED(9999, "Uncategorized Error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(9001, "You are not authenticated!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(9002, "You don't have permission!", HttpStatus.FORBIDDEN),
    CANNOT_SEND_EMAIL(9003, "Cannot send email!", HttpStatus.BAD_REQUEST),
    KEY_INVALID(2003, "Invalid message key", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
