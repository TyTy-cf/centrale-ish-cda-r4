package fr.cda.centaleish.advisor;

import fr.cda.centaleish.exception.AlreadyActiveException;
import fr.cda.centaleish.exception.ExpiredCodeException;
import fr.cda.centaleish.response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuntimeExceptionHandler {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public CustomResponse handler(RuntimeException e) {
        if (e instanceof AlreadyActiveException || e instanceof ExpiredCodeException) {
            return new CustomResponse(HttpStatus.BAD_GATEWAY.value(), e.getMessage());
        }
        return new CustomResponse();
    }

}
