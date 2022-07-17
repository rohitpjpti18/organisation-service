package wisepanda.exceptions;

public class DataNotFoundException extends Exception{
    private String dataCode;
    public DataNotFoundException(String dataCode) {
        super("Data Not found");
        this.dataCode = dataCode;
    }
}
