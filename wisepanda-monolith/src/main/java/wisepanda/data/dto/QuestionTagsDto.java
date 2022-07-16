package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.question.QuestionTags;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTagsDto implements Serializable {
    private Long id;
    private QuestionDto question;
    private TopicTagDto topicTag;

    public void fill(QuestionTags q){
        if(q != null) {
            q.setId(id);
            question.fill(q.getQuestion());
            topicTag.fill(q.getTopicTag());
        }
    }
}
