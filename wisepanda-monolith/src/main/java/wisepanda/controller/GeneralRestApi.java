package wisepanda.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wisepanda.common.ApiConstants;
import wisepanda.data.dto.*;
import wisepanda.data.entities.contact.*;
import wisepanda.exceptions.DataNotFoundException;
import wisepanda.exceptions.DataNotValidException;
import wisepanda.service.ContactServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.GENERAL_REST_API_BASE)
public class GeneralRestApi {
    private static final Logger log = LogManager.getLogger(GeneralRestApi.class);

    @Autowired
    private ContactServiceImpl generalService;

    @PostMapping(value=ApiConstants.ADD_CONTACT)
    public ResponseEntity addContact(@RequestBody CompContactDto compContact) throws DataNotValidException, DataNotFoundException {
        ContactDto c = new ContactDto();
        c.setType(compContact.getType());
        Contact contact = generalService.addContact(c);
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
            saveddto.getPhoneNumbers().add(new PhoneNumberDto(generalService.addPhoneNumber(p)));
        }
        while(compContact.getAddresses() != null && !compContact.getAddresses().isEmpty()) {
            AddressDto a = compContact.getAddresses().remove(0);
            a.setContact(dto);
            saveddto.getAddresses().add(new AddressDto(generalService.addNewAddress(a)));
        }
        while(compContact.getEmails() != null && !compContact.getEmails().isEmpty()){
            EmailDto e = compContact.getEmails().remove(0);
            e.setContact(dto);
            saveddto.getEmails().add(new EmailDto(generalService.addEmailAddress(e)));
        }

        return new ResponseEntity(saveddto, HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.ADD_COUNTRY_CODE)
    public ResponseEntity addEntity(@RequestBody CountryCodeDto countryCodeDto) throws DataNotValidException, Exception {
        CountryCode c = generalService.addCountryCode(countryCodeDto);
        return new ResponseEntity(new CountryCodeDto(c), HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.ADD_PHONE_NUMBER)
    public ResponseEntity addPhoneNumber(@RequestBody PhoneNumberDto phoneNumber) throws DataNotValidException, Exception {
        PhoneNumber ph = generalService.addPhoneNumber(phoneNumber);
        return new ResponseEntity(new PhoneNumberDto(ph), HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.ADD_EMAIL)
    public ResponseEntity addEmail(@RequestBody EmailDto email) throws DataNotValidException, Exception {
        Email e = generalService.addEmailAddress(email);
        return new ResponseEntity(new EmailDto(e), HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.ADD_ADDRESS)
    public ResponseEntity addAddress(@RequestBody AddressDto address) throws DataNotValidException, Exception {
        Address a = generalService.addNewAddress(address);
        return new ResponseEntity(new AddressDto(a), HttpStatus.ACCEPTED);
    }

    @GetMapping(value=ApiConstants.GET_CONTACT_DETAILS)
    public ResponseEntity getContactDetails(@PathVariable(name="id") Long id) throws DataNotValidException, DataNotFoundException {
        CompContactDto c = generalService.getContactDetailsById(id);
        return new ResponseEntity(c, HttpStatus.OK);
    }
}
