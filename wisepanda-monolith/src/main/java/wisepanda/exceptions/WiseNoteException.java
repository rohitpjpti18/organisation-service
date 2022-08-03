package wisepanda.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import wisepanda.enums.ErrorType;

public class WiseNoteException extends Exception{
    @JsonProperty("error-code")
    public String errorCode;

    @JsonProperty("error-message")
    public String errorMsg;

    @JsonProperty("error-details")
    public Map<String, String> errorDetails;

    @JsonProperty("response-status")
    public HttpStatus httpStatus;

    public WiseNoteException(ErrorType error) {
        super(error.getLogMessage());
        this.errorCode = error.code;
        this.errorMsg = error.message;
        this.httpStatus = error.httpStatus;
        this.errorDetails = null;
    }

    public WiseNoteException(ErrorType error, String msg) {
        super(error.getLogMessage(msg));
        this.errorCode = error.code;
        this.errorMsg = msg;
        this.httpStatus = error.httpStatus;
        this.errorDetails = null;
    }

    public WiseNoteException(ErrorType error, String msg, Map<String, String> errorDetails) {
        super(error.getLogMessage(msg));
        this.errorCode = error.code;
        this.errorMsg = msg;
        this.httpStatus = error.httpStatus;
        this.errorDetails = errorDetails;
    }
}
