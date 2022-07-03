package wisepanda.data.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="QUESTION_TYPE_DETAIL_TYPE")
@Entity
public class QuestionTypeDetailType {
    @Id
    private Long id;

    @Column(name="QUESTION_DESCRIPTION")
    @NonNull
    private String questionDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    @ToString.Exclude
    private Question question;

    @Column(name="IS_APPROVED")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionTypeDetailType that = (QuestionTypeDetailType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
