package wisepanda.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wisepanda.data.dao.GeneralDao;
import wisepanda.data.dto.*;
import wisepanda.data.dto.contact.AddressDto;
import wisepanda.data.dto.contact.ContactDto;
import wisepanda.data.dto.contact.CountryCodeDto;
import wisepanda.data.dto.contact.EmailDto;
import wisepanda.data.dto.contact.PhoneNumberDto;
import wisepanda.data.dto.question.QuestionDetailTypeDto;
import wisepanda.data.dto.question.QuestionDto;
import wisepanda.data.dto.question.QuestionMultipleChoiceDto;
import wisepanda.data.dto.question.QuestionTagsDto;
import wisepanda.data.dto.question.TopicTagDto;
import wisepanda.enums.ErrorType;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;

@Component
public class InputValidator {
    @Autowired
    private static GeneralDao generalDao;
    
    public static CountryCodeDto validate(CountryCodeDto data) throws InValidDataException {
        return data;
    }

    public static PhoneNumberDto validate(PhoneNumberDto data) throws InValidDataException {
        return data;
    }

    public static EmailDto validate(EmailDto data) throws InValidDataException {
        return data;
    }

    public static AddressDto validate(AddressDto data) throws InValidDataException {
        return data;
    }

    public static ContactDto validate(ContactDto data) throws  InValidDataException {
        return data;
    }

    public static OrganisationDto validate(OrganisationDto data) throws InValidDataException {
        return data;
    }

    public static SchoolDto validate(SchoolDto data) throws WiseNoteException {
        return data;
    }

    public static QuestionDto validate(QuestionDto data) throws WiseNoteException {
        return data;
    }

    public static TopicTagDto validate(TopicTagDto data) throws WiseNoteException {
        if(data == null) {
            WiseNoteException err = new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
            throw err;
        }

        return data;
    }

    public static QuestionTagsDto validate(QuestionTagsDto data) throws WiseNoteException {
        if(data == null) {
            WiseNoteException err = new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
            throw err;
        }
        return data;
    }

    public static QuestionDetailTypeDto validate(QuestionDetailTypeDto data) throws InValidDataException {
        return data;
    }

    public static QuestionMultipleChoiceDto validate(QuestionMultipleChoiceDto data) throws InValidDataException {
        return data;
    }
}
