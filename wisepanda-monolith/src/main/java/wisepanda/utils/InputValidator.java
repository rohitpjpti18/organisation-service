package wisepanda.utils;

import org.springframework.http.HttpStatus;
import wisepanda.data.dto.*;
import wisepanda.data.enums.ErrorCode;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;

public class InputValidator {
    public static CountryCodeDto validate(CountryCodeDto data) throws InValidDataException {
        return null;
    }

    public static PhoneNumberDto validate(PhoneNumberDto data) throws InValidDataException {
        return null;
    }

    public static EmailDto validate(EmailDto data) throws InValidDataException {
        return null;
    }

    public static AddressDto validate(AddressDto data) throws InValidDataException {
        return null;
    }

    public static ContactDto validate(ContactDto data) throws  InValidDataException {
        return null;
    }

    public static OrganisationDto validate(OrganisationDto data) throws InValidDataException {
        return null;
    }

    public static SchoolDto validate(SchoolDto data) throws WiseNoteException {
        return null;
    }

    public static QuestionDto validate(QuestionDto data) throws WiseNoteException {
        return null;
    }

    public static TopicTagDto validate(TopicTagDto data) throws WiseNoteException {
        if(data == null) {
            WiseNoteException err = new WiseNoteException();
            err.setErrorCode(ErrorCode.ERR_INPUT_INVALID);
            err.setStatusCode(HttpStatus.BAD_REQUEST);
            err.setErrorMsg("TopicTagDto is null");
            throw new WiseNoteException();
        }

        return data;
    }

    public static QuestionTagsDto validate(QuestionTagsDto data) throws InValidDataException {
        return null;
    }

    public static QuestionDetailTypeDto validate(QuestionDetailTypeDto data) throws InValidDataException {
        return null;
    }

    public static QuestionMultipleChoiceDto validate(QuestionMultipleChoiceDto data) throws InValidDataException {
        return null;
    }
}
