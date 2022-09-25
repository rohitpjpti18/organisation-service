package wisepanda.data.entities.question;

import lombok.*;
import wisepanda.enums.TagType;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_topic_tag_seq")
    @SequenceGenerator(name = "s_topic_tag_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="tag_name")
    private String tagName;

    @Column(name="is_approved")
    private Boolean isApproved;

    @Column(name="tag_type")
    @Enumerated(EnumType.STRING)
    private TagType tagType = TagType.BASE;

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
