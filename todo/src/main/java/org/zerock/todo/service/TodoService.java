package org.zerock.todo.service;

import org.zerock.todo.dto.PageRequestDTO;
import org.zerock.todo.dto.PageResponseDTO;
import org.zerock.todo.dto.TodoDTO;

import javax.transaction.Transactional;

@Transactional
public interface TodoService {
    Long register(TodoDTO todoDTO);
    TodoDTO read(Long tno);
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
    void remove(Long tno);
    void modify(TodoDTO todoDTO);

}
