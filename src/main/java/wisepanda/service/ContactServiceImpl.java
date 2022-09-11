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

    @Autowired
    private InputValidator validationService;
    /*
     * Country Code methods
     * @Author: rohit_prajapati
     */
    @Override
    public CountryCode addCountryCode(CountryCodeDto data) throws WiseNoteException {
        data = validationService.validate(data);

        
        
        CountryCode c = new CountryCode();
        data.fill(c);
        log.info(c);
        return generalDao.countryCode.saveAndFlush(c);
    }

    @Override
    public List<CountryCode> getCountryCode(CountryCodeDto data) throws WiseNoteException {
        CountryCode c = generalDao.countryCode.findByCountryCode(data.getCountryCode()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity Not found, Entity.name: COUNTRY_CODE, country_code: " + data.getId()));;
        List<CountryCode> result = new ArrayList<>();
        result.add(c);

        return result;
    }

    @Override
    public CountryCode updateCountryCode(CountryCodeDto data) throws WiseNoteException {
        try{
            validationService.validate(data);

            CountryCode c = generalDao.countryCode.findById(data.getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity.name: COUNTRY_CODE, Id: " + data.getId()));
            data.fill(c);
            return generalDao.countryCode.saveAndFlush(c);
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override
    public void deleteCountryCode(CountryCodeDto data) throws WiseNoteException {
        try{
            validationService.validate(data);

            generalDao.countryCode.deleteById(data.getId());
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }











    /*
     * Address methods
     * @Author: rohit_prajapati
     */
    @Override
    public Address addAddress(AddressDto data) throws WiseNoteException, InValidDataException {
        try{
            data = validationService.validate(data);

            Address a = new Address();
            a.setContact(this.findContact(data.getContact()));
            data.fill(a);
            return generalDao.address.saveAndFlush(a);
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override 
    public List<Address> getAddress(AddressDto data) throws WiseNoteException {
        Address a = generalDao.address.findById(data.getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity.name: ADDRESS, Id: " + data.getId()));
        List<Address> result = new ArrayList<>();
        result.add(a);

        return result;
    }

    @Override
    public Address updateAddress(AddressDto data) throws WiseNoteException {
        try{
            validationService.validate(data);

            Address a = generalDao.address.findById(data.getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity.name: ADDRESS, Id: " + data.getId()));
            data.fill(a);
            return generalDao.address.saveAndFlush(a);
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override
    public void deleteAddress(AddressDto data) throws WiseNoteException {
        try {
            validationService.validate(data);

            generalDao.address.deleteById(data.getId());
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }










    /*
     * Email: methods
     * @Author: rohit_prajapati
     */
    @Override
    public Email addEmailAddress(EmailDto data) throws WiseNoteException {
        try{
            data = validationService.validate(data);
            
            Email e = new Email();
            e.setContact(this.findContact(data.getContact()));
            data.fill(e);
    
            return generalDao.email.saveAndFlush(e);
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override
    public Email updateEmailAddress(EmailDto data) throws WiseNoteException {
        try{
            data = validationService.validate(data);

            final Long id = data.getId();
            Email e = generalDao.email.findById(data.getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity.name: EMAIL, Id: " + id));
            data.fill(e);
            return generalDao.email.saveAndFlush(e);
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override
    public void deleteEmailAddress(EmailDto data) throws WiseNoteException {
        try {
            validationService.validate(data);
            generalDao.email.deleteById(data.getId());
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override
    public List<Email> getEmailAddress(EmailDto data) throws WiseNoteException {
        Email e = generalDao.email.findById(data.getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity.name: EMAIL, Id: " + data.getId()));
        List<Email> result = new ArrayList<>();
        result.add(e);

        return result;
    }








    /*
     * PhoneNumber: methods
     * @Author: rohit_prajapati
     */
    @Override
    public PhoneNumber addPhoneNumber(PhoneNumberDto data) throws WiseNoteException {
        try{
            data = validationService.validate(data);

            PhoneNumber p = new PhoneNumber();
            p.setContact(findContact(data.getContact()));
            p.setCountryCode(getCountryCode(data.getCountryCode()).get(0));
            data.fill(p);

            return generalDao.phoneNumber.saveAndFlush(p);
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override
    public PhoneNumber updatePhoneNumber(PhoneNumberDto data) throws WiseNoteException {
        try{
            data = validationService.validate(data);

            final Long id = data.getId();
            PhoneNumber ph = generalDao.phoneNumber.findById(data.getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity.name: PHONE_NUMBER, Id: " + id));
            data.fill(ph);
            return generalDao.phoneNumber.saveAndFlush(ph);
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override
    public void deletePhoneNumber(PhoneNumberDto data) throws WiseNoteException {
        try {
            validationService.validate(data);
            generalDao.email.deleteById(data.getId());
        } catch (WiseNoteException e) {
            throw e;
        } catch(Exception e) {
            WiseNoteException err = new WiseNoteException(e);
            throw err;
        }
    }

    @Override
    public List<PhoneNumber> getPhoneNumber(PhoneNumberDto data) throws WiseNoteException {
        PhoneNumber ph = generalDao.phoneNumber.findById(data.getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Entity.name: PHONE_NUMBER, Id: " + data.getId()));
        List<PhoneNumber> result = new ArrayList<>();
        result.add(ph);

        return result;
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
