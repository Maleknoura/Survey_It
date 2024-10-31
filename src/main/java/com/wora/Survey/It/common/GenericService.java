package com.wora.Survey.It.common;

import java.util.List;
import java.util.Optional;

public interface GenericService<RequestDto, ResponseDto, ID> {
    ResponseDto save(RequestDto requestDto);
    List<ResponseDto> findAll();
    Optional<ResponseDto> findById(ID id);
    ResponseDto update(RequestDto requestDto, ID id);
    void deleteById(ID id);

}
