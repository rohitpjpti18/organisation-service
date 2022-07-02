package wisepanda.data.entities.contact;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="EMAIL")
@Entity
public class Email {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACT_ID")
    @ToString.Exclude
    private Contact contact;

    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;

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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Email email = (Email) o;
        return id != null && Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
