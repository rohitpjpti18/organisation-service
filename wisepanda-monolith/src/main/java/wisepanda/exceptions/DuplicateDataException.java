package wisepanda.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateDataException extends Exception{
    private String dataCode;
    public DuplicateDataException(String dataCode) {
        super("Data Not found");
        this.dataCode = dataCode;
    }
}
