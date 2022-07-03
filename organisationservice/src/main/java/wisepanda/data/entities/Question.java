package wisepanda.data.entities;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="QUESTION")
@Entity
public class Question {
    @Id
    private Long id;

    @Column(name="QUESTION_NAME")
    private String questionName;

    @ManyToOne
    @JoinColumn(name="ORG_ID")
    private Organisation organisation;

    @Column(name="IS_APPROVED")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
