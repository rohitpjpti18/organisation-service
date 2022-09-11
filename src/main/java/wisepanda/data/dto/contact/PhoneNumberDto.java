package wisepanda.data.dto.contact;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.PhoneNumber;
import wisepanda.enums.ActivationMethod;
import wisepanda.enums.PhoneNumberType;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDto implements Serializable {
    private Long id;
    private ContactDto contact = new ContactDto();
    private String number;
    private PhoneNumberType type;
    @JsonAlias({"country_code"})
    private CountryCodeDto countryCode = new CountryCodeDto();
    @JsonAlias({"is_verified"})
    private Boolean isVerified = false;
    @JsonAlias({"is_active"})
    private Boolean isActive = false;
    @JsonAlias({"activated_on"})
    private Instant activatedOn;
    @JsonAlias({"activation_method"})
    private ActivationMethod activationMethod;


    public PhoneNumberDto(PhoneNumber ph) {
        this.id = ph.getId();
        this.contact = new ContactDto(ph.getContact());
        this.number = ph.getNumber();
        this.type = ph.getType();
        this.countryCode = new CountryCodeDto(ph.getCountryCode());
        this.isVerified = ph.getIsVerified();
        this.isActive = ph.getIsActive();
        this.activatedOn = ph.getActivatedOn();
        this.activationMethod = ph.getActivationMethod();
    }

    public void fill(PhoneNumber p) {
        if(p != null){
            p.setId(id);
            contact.fill(p.getContact());
            p.setNumber(number);
            p.setType(type);
            p.setActivationMethod(activationMethod);
            p.setActivatedOn(activatedOn);
            p.setIsActive(isActive);
            p.setIsVerified(isVerified);
        }
    }
}
