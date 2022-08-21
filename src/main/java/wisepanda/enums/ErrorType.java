package wisepanda.enums;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    ERROR_ENTITY_NOT_FOUND("ERR201", "Entity not found", HttpStatus.OK),
    ERROR_DUPLICATE_DATA("ERR202", "Data Already Exists", HttpStatus.OK),
    ERROR_INPUT_INVALID("ERR401", "Input Data is not valid or is empty/null.", HttpStatus.BAD_REQUEST),
    ERROR_UNEXPECTED_EXCEPTION("ERR501", "Some Unexpected exception occured", HttpStatus.INTERNAL_SERVER_ERROR);
    public String code;
    public String message;
    public HttpStatus httpStatus;

    ErrorType(String errorCode, String errorMsg, HttpStatus httpStatus){
        this.code = errorCode;
        this.message = errorMsg;
        this.httpStatus = httpStatus;
    }

    public String getLogMessage() {
        return this.code + ": " + this.message;
    }
    
    public String getLogMessage(String msg) {
        return this.code + ": " + msg;
    }
}
