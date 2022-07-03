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
@Table(name="SCHOOL")
@Entity
public class School {

    @Id
    private Long id;

    @Column(name="NAME")
    @NonNull
    private String name;

    @Column(name="AFFILIATION")
    @Enumerated(EnumType.STRING)
    private AffiliationType affiliation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORG_ID")
    @ToString.Exclude
    private Organisation organisation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CONTACT_ID")
    @ToString.Exclude
    private Contact contact;

    @Column(name="IS_APPROVED")
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
