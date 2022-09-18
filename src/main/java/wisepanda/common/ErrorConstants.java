package wisepanda.common;

public class ErrorConstants {
    public static final String INVALID_COUNTRY_CODE = "INVALID_COUNTRY_CODE";
    public static final String INVALID_COUNTRY_CODE_MSG = "Country code should start with \"+\"";

    public static final String INVALID_EMAIL = "INVALID_COUNTRY_CODE";
    public static final String INVALID_EMAIL_MSG = "Email Address is not valid";
    public static final String EMPTY_ORG_NAME = "EMPTY_NAME";
    public static final String EMPTY_ORG_NAME_MSG = "Name empty";
    public static final String EMPTY_CONTACT = "EMPTY_CONTACT";
    public static final String EMPTY_CONTACT_MSG = "contact information not provided";
    public static final String CONTACT_NOT_FOUND = "CONTACT_NOT_FOUND";
    public static final String CONTACT_NOT_FOUND_MSG = "Contact not found";

    // Entity Not found Errors
    public static final String DATA_NOT_FOUND                   = "DATA_NOT_FOUND";

    public static final String NOT_FOUND_CONTACT                = "ENTITY_NAME: Contact, ENTITY_ID: ";
    public static final String NOT_FOUND_COUNTRY_CODE           = "ENTITY_NAME: CountryCode, ENTITY_ID: ";
    public static final String NOT_FOUND_EMAIL                  = "ENTITY_NAME: Email, ENTITY_ID: ";
    public static final String NOT_FOUND_PHONE_NUMBER           = "ENTITY_NAME: PhoneNumber, ENTITY_ID: ";
    public static final String NOT_FOUND_ADDRESS                = "ENTITY_NAME: Address, ENTITY_ID: ";

    public static final String NOT_FOUND_QUESTION               = "ENTITY_NAME: Question, ENTITY_ID: ";
    public static final String NOT_FOUND_QUES_DETAIL            = "ENTITY_NAME: QuestionDetailType, ENTITY_ID: ";
    public static final String NOT_FOUND_QUES_MULTIPLE_CHOICE   = "ENTITY_NAME: QuestionMultipleChoice, ENTITY_ID: ";
    public static final String NOT_FOUND_QUES_TAGS              = "ENTITY_NAME: QuestionTags, ENTITY_ID: ";
    public static final String NOT_FOUND_TOPIC_TAG              = "ENTITY_NAME: TopicTag, ENTITY_ID: ";

    public static final String NOT_FOUND_APPROVAL               = "ENTITY_NAME: Approval, ENTITY_ID: ";
    public static final String NOT_FOUND_ORGANISATION           = "ENTITY_NAME: Organisation, ENTITY_ID: ";
    public static final String NOT_FOUND_SCHOOL                 = "ENTITY_NAME: School, ENTITY_ID: ";
    public static final String DATA_PROVIDED                    = "DATA_PROVIDED: ";


    public static final String GROUP_ERROR_TYPE = "ERROR_TYPE";
    public static final String KEY_MSG_ERR201   = "MSG_ERR201";
    public static final String KEY_MSG_ERR202   = "MSG_ERR202";
    public static final String KEY_MSG_ERR401   = "MSG_ERR401";
    public static final String KEY_MSG_ERR501   = "MSG_ERR501";


    public static String emptyValueMsg(String value) {
        return value + "cannot be empty/null.";
    }
}
