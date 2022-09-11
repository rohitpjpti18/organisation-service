package wisepanda.enums;

import org.springframework.http.HttpStatus;

import wisepanda.common.Constants;
import wisepanda.common.ErrorConstants;
import wisepanda.service.AppConfigService;



public enum ErrorType {
    
    ERROR_ENTITY_NOT_FOUND("ERR201", AppConfigService.get(ErrorConstants.GROUP_ERROR_TYPE, ErrorConstants.KEY_MSG_ERR201), HttpStatus.OK),
    ERROR_DUPLICATE_DATA("ERR202", AppConfigService.get(ErrorConstants.GROUP_ERROR_TYPE, ErrorConstants.KEY_MSG_ERR202), HttpStatus.OK),
    ERROR_INPUT_INVALID("ERR401", AppConfigService.get(ErrorConstants.GROUP_ERROR_TYPE, ErrorConstants.KEY_MSG_ERR401), HttpStatus.BAD_REQUEST),
    ERROR_UNEXPECTED_EXCEPTION("ERR501", AppConfigService.get(ErrorConstants.GROUP_ERROR_TYPE, ErrorConstants.KEY_MSG_ERR501), HttpStatus.INTERNAL_SERVER_ERROR);
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
