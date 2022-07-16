package wisepanda.data.entities.contact;

import lombok.*;
import org.hibernate.Hibernate;
import wisepanda.data.enums.ActivationMethod;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Column(name="IS_VERIFIED")
    private Boolean isVerified;

    @Column(name="IS_ACTIVE")
    private Boolean isActive;

    @Column(name="ACTIVATED_ON")
    private Instant instant;

    @Column(name="ACTIVATION_METHOD")
    private ActivationMethod activationMethod;

    @Column(name="IS_APPROVED")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
