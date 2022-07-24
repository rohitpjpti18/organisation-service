package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.Contact;
import wisepanda.data.enums.ContactType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto implements Serializable {
    private Long id;
    private ContactType type;
    @JsonAlias({"is_approved"})
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
