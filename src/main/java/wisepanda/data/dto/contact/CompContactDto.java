package wisepanda.data.dto.contact;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.enums.ContactType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompContactDto {
    private Long id;
    private ContactType type;
    @JsonAlias({"phone_numbers"})
    private List<PhoneNumberDto> phoneNumbers;
    private List<EmailDto> emails;
    private List<AddressDto> addresses;
}
