package publiclighting.cm.streetlight.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }

    public CustomException(String locationIsNull, HttpStatus httpStatus) {
    }
}
