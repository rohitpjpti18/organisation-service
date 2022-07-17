package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.PhoneNumber;
import wisepanda.data.enums.ActivationMethod;
import wisepanda.data.enums.PhoneNumberType;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDto implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private ContactDto contact;
    private String number;
    private PhoneNumberType type;
    private CountryCodeDto countryCode;
    private Boolean isVerified = false;
    private Boolean isActive = false;
    private Instant activatedOn;
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
