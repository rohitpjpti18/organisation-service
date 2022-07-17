package wisepanda.data.validators;

import wisepanda.common.ErrorConstants;
import wisepanda.data.dto.AddressDto;
import wisepanda.data.dto.CountryCodeDto;
import wisepanda.data.dto.EmailDto;
import wisepanda.data.dto.PhoneNumberDto;
import wisepanda.data.entities.contact.CountryCode;

import java.util.HashMap;
import java.util.Map;

public class ContactValidators {

    public static boolean isValidCountryCode(CountryCode c) {
        if(!validateCountryCode(new CountryCodeDto(c)).isEmpty()) return false;
        return true;
    }

    public static boolean isValidCountryCode(CountryCodeDto c) {
        if(c == null || !validateCountryCode(c).isEmpty()) return false;
        return true;
    }

    public boolean isValidPhoneNumber(PhoneNumberDto ph) {
        if(ph == null || !validatePhoneNumber(ph).isEmpty()) return false;
        return true;
    }

    public static Map<String, String> validateCountryCode(CountryCodeDto c) {
        Map<String, String> errors = new HashMap<>();

        if(!c.getCountryCode().startsWith("+")){
            errors.put(ErrorConstants.INVALID_COUNTRY_CODE, ErrorConstants.INVALID_COUNTRY_CODE_MSG);
        }

        return errors;
    }

    public static Map<String, String> validateEmailAddress(EmailDto data) {
        Map<String, String> errors = new HashMap<>();

        if(!data.getEmailAddress().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")){
            errors.put(ErrorConstants.INVALID_EMAIL, ErrorConstants.INVALID_EMAIL_MSG);
        }

        return errors;
    }

    public static Map<String, String> validateAddress(AddressDto data) {
        Map<String, String> errors = new HashMap<>();

        if(false){
            errors.put(ErrorConstants.INVALID_EMAIL, ErrorConstants.INVALID_EMAIL_MSG);
        }

        return errors;
    }

    public static Map<String, String> validatePhoneNumber(PhoneNumberDto ph) {
        Map<String, String> mpp = new HashMap<>();
        if(ph.getNumber().matches("%[a-zA-z]%")) mpp.put("ALPHABET_CHARS_FOUND", "Character contains alphabets");
        if(ph.getCountryCode() == null || !isValidCountryCode(ph.getCountryCode())) mpp.put("COUNTRY_CODE_INVALID", "Country of phone number not valid");

        return mpp;
    }
}
