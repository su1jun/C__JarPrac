package org.zerock.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.todo.domain.APIUser;

public interface APIUserRepository extends JpaRepository<APIUser, String> {
}
