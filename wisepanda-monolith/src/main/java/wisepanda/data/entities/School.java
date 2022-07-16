package wisepanda.data.entities;

import lombok.*;
import wisepanda.data.entities.contact.Contact;
import wisepanda.data.enums.AffiliationType;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="school")
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "school_seq")
    @SequenceGenerator(name = "school_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    @NonNull
    private String name;

    @Column(name="affiliation")
    @Enumerated(EnumType.STRING)
    private AffiliationType affiliation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    @ToString.Exclude
    private Organisation organisation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_id")
    @ToString.Exclude
    private Contact contact;

    @Column(name="is_approved")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
