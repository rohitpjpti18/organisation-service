package wisepanda.data.entities.question;


import lombok.*;
import wisepanda.data.entities.Organisation;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="question")
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_question_seq")
    @SequenceGenerator(name = "s_question_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="question_name")
    @JsonProperty("question_name")
    private String questionName;

    @ManyToOne
    @JoinColumn(name="organisation_id")
    private Organisation organisation;

    @Column(name="default_weightage")
    @JsonProperty("default_weightage")
    private Short defaultWeightage;

    @Column(name="negative_marking")
    private Short negativeMarking = 0;

    @Column(name="is_approved")
    @JsonProperty("is_approved")
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
