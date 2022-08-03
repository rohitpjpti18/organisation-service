package wisepanda.service;

import wisepanda.data.dto.contact.AddressDto;
import wisepanda.data.dto.contact.CompContactDto;
import wisepanda.data.dto.contact.ContactDto;
import wisepanda.data.dto.contact.CountryCodeDto;
import wisepanda.data.dto.contact.EmailDto;
import wisepanda.data.dto.contact.PhoneNumberDto;
import wisepanda.data.entities.contact.*;
import wisepanda.exceptions.WiseNoteException;

public interface ContactService {
    CountryCode addCountryCode(CountryCodeDto countryDto) throws WiseNoteException;
    CountryCode findCountryCode(CountryCodeDto data) throws WiseNoteException;

    Address addNewAddress(AddressDto data) throws WiseNoteException;

    Email addEmailAddress(EmailDto data) throws WiseNoteException;
    boolean isUnique(EmailDto data) throws WiseNoteException;

    PhoneNumber addPhoneNumber(PhoneNumberDto data) throws WiseNoteException;
    boolean isUnique(PhoneNumberDto data) throws WiseNoteException;

    Contact addContact(ContactDto data) throws WiseNoteException;
    Contact findContact(ContactDto data) throws WiseNoteException;

    CompContactDto findContactDetailsById(Long id) throws WiseNoteException;
}
