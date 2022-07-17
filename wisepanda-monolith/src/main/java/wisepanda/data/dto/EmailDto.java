package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.Email;
import wisepanda.data.enums.ActivationMethod;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private ContactDto contact;
    private String emailAddress;
    private Boolean isVerified = false;
    private Boolean isActive = false;
    private Instant activatedOn;
    private ActivationMethod activationMethod;

    public EmailDto(Email e) {
        this.id = e.getId();
        this.contact = new ContactDto(e.getContact());
        this.emailAddress = e.getEmailAddress();
        this.isVerified = e.getIsVerified();
        this.isActive = e.getIsActive();
        this.activatedOn = e.getActivatedOn();
        this.activationMethod = e.getActivationMethod();

    }

    public void fill(Email e) {
        if(e != null){
            e.setId(id);
            contact.fill(e.getContact());
            e.setEmailAddress(emailAddress);
            e.setActivationMethod(activationMethod);
            e.setActivatedOn(activatedOn);
            e.setIsActive(isActive);
            e.setIsVerified(isVerified);
        }
    }
}
