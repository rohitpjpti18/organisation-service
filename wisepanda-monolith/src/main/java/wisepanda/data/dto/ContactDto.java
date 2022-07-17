package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.Contact;
import wisepanda.data.enums.ContactType;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto implements Serializable {
    private Long id;
    private ContactType type;
    private Boolean isApproved = false;

    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.type = contact.getType();
        this.isApproved = contact.getIsApproved();
    }

    public void fill(Contact contact) {
        if(contact != null){
            contact.setId(id);
            contact.setType(type);
            contact.setIsApproved(isApproved);
        }
    }
}
