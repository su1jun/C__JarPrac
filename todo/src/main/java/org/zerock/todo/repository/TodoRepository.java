package org.zerock.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.todo.domain.Todo;
import org.zerock.todo.repository.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
}
