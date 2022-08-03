package wisepanda.controller.restapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wisepanda.common.ApiConstants;
import wisepanda.data.dto.contact.AddressDto;
import wisepanda.data.dto.contact.CompContactDto;
import wisepanda.data.dto.contact.ContactDto;
import wisepanda.data.dto.contact.CountryCodeDto;
import wisepanda.data.dto.contact.EmailDto;
import wisepanda.data.dto.contact.PhoneNumberDto;
import wisepanda.data.entities.contact.*;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;
import wisepanda.service.ContactService;

import java.util.ArrayList;

@RestController
@RequestMapping(ApiConstants.REST_URL_CONTACT_ROOT)
public class ContactRestApi {
    private static final Logger log = LogManager.getLogger(ContactRestApi.class);

    @Autowired
    private ContactService contactService;

    @PostMapping(value=ApiConstants.REST_URL_CONTACT)
    public ResponseEntity<Object> addContact(@RequestBody CompContactDto compContact) throws WiseNoteException, InValidDataException {
        ContactDto c = new ContactDto();
        c.setType(compContact.getType());
        Contact contact = contactService.addContact(c);
        ContactDto dto = new ContactDto(contact);
        CompContactDto saveddto = new CompContactDto();
        saveddto.setId(contact.getId());
        saveddto.setType(contact.getType());
        saveddto.setAddresses(new ArrayList<AddressDto>());
        saveddto.setEmails(new ArrayList<>());
        saveddto.setPhoneNumbers(new ArrayList<>());

        while(compContact.getPhoneNumbers() != null && !compContact.getPhoneNumbers().isEmpty()) {
            PhoneNumberDto p = compContact.getPhoneNumbers().remove(0);
            p.setContact(dto);
            saveddto.getPhoneNumbers().add(new PhoneNumberDto(contactService.addPhoneNumber(p)));
        }
        while(compContact.getAddresses() != null && !compContact.getAddresses().isEmpty()) {
            AddressDto a = compContact.getAddresses().remove(0);
            a.setContact(dto);
            saveddto.getAddresses().add(new AddressDto(contactService.addNewAddress(a)));
        }
        while(compContact.getEmails() != null && !compContact.getEmails().isEmpty()){
            EmailDto e = compContact.getEmails().remove(0);
            e.setContact(dto);
            saveddto.getEmails().add(new EmailDto(contactService.addEmailAddress(e)));
        }

        return new ResponseEntity<>(saveddto, HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.REST_URL_COUNTRY_CODE)
    public ResponseEntity<Object> addCountry(@RequestBody CountryCodeDto countryCodeDto) throws WiseNoteException, InValidDataException {
        log.info(countryCodeDto);
        CountryCode c = contactService.addCountryCode(countryCodeDto);
        return new ResponseEntity<>(new CountryCodeDto(c), HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.REST_URL_PHONE_NUMBER)
    public ResponseEntity<Object> addPhoneNumber(@RequestBody PhoneNumberDto phoneNumber) throws WiseNoteException {
        PhoneNumber ph = contactService.addPhoneNumber(phoneNumber);
        return new ResponseEntity<>(new PhoneNumberDto(ph), HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.REST_URL_EMAIL)
    public ResponseEntity<Object> addEmail(@RequestBody EmailDto email) throws InValidDataException, Exception {
        Email e = contactService.addEmailAddress(email);
        return new ResponseEntity<>(new EmailDto(e), HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.REST_URL_ADDRESS)
    public ResponseEntity<Object> addAddress(@RequestBody AddressDto address) throws InValidDataException, Exception {
        Address a = contactService.addNewAddress(address);
        return new ResponseEntity<>(new AddressDto(a), HttpStatus.ACCEPTED);
    }

    @GetMapping(value=ApiConstants.REST_URL_CONTACT_DETAILS)
    public ResponseEntity<Object> getContactDetails(@PathVariable(name="id") Long id) throws WiseNoteException{
        CompContactDto c = contactService.findContactDetailsById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
