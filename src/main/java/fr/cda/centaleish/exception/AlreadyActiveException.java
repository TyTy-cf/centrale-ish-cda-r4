package fr.cda.centaleish.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyActiveException extends RuntimeException {

    public AlreadyActiveException(String message) {
        super(message);
    }

}
