package wisepanda.data.dto.app;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import wisepanda.enums.ErrorType;
import wisepanda.exceptions.WiseNoteException;

public class ErrorResponse {
    @JsonProperty("error-code")
    public String errorCode;
    
    @JsonProperty("error-message")
    public String errorMsg;

    @JsonProperty("http-status")
    public HttpStatus httpStatus;

    @JsonProperty("timestamp")
    public LocalDateTime timestamp;

    @JsonProperty("path")
    public String path;

    @JsonProperty("error-details")
    public Map<String, String> errorDetails;

    public ErrorResponse(String path) {
        this.errorCode  = ErrorType.ERROR_UNEXPECTED_EXCEPTION.code;
        this.errorMsg   = ErrorType.ERROR_UNEXPECTED_EXCEPTION.message;
        this.httpStatus = ErrorType.ERROR_UNEXPECTED_EXCEPTION.httpStatus; 
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.errorDetails = null;
    }

    public ErrorResponse(ErrorType err, String path) {
        this.errorCode = err.code;
        this.errorMsg = err.message;
        this.httpStatus = err.httpStatus;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.errorDetails = null;
    }

    public ErrorResponse(ErrorType err, String path, Map<String, String> errorDetails) {
        this.errorCode = err.code;
        this.errorMsg = err.message;
        this.httpStatus = err.httpStatus;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.errorDetails = errorDetails;
    }

    public ErrorResponse(WiseNoteException err, String path) {
        this.errorCode = err.errorCode;
        this.errorMsg = err.errorMsg;
        this.httpStatus = err.httpStatus;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.errorDetails = null;
    }

    public ErrorResponse(WiseNoteException err, String path, Map<String, String> errorDetails) {
        this.errorCode = err.errorCode;
        this.errorMsg = err.errorMsg;
        this.httpStatus = err.httpStatus;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.errorDetails = errorDetails;
    }
}
