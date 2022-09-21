package wisepanda.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wisepanda.data.entities.question.Question;
import wisepanda.data.entities.question.QuestionTags;
import wisepanda.data.entities.question.TopicTag;

import java.util.List;
import java.util.Optional;

public interface QuestionTagsRepository extends JpaRepository<QuestionTags, Long> {
    @Query(value="SELECT qt.question.id FROM QuestionTags qt WHERE qt.topicTag IN :topicTagIds GROUP BY qt.question.id")
    List<Long> findByTopicId(List<Long> topicTagIds);

    //@Query(value="FROM QuestionTags qt WHERE qt.topicTag.tagName IN :topicTags GROUP BY qt.question")
    //List<QuestionTags> findByTopic(@Param(value="topicTags") List<String> tags, @Param(value="value") Integer value);

    @Query(value="FROM QuestionTags qt WHERE qt.question.id = ?1 AND qt.topicTag.id = ?2")
    Optional<QuestionTags> findByQuesAndTag(Long questionId, Long topicTagId);

    @Query(value="FROM QuestionTags qt WHERE qt.question = :question")
    List<QuestionTags> findByQuestion(@Param(value="question") Question q);
}