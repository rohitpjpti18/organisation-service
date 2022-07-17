package wisepanda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisepanda.common.EntityConstants;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.dto.*;
import wisepanda.data.entities.*;
import wisepanda.data.entities.contact.*;
import wisepanda.data.entities.question.*;
import wisepanda.data.validators.ContactValidators;
import wisepanda.exceptions.DataNotFoundException;
import wisepanda.exceptions.DataNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactServiceImpl {
    private static final Logger log = LogManager.getLogger(ContactServiceImpl.class);

    @Autowired
    private GeneralDao generalDao;


    public CountryCode addCountryCode(CountryCodeDto countryDto) throws DataNotValidException, DataNotFoundException {

        if(!ContactValidators.isValidCountryCode(countryDto)){
            throw new DataNotValidException(ContactValidators.validateCountryCode(countryDto));
        }

        Optional<CountryCode> o = generalDao.countryCode.findByCountryCode(countryDto.getCountryCode());

        if(o.isPresent()) {
            return o.get();
        }else{
            CountryCode c = new CountryCode();
            countryDto.fill(c);
            log.info(c);
            return generalDao.countryCode.saveAndFlush(c);
        }
    }

    public Address addNewAddress(AddressDto data) throws DataNotFoundException, DataNotValidException {
        Address a = new Address();
        a.setContact(this.findContact(data.getContact()));
        data.fill(a);

        Map<String, String> errors = ContactValidators.validateAddress(data);
        if(!errors.isEmpty()) throw new DataNotValidException(errors);
        return generalDao.address.saveAndFlush(a);
    }

    public Email addEmailAddress(EmailDto data) throws DataNotValidException, DataNotFoundException {
        Email e = new Email();
        e.setContact(this.findContact(data.getContact()));
        data.fill(e);

        Map<String, String> errors = ContactValidators.validateEmailAddress(data);
        if(!errors.isEmpty()) throw new DataNotValidException(errors);
        return generalDao.email.saveAndFlush(e);
    }

    public PhoneNumber addPhoneNumber(PhoneNumberDto data) throws DataNotFoundException, DataNotValidException {
        PhoneNumber p = new PhoneNumber();
        p.setContact(findContact(data.getContact()));
        p.setCountryCode(findCountryCode(data.getCountryCode()));
        data.fill(p);

        Map<String, String> errors = ContactValidators.validatePhoneNumber(data);
        if(!errors.isEmpty()) throw new DataNotValidException(errors);
        return generalDao.phoneNumber.saveAndFlush(p);
    }

    public CountryCode findCountryCode(CountryCodeDto data) throws DataNotFoundException {
        Optional<CountryCode> c = generalDao.countryCode.findByCountryCode(data.getCountryCode());
        if(!c.isPresent()) throw new DataNotFoundException(EntityConstants.COUNTRY_CODE_ENTITY);
        return c.get();
    }

    public Contact findContact(ContactDto data) throws DataNotFoundException {
        Optional<Contact> c = generalDao.contact.findById(data.getId());
        if(!c.isPresent()) throw new DataNotFoundException(EntityConstants.CONTACT_ENTITY);
        return c.get();
    }

    public Contact addContact(ContactDto data) throws DataNotValidException, DataNotFoundException {
        Contact c = new Contact();
        data.fill(c);
        return generalDao.contact.saveAndFlush(c);
    }

    public Organisation addOrganisation(OrganisationDto data) throws Exception {
        Organisation o = new Organisation();
        data.fill(o);
        return generalDao.organisation.saveAndFlush(o);
    }

    public School addSchool(SchoolDto data) throws Exception {
        School s = new School();
        data.fill(s);
        return generalDao.school.saveAndFlush(s);
    }

    public Question addQuestion(QuestionDto data) throws Exception {
        Question q = new Question();
        data.fill(q);
        return generalDao.question.saveAndFlush(q);
    }

    public TopicTag addTopicTag(TopicTagDto data) throws Exception {
        TopicTag t = new TopicTag();
        data.fill(t);
        return generalDao.topicTag.saveAndFlush(t);
    }

    public QuestionTags addQuestionTags(QuestionTagsDto data) throws Exception {
        QuestionTags q = new QuestionTags();
        data.fill(q);
        return generalDao.questionTags.saveAndFlush(q);
    }

    public QuestionTypeDetailType addQuestionTypeDetailType(QuestionTypeDetailTypeDto data) throws Exception {
        QuestionDto q = data.getQuestion();
        Question qEntity;
        if(q.getId() != null && q.getId() != 0) {
            Optional<Question> o = generalDao.question.findById(q.getId());

            if(o.isPresent()) qEntity = o.get();
            else qEntity = new Question();
        } else {
            qEntity = addQuestion(q);
        }

        QuestionTypeDetailType qm = new QuestionTypeDetailType();
        data.fill(qm);

        return generalDao.questionTypeDetailType.saveAndFlush(qm);
    }

    public QuestionTypeMultipleChoice addQuestionTypeMultipleChoice(QuestionTypeMultipleChoiceDto data) throws Exception {
        QuestionDto q = data.getQuestion();
        Question qEntity;
        if(q.getId() != null && q.getId() != 0) {
            Optional<Question> o = generalDao.question.findById(q.getId());

            if(o.isPresent()) qEntity = o.get();
            else qEntity = new Question();
        } else {
            qEntity = addQuestion(q);
        }

        QuestionTypeMultipleChoice qm = new QuestionTypeMultipleChoice();
        data.fill(qm);
        qm.setQuestion(qEntity);

        return generalDao.questionTypeMultipleChoice.saveAndFlush(qm);
    }

    public CompContactDto getContactDetailsById(Long id) throws DataNotFoundException{
        Optional<Contact> c = generalDao.contact.findById(id);
        if(!c.isPresent()) throw new DataNotFoundException(EntityConstants.CONTACT_ENTITY);

        CompContactDto result = new CompContactDto();
        result.setType(c.get().getType());
        result.setId(c.get().getId());

        List<PhoneNumber> ph = generalDao.phoneNumber.findByContactId(id);
        if(ph == null) result.setPhoneNumbers(new ArrayList<>());
        else result.setPhoneNumbers(eToDtoPh(ph));

        List<Email> e = generalDao.email.findByContactId(id);
        if(e == null) result.setEmails(new ArrayList<>());
        else result.setEmails(eToDtoE(e));

        List<Address> a = generalDao.address.findByContactId(id);
        if(a == null) result.setAddresses(new ArrayList<>());
        else result.setAddresses(eToDtoA(a));

        return result;
    }

    private List<PhoneNumberDto> eToDtoPh(List<PhoneNumber> phoneNumbers) {
        List<PhoneNumberDto> result = new ArrayList<>();
        for(PhoneNumber p: phoneNumbers) {
            result.add(new PhoneNumberDto(p));
        }

        return result;
    }

    private List<AddressDto> eToDtoA(List<Address> addresses) {
        List<AddressDto> result = new ArrayList<>();
        for(Address a: addresses) {
            result.add(new AddressDto(a));
        }

        return result;
    }

    private List<EmailDto> eToDtoE(List<Email> emails) {
        List<EmailDto> result = new ArrayList<>();
        for(Email e: emails) {
            result.add(new EmailDto(e));
        }

        return result;
    }
}
