package wisepanda.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.TreeMap;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InValidDataException.class})
    protected ResponseEntity dataNotValid(InValidDataException ex, WebRequest request) {
        Map<String, String> body = ex.errors;

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity internalServerError(RuntimeException ex, WebRequest request) {
        Map<String, String> errors = new TreeMap<>();
        errors.put("ERROR_CODE" , "INTERNAL_SERVER_ERROR");
        errors.put("ERROR_MSG", ex.getMessage());
        errors.put("PATH", request.getContextPath());
        errors.put("TIMESTAMP", String.valueOf(request.getLocale()));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
