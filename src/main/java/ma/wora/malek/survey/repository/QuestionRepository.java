package ma.wora.malek.survey.repository;

import ma.wora.malek.survey.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository  extends JpaRepository<Question, Long> {
    List<Question> findByChapterId(Long chapterId);
    Page<Question> findBySubChapterId(Long subChapterId, Pageable pageable);


}
