package wisepanda.data.entities.contact;

import lombok.*;
import wisepanda.data.enums.ContactType;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="CONTACT")
@Entity
public class Contact {
    @Id
    private Long id;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(name="IS_APPROVED")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
