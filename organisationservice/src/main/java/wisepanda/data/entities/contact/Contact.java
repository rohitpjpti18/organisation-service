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
@NoArgsConstructor
@Table(name="CONTACT")
@Entity
public class Contact {
    @Id
    private Long id;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(name="CREATED_AT")
    @NonNull
    private Instant createdAt;

    @Column(name="CREATED_BY")
    @NonNull
    private Long createdBy;

    @Column(name="LAST_UPDATED_AT")
    @NonNull
    private Instant lastUpdatedAt;

    @Column(name="LAST_UPDATED_BY")
    @NonNull
    private Long lastUpdatedBy;

    @Column(name="APPROVED_AT")
    @NonNull
    private Instant approvedAt;

    @Column(name="APPROVED_BY")
    @NonNull
    private Long approvedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && type == contact.type && createdAt.equals(contact.createdAt) && createdBy.equals(contact.createdBy) && lastUpdatedAt.equals(contact.lastUpdatedAt) && lastUpdatedBy.equals(contact.lastUpdatedBy) && approvedAt.equals(contact.approvedAt) && approvedBy.equals(contact.approvedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, createdAt, createdBy, lastUpdatedAt, lastUpdatedBy, approvedAt, approvedBy);
    }
}
