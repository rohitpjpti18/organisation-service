package wisepanda.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import wisepanda.data.enums.ErrorCode;

@Data
public class WiseNoteException extends Exception{
    private String errorMsg;
    private ErrorCode errorCode;
    private HttpStatus statusCode;


}
