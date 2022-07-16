package wisepanda.exceptions;

import java.util.Map;

public class DataNotValidException extends Exception {
    public Map<String, String> errors;

    public DataNotValidException(Map<String, String> errors) {
        this.errors = errors;
    }
}
