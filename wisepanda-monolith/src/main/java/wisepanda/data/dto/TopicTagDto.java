package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicTagDto implements Serializable {
    private Long id;
    private QuestionDto question;
    private String tagName;
    private Boolean isApproved;
}
