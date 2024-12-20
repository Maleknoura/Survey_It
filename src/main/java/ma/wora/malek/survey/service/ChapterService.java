package ma.wora.malek.survey.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import ma.wora.malek.survey.dto.chapter.ChapterRequestDTO;
import ma.wora.malek.survey.entity.Chapter;
import ma.wora.malek.survey.entity.SurveyEdition;
import ma.wora.malek.survey.mapper.ChapterMapper;
import ma.wora.malek.survey.repository.ChapterRepository;
import ma.wora.malek.survey.repository.SurveyEditionRepository;
import ma.wora.malek.survey.util.AbstractGenericService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChapterService extends AbstractGenericService<Chapter, Long, ChapterRequestDTO, ChapterRequestDTO, ChapterRequestDTO> {

    private final SurveyEditionRepository surveyEditionRepository;
    private final ChapterMapper chapterMapper;
    private final ChapterRepository chapterRepository;

    public ChapterService(
            ChapterRepository chapterRepository,
            SurveyEditionRepository surveyEditionRepository,
            ChapterMapper chapterMapper
    ) {
        super(chapterRepository);
        this.surveyEditionRepository = surveyEditionRepository;
        this.chapterMapper = chapterMapper;
        this.chapterRepository = chapterRepository;
    }

    @Override
    protected ChapterRequestDTO mapToCreateDto(Chapter entity) {
        return chapterMapper.toDto(entity);
    }

    @Override
    protected Chapter mapToEntity(ChapterRequestDTO createDto) {
        return chapterMapper.toEntity(createDto);
    }

    @Override
    protected void mapToEntity(ChapterRequestDTO updateDto, Chapter entity) {
        Chapter updatedChapter = chapterMapper.toEntity(updateDto);
        entity.setTitle(updatedChapter.getTitle());

    }

    @Override
    protected ChapterRequestDTO mapToResponseDto(Chapter entity) {
        return chapterMapper.toDto(entity);
    }

    public ChapterRequestDTO addChapterToSurveyEdition(Long surveyEditionId, ChapterRequestDTO chapterRequestDTO) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(surveyEditionId)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with id: " + surveyEditionId));

        Chapter chapter = mapToEntity(chapterRequestDTO);
        chapter.setSurveyEdition(surveyEdition);

        if (chapterRequestDTO.getParentChapterId() != null) {
            Chapter parentChapter = chapterRepository.findById(chapterRequestDTO.getParentChapterId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent chapter not found with id: " +
                            chapterRequestDTO.getParentChapterId()));
            chapter.setParentChapter(parentChapter);
            parentChapter.getSubChapters().add(chapter);
            chapterRepository.save(parentChapter);
        }

        Chapter savedChapter = chapterRepository.save(chapter);
        return mapToCreateDto(savedChapter);
    }

    public List<ChapterRequestDTO> getChaptersBySurveyEditionId(Long surveyEditionId) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(surveyEditionId)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with id: " + surveyEditionId));

        return surveyEdition.getChapters().stream()
                .filter(chapter -> chapter.getParentChapter() == null)
                .map(this::mapToCreateDto)
                .collect(Collectors.toList());
    }

    public ChapterRequestDTO getChapterById(Long surveyEditionId, Long chapterId) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(surveyEditionId)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with id: " + surveyEditionId));

        Chapter chapter = findOrThrow(chapterId);

        if (!chapter.getSurveyEdition().equals(surveyEdition)) {
            throw new EntityNotFoundException("Chapter does not belong to the specified SurveyEdition");
        }

        return mapToCreateDto(chapter);
    }

    public ChapterRequestDTO updateChapter(Long surveyEditionId, Long chapterId, ChapterRequestDTO chapterRequestDTO) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(surveyEditionId)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with id: " + surveyEditionId));

        Chapter existingChapter = findOrThrow(chapterId);

        if (!existingChapter.getSurveyEdition().equals(surveyEdition)) {
            throw new EntityNotFoundException("Chapter does not belong to the specified SurveyEdition");
        }

        mapToEntity(chapterRequestDTO, existingChapter);
        existingChapter.setSurveyEdition(surveyEdition);

        if (chapterRequestDTO.getParentChapterId() != null) {
            Chapter parentChapter = chapterRepository.findById(chapterRequestDTO.getParentChapterId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent chapter not found with id: " +
                            chapterRequestDTO.getParentChapterId()));
            existingChapter.setParentChapter(parentChapter);
        }

        Chapter savedChapter = chapterRepository.save(existingChapter);
        return mapToResponseDto(savedChapter);
    }

    public void deleteChapter(Long surveyEditionId, Long chapterId) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(surveyEditionId)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with id: " + surveyEditionId));

        Chapter chapter = findOrThrow(chapterId);

        if (!chapter.getSurveyEdition().equals(surveyEdition)) {
            throw new EntityNotFoundException("Chapter does not belong to the specified SurveyEdition");
        }

        if (chapter.getParentChapter() != null) {
            chapter.getParentChapter().getSubChapters().remove(chapter);
        }

        chapter.getSubChapters().clear();
        delete(chapterId);
    }

    public ChapterRequestDTO addSubChapter(Long parentChapterId, ChapterRequestDTO subChapterDTO) {
        Chapter parentChapter = findOrThrow(parentChapterId);

        Chapter subChapter = mapToEntity(subChapterDTO);
        subChapter.setParentChapter(parentChapter);
        subChapter.setSurveyEdition(parentChapter.getSurveyEdition());

        Chapter savedSubChapter = chapterRepository.save(subChapter);

        parentChapter.getSubChapters().add(savedSubChapter);
        chapterRepository.save(parentChapter);

        return mapToCreateDto(savedSubChapter);
    }

    public Optional<Chapter> findById(Long chapterId) {
        return chapterRepository.findById(chapterId);
    }
}