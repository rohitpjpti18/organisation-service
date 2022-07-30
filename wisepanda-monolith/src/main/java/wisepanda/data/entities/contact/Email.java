package wisepanda.data.entities.contact;

import lombok.*;
import wisepanda.enums.ActivationMethod;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_email_seq")
    @SequenceGenerator(name = "s_email_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private Contact contact;

    @Column(name="email_address", unique = true)
    private String emailAddress;

    @Column(name="is_verified")
    private Boolean isVerified;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="activated_on")
    private Instant activatedOn;

    @Column(name="activation_method")
    private ActivationMethod activationMethod;

    @Column(name="is_approved")
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
