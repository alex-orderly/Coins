package me.alexjs.coins.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CoinsException.class)
    public ResponseEntity handleException(HttpServletRequest request, CoinsException e) {
        ExceptionResponseBody responseBody
                = new ExceptionResponseBody(e.getStatus(), e.getMessage(), (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
        return ResponseEntity.status(e.getStatus()).body(responseBody);
    }

    private static class ExceptionResponseBody {

        private final ZonedDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;

        public ExceptionResponseBody(HttpStatus status, String message, String path) {
            this.timestamp = ZonedDateTime.now();
            this.status = status.value();
            this.error = status.getReasonPhrase();
            this.message = message;
            this.path = path;
        }

        public ZonedDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

    }

}
