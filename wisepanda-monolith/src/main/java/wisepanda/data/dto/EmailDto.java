package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.Email;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto implements Serializable {
    @JsonIgnore
    private Long id;
    private ContactDto contact;
    private String emailAddress;

    public void fill(Email e) {
        if(e != null){
            e.setId(id);
            contact.fill(e.getContact());
            e.setEmailAddress(emailAddress);
        }
    }
}
