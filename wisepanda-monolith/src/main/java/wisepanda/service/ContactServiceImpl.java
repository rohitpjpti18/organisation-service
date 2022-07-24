package wisepanda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisepanda.common.EntityConstants;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.dto.*;
import wisepanda.data.entities.contact.*;
import wisepanda.exceptions.DataNotFoundException;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.DuplicateDataException;
import wisepanda.utils.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{
    private static final Logger log = LogManager.getLogger(ContactServiceImpl.class);

    @Autowired
    private GeneralDao generalDao;

    @Override
    public CountryCode addCountryCode(CountryCodeDto data) throws InValidDataException {
        data = InputValidator.validate(data);

        Optional<CountryCode> o = generalDao.countryCode.findByCountryCode(data.getCountryCode());
        if(o.isPresent()) {
            return o.get();
        }else{
            CountryCode c = new CountryCode();
            data.fill(c);
            log.info(c);
            return generalDao.countryCode.saveAndFlush(c);
        }
    }

    @Override
    public Address addNewAddress(AddressDto data) throws DataNotFoundException, InValidDataException {
        data = InputValidator.validate(data);


        Address a = new Address();
        a.setContact(this.findContact(data.getContact()));
        data.fill(a);

        return generalDao.address.saveAndFlush(a);
    }

    @Override
    public Email addEmailAddress(EmailDto data) throws InValidDataException, DataNotFoundException {
        data = InputValidator.validate(data);

        Email e = new Email();
        e.setContact(this.findContact(data.getContact()));
        data.fill(e);

        return generalDao.email.saveAndFlush(e);
    }

    @Override
    public PhoneNumber addPhoneNumber(PhoneNumberDto data) throws
            DataNotFoundException,
            InValidDataException {
        data = InputValidator.validate(data);

        PhoneNumber p = new PhoneNumber();
        p.setContact(findContact(data.getContact()));
        p.setCountryCode(findCountryCode(data.getCountryCode()));
        data.fill(p);

        return generalDao.phoneNumber.saveAndFlush(p);
    }

    @Override
    public CountryCode findCountryCode(CountryCodeDto data) throws DataNotFoundException {
        Optional<CountryCode> c = generalDao.countryCode.findByCountryCode(data.getCountryCode());
        if(!c.isPresent()) throw new DataNotFoundException(EntityConstants.COUNTRY_CODE_ENTITY);
        return c.get();
    }

    @Override
    public Contact findContact(ContactDto data) throws DataNotFoundException {
        Optional<Contact> c = generalDao.contact.findById(data.getId());
        if(!c.isPresent()) throw new DataNotFoundException(EntityConstants.CONTACT_ENTITY);
        return c.get();
    }

    @Override
    public Contact addContact(ContactDto data) throws InValidDataException, DataNotFoundException {
        Contact c = new Contact();
        data.fill(c);
        return generalDao.contact.saveAndFlush(c);
    }

    @Override
    public CompContactDto findContactDetailsById(Long id) throws DataNotFoundException{
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

    @Override
    public boolean isUnique(PhoneNumberDto data) throws DuplicateDataException{
        PhoneNumber ph = generalDao.phoneNumber.findByNumber(data.getCountryCode().getCountryCode(), data.getNumber());
        if(ph != null) throw new DuplicateDataException(EntityConstants.PHONE_NUMBER_ENTITY);
        return true;
    }

    @Override
    public boolean isUnique(EmailDto data) throws DuplicateDataException {
        Email e = generalDao.email.findByEmailId(data.getEmailAddress());
        if(e != null) throw new DuplicateDataException(EntityConstants.EMAIL_ENTITY);
        return true;
    }
}
