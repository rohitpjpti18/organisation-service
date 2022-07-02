package wisepanda.data.entities;

import lombok.*;
import wisepanda.data.entities.contact.Contact;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="ORGANISATION")
@Entity
public class Organisation {
    @Id
    private Long id;

    @Column(name="NAME")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CONTACT_ID")
    @ToString.Exclude
    private Contact contact;

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
        Organisation that = (Organisation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
