package dev.ducnguyen.identity.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED(9999, "Uncategorized Error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(9001, "You are not authenticated!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(9002, "You don't have permission!", HttpStatus.FORBIDDEN),
    CANNOT_ADD_YOURSELF(9003, "You cannot add yourself!", HttpStatus.INTERNAL_SERVER_ERROR),
    WRONG_REQUEST_STATUS(9004, "Request is not in PENDING status!", HttpStatus.BAD_REQUEST),
    ONLY_RECEIVER(9005, "Only the receiver can accept the request!", HttpStatus.BAD_REQUEST),
    DELETE_WRONG_POST(9006, "You don't have permission to delete posts made by another user.", HttpStatus.BAD_REQUEST),

    USER_EXISTED(1001, "User already exists", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1002, "Email already exists", HttpStatus.BAD_REQUEST),
    FRIEND_EXISTED(1003, "Friend request already sent", HttpStatus.BAD_REQUEST),

    PASSWORD_INVALID(2001, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(2002, "Please enter a valid email", HttpStatus.BAD_REQUEST),
    KEY_INVALID(2003, "Invalid message key", HttpStatus.BAD_REQUEST),

    FIRSTNAME_EMPTY(3001, "What's your first name", HttpStatus.BAD_REQUEST),
    SURNAME_EMPTY(3002, "What's your sur name", HttpStatus.BAD_REQUEST),
//    GENDER_EMPTY(3003, "Please choose a gender. You can change who can see this later.", HttpStatus.BAD_REQUEST),

    USER_NOT_FOUND(4004, "User could not be found!", HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(4005, "Post could not be found!", HttpStatus.NOT_FOUND),
    FRIEND_NOT_EXISTED(4006, "Friend request does not exist!", HttpStatus.NOT_FOUND),
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
