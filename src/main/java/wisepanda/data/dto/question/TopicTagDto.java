package wisepanda.data.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import wisepanda.data.entities.question.TopicTag;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicTagDto implements Serializable {

    private Long id;
    private String tagName;
    private Boolean isApproved;

    public TopicTagDto(TopicTag t) {
        if(t != null) {
            this.id = t.getId();
            this.tagName = t.getTagName();
            this.isApproved = t.getIsApproved();
        }
    }

    public void fill(TopicTag t) {
        if(t != null) {
            t.setId(id);
            t.setTagName(tagName);
            t.setIsApproved(isApproved);
        }
    }
}
