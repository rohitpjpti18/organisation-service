package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.Organisation;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDto implements Serializable {
    private Long id;
    private String name;
    private ContactDto contact;

    public void fill(Organisation o) {
        if(o != null){
            o.setId(id);
            o.setName(name);
            contact.fill(o.getContact());
        }
    }
}
