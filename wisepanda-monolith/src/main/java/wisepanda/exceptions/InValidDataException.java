package wisepanda.exceptions;

import java.util.Map;

public class InValidDataException extends Exception {
    public Map<String, String> errors;

    public InValidDataException(Map<String, String> errors) {
        this.errors = errors;
    }
}
