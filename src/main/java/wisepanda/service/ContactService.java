package wisepanda.service;

import java.util.List;

import wisepanda.data.dto.contact.AddressDto;
import wisepanda.data.dto.contact.CompContactDto;
import wisepanda.data.dto.contact.ContactDto;
import wisepanda.data.dto.contact.CountryCodeDto;
import wisepanda.data.dto.contact.EmailDto;
import wisepanda.data.dto.contact.PhoneNumberDto;
import wisepanda.data.entities.contact.*;
import wisepanda.exceptions.WiseNoteException;

public interface ContactService {
    Contact addContact(ContactDto data) throws WiseNoteException;
    Contact findContact(ContactDto data) throws WiseNoteException;

    CompContactDto findContactDetailsById(Long id) throws WiseNoteException;

    CountryCode addCountryCode(CountryCodeDto data) throws WiseNoteException;
    List<CountryCode> getCountryCode(CountryCodeDto data) throws WiseNoteException;
    CountryCode updateCountryCode(CountryCodeDto data) throws WiseNoteException;
    void deleteCountryCode(CountryCodeDto data) throws WiseNoteException;

    Address addAddress(AddressDto data) throws WiseNoteException;
    List<Address> getAddress(AddressDto data) throws WiseNoteException;
    Address updateAddress(AddressDto data) throws WiseNoteException;
    void deleteAddress(AddressDto data) throws WiseNoteException;

    Email addEmailAddress(EmailDto data) throws WiseNoteException;
    List<Email> getEmailAddress(EmailDto data) throws WiseNoteException;
    Email updateEmailAddress(EmailDto data) throws WiseNoteException;
    void deleteEmailAddress(EmailDto data) throws WiseNoteException;
    boolean isUnique(EmailDto data) throws WiseNoteException;

    PhoneNumber addPhoneNumber(PhoneNumberDto data) throws WiseNoteException;
    PhoneNumber updatePhoneNumber(PhoneNumberDto data) throws WiseNoteException;
    List<PhoneNumber> getPhoneNumber(PhoneNumberDto data) throws WiseNoteException;
    void deletePhoneNumber(PhoneNumberDto data) throws WiseNoteException;
    boolean isUnique(PhoneNumberDto data) throws WiseNoteException;
}
