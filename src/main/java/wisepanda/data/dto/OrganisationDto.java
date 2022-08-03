package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.dto.contact.ContactDto;
import wisepanda.data.entities.Organisation;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDto implements Serializable {
    @JsonIgnore
    private Long id;
    private String name;
    private ContactDto contact = new ContactDto();

    public OrganisationDto(Organisation organisation) {
        if(organisation != null) {
            this.id = organisation.getId();
            this.name = organisation.getName();
            this.contact = new ContactDto(organisation.getContact());
        }
    }

    public void fill(Organisation o) {
        if(o != null){
            o.setId(id);
            o.setName(name);
            contact.fill(o.getContact());
        }
    }
}
