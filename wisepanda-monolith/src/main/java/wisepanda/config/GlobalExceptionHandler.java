package wisepanda.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import wisepanda.data.dto.app.ErrorResponse;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> genericExceptionHandler(RuntimeException ex, WebRequest request) {
        ErrorResponse err = new ErrorResponse(request.getContextPath());
        log.error(err.errorMsg, ex);

        return handleExceptionInternal(ex, err, new HttpHeaders(), err.httpStatus, request);
    }

    @ExceptionHandler(value = {WiseNoteException.class})
    protected ResponseEntity<Object> wiseNoteExceptionHandler(WiseNoteException ex, WebRequest request) {
        log.info(request.getDescription(true));
        log.info(request.getContextPath());
        ErrorResponse err = new ErrorResponse(ex, ((ServletWebRequest)request).getRequest().getRequestURI().toString());
        log.error(err.errorMsg, ex);

        return handleExceptionInternal(ex, err, new HttpHeaders(), err.httpStatus, request);
    }

    @ExceptionHandler(value = {InValidDataException.class})
    protected ResponseEntity<Object> invalidDataExceptionHandler(InValidDataException ex, WebRequest request) {
        ErrorResponse err = new ErrorResponse(ex, request.getContextPath(), ex.errorDetails);
        log.error(err.errorMsg, ex);

        return handleExceptionInternal(ex, err, new HttpHeaders(), err.httpStatus, request);
    }
}
