package fr.cda.centaleish.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExpiredCodeException extends RuntimeException {

    public ExpiredCodeException(String message) {
        super(message);
    }

}
