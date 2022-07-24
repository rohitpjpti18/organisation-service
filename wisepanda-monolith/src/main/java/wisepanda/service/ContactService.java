package wisepanda.service;

import wisepanda.data.dto.*;
import wisepanda.data.entities.contact.*;
import wisepanda.exceptions.DataNotFoundException;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.DuplicateDataException;

public interface ContactService {
    CountryCode addCountryCode(CountryCodeDto countryDto) throws InValidDataException;
    CountryCode findCountryCode(CountryCodeDto data) throws DataNotFoundException;

    Address addNewAddress(AddressDto data) throws DataNotFoundException, InValidDataException;

    Email addEmailAddress(EmailDto data) throws InValidDataException, DataNotFoundException;
    boolean isUnique(EmailDto data) throws DuplicateDataException;

    PhoneNumber addPhoneNumber(PhoneNumberDto data) throws
            DataNotFoundException,
            InValidDataException,
            DuplicateDataException;
    boolean isUnique(PhoneNumberDto data) throws DuplicateDataException;

    Contact addContact(ContactDto data) throws InValidDataException, DataNotFoundException;
    Contact findContact(ContactDto data) throws DataNotFoundException;

    CompContactDto findContactDetailsById(Long id) throws DataNotFoundException;
}
