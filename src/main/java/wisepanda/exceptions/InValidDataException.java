package wisepanda.exceptions;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import wisepanda.enums.ErrorType;

public class InValidDataException extends WiseNoteException {
    @JsonFormat
    public Map<String, String> errorDetails;

    public InValidDataException(Map<String, String> errors) {
        super(ErrorType.ERROR_INPUT_INVALID);
        this.errorDetails = errors;
    }
}
