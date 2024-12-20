package ma.wora.malek.survey.controller;

import jakarta.validation.Valid;
import ma.wora.malek.survey.dto.api.ApiResponseDTO;
import ma.wora.malek.survey.dto.chapter.ChapterRequestDTO;
import ma.wora.malek.survey.service.ChapterService;
import ma.wora.malek.survey.util.AbstractCrudController;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys/{surveyId}/chapters")
public class ChapterController extends AbstractCrudController<
        ChapterRequestDTO,
        ChapterRequestDTO,
        ChapterRequestDTO
        > {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        super(chapterService);
        this.chapterService = chapterService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ChapterRequestDTO>> addChapter(
            @PathVariable Long surveyId,
            @Valid @RequestBody ChapterRequestDTO chapterRequestDTO) {
        ChapterRequestDTO savedChapter = chapterService.addChapterToSurveyEdition(surveyId, chapterRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.success(savedChapter));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<ChapterRequestDTO>>> getChapters(
            @PathVariable Long surveyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return super.getAll(page, size, sortBy, sortDirection);
    }

    @GetMapping("/{chapterId}")
    public ResponseEntity<ApiResponseDTO<ChapterRequestDTO>> getChapter(
            @PathVariable Long surveyId,
            @PathVariable Long chapterId) {
        ChapterRequestDTO chapter = chapterService.getChapterById(surveyId, chapterId);
        return ResponseEntity.ok(ApiResponseDTO.success(chapter));
    }

    @PutMapping("/{chapterId}")
    public ResponseEntity<ApiResponseDTO<ChapterRequestDTO>> updateChapter(
            @PathVariable Long surveyId,
            @PathVariable Long chapterId,
            @Valid @RequestBody ChapterRequestDTO chapterRequestDTO) {
        ChapterRequestDTO updatedChapter = chapterService.updateChapter(surveyId, chapterId, chapterRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.success(updatedChapter));
    }

    @DeleteMapping("/{chapterId}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteChapter(
            @PathVariable Long surveyId,
            @PathVariable Long chapterId) {
        chapterService.deleteChapter(surveyId, chapterId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponseDTO.success(null));
    }

    @PostMapping("/{chapterId}/subchapters")
    public ResponseEntity<ApiResponseDTO<ChapterRequestDTO>> addSubChapter(
            @PathVariable Long chapterId,
            @Valid @RequestBody ChapterRequestDTO subChapterDTO) {
        ChapterRequestDTO savedSubChapter = chapterService.addSubChapter(chapterId, subChapterDTO);
        return ResponseEntity.ok(ApiResponseDTO.success(savedSubChapter));
    }
}