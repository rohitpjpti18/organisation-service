package wisepanda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.dto.contact.AddressDto;
import wisepanda.data.dto.contact.CompContactDto;
import wisepanda.data.dto.contact.ContactDto;
import wisepanda.data.dto.contact.CountryCodeDto;
import wisepanda.data.dto.contact.EmailDto;
import wisepanda.data.dto.contact.PhoneNumberDto;
import wisepanda.data.entities.contact.*;
import wisepanda.enums.ErrorType;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;
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
    public Address addNewAddress(AddressDto data) throws WiseNoteException, InValidDataException {
        data = InputValidator.validate(data);


        Address a = new Address();
        a.setContact(this.findContact(data.getContact()));
        data.fill(a);

        return generalDao.address.saveAndFlush(a);
    }

    @Override
    public Email addEmailAddress(EmailDto data) throws WiseNoteException, InValidDataException {
        data = InputValidator.validate(data);

        Email e = new Email();
        e.setContact(this.findContact(data.getContact()));
        data.fill(e);

        return generalDao.email.saveAndFlush(e);
    }

    @Override
    public PhoneNumber addPhoneNumber(PhoneNumberDto data) throws WiseNoteException, InValidDataException {
        data = InputValidator.validate(data);

        PhoneNumber p = new PhoneNumber();
        p.setContact(findContact(data.getContact()));
        p.setCountryCode(findCountryCode(data.getCountryCode()));
        data.fill(p);

        return generalDao.phoneNumber.saveAndFlush(p);
    }

    @Override
    public CountryCode findCountryCode(CountryCodeDto data) throws WiseNoteException, InValidDataException {
        Optional<CountryCode> c = generalDao.countryCode.findByCountryCode(data.getCountryCode());
        if(!c.isPresent()) throw new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND);
        return c.get();
    }

    @Override
    public Contact findContact(ContactDto data) throws WiseNoteException, InValidDataException {
        Optional<Contact> c = generalDao.contact.findById(data.getId());
        if(!c.isPresent()) throw new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND);
        return c.get();
    }

    @Override
    public Contact addContact(ContactDto data) throws WiseNoteException, InValidDataException {
        Contact c = new Contact();
        data.fill(c);
        return generalDao.contact.saveAndFlush(c);
    }

    @Override
    public CompContactDto findContactDetailsById(Long id) throws WiseNoteException, InValidDataException {
        Optional<Contact> c = generalDao.contact.findById(id);
        if(!c.isPresent()) throw new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND);

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
    public boolean isUnique(PhoneNumberDto data) throws WiseNoteException, InValidDataException {
        PhoneNumber ph = generalDao.phoneNumber.findByNumber(data.getCountryCode().getCountryCode(), data.getNumber());
        if(ph != null) throw new WiseNoteException(ErrorType.ERROR_DUPLICATE_DATA);
        return true;
    }

    @Override
    public boolean isUnique(EmailDto data) throws WiseNoteException, InValidDataException {
        Email e = generalDao.email.findByEmailId(data.getEmailAddress());
        if(e != null) throw new WiseNoteException(ErrorType.ERROR_DUPLICATE_DATA);
        return true;
    }
}
