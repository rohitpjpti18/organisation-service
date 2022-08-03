package wisepanda.data.entities.contact;

import lombok.*;
import wisepanda.enums.ContactType;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="contact")
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_contact_seq")
    @SequenceGenerator(name = "s_contact_seq")
    private Long id;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(name="is_approved")
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
