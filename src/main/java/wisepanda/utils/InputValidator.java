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
    private GeneralDao generalDao;
    
    public CountryCodeDto validate(CountryCodeDto data) throws WiseNoteException {
        if(generalDao.countryCode.findByCountryCode(data.getCountryCode()).isPresent()) {
            final Long id = data.getId();
            throw new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity.name: COUNTRY_CODE, Id: " + id);
        }

        return data;
    }

    public PhoneNumberDto validate(PhoneNumberDto data) throws InValidDataException {
        return data;
    }

    public EmailDto validate(EmailDto data) throws InValidDataException {
        return data;
    }

    public AddressDto validate(AddressDto data) throws InValidDataException {
        return data;
    }

    public ContactDto validate(ContactDto data) throws  InValidDataException {
        return data;
    }

    public OrganisationDto validate(OrganisationDto data) throws InValidDataException {
        return data;
    }

    public SchoolDto validate(SchoolDto data) throws WiseNoteException {
        return data;
    }

    public QuestionDto validate(QuestionDto data) throws WiseNoteException {
        return data;
    }

    public TopicTagDto validate(TopicTagDto data) throws WiseNoteException {
        if(data == null) {
            WiseNoteException err = new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
            throw err;
        }

        return data;
    }

    public QuestionTagsDto validate(QuestionTagsDto data) throws WiseNoteException {
        if(data == null) {
            WiseNoteException err = new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
            throw err;
        }
        return data;
    }

    public QuestionDetailTypeDto validate(QuestionDetailTypeDto data) throws InValidDataException {
        return data;
    }

    public QuestionMultipleChoiceDto validate(QuestionMultipleChoiceDto data) throws InValidDataException {
        return data;
    }
}
