package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
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
    private QuestionDto question = new QuestionDto();
    @JsonAlias({"topic_tag"})
    private TopicTagDto topicTag = new TopicTagDto();

    public void fill(QuestionTags q){
        if(q != null) {
            q.setId(id);
            question.fill(q.getQuestion());
            topicTag.fill(q.getTopicTag());
        }
    }
}
