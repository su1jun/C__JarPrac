package org.zerock.todo.repository.search;

import org.springframework.data.domain.Page;
import org.zerock.todo.dto.PageRequestDTO;
import org.zerock.todo.dto.TodoDTO;

public interface TodoSearch {

    Page<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
