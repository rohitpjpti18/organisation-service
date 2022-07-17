package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.enums.ContactType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompContactDto {
    private Long id;
    private ContactType type;
    private List<PhoneNumberDto> phoneNumbers;
    private List<EmailDto> emails;
    private List<AddressDto> addresses;
}
