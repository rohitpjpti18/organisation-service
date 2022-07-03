package wisepanda.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="TOPIC_TAG")
@Entity
public class TopicTag {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    @ToString.Exclude
    private Question question;

    @Column(name="TAG_NAME")
    private String tagName;

    @Column(name="IS_APPROVED")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicTag topicTag = (TopicTag) o;
        return Objects.equals(id, topicTag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
