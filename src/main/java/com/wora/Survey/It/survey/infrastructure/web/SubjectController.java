package com.wora.Survey.It.survey.infrastructure.web;

import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.common.Validation.Exists;
import com.wora.Survey.It.survey.application.dto.request.SubSubjectRequestDto;
import com.wora.Survey.It.survey.application.dto.request.SubjectRequestdto;
import com.wora.Survey.It.survey.application.dto.response.SubjectResponseDto;
import com.wora.Survey.It.survey.application.service.SubjectServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SubjectController {
    @Autowired
    private SubjectServiceImpl subjectService;
    @PostMapping("/{surveyEditionId}/chapters")
    public ResponseEntity<SubjectResponseDto> createChapter(
            @PathVariable Long surveyEditionId,
             @RequestBody SubjectRequestdto requestDto) {
        SubjectRequestdto updatedRequestDto = new SubjectRequestdto(
                requestDto.title(),
                surveyEditionId,
                requestDto.parentSubjectId()
        );
        SubjectResponseDto response = subjectService.save(updatedRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/{subjectId}/subsubjects")
    public ResponseEntity<SubjectResponseDto> addSubSubject(
            @PathVariable Long subjectId,
            @RequestBody SubSubjectRequestDto dto) {
        SubjectResponseDto responseDto = subjectService.addSubSubject(subjectId, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
