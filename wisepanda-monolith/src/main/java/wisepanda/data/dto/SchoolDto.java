package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.dto.contact.ContactDto;
import wisepanda.data.entities.School;
import wisepanda.enums.AffiliationType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDto implements Serializable {
    private Long id;
    private String name;
    private AffiliationType affiliation;
    private OrganisationDto organisation;
    private ContactDto contact;

    public void fill(School s) {
        if(s != null) {
            s.setId(id);
            s.setName(name);
            s.setAffiliation(affiliation);
            organisation.fill(s.getOrganisation());
            contact.fill(s.getContact());
        }
    }
}
