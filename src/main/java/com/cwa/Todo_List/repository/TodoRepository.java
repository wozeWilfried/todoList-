package com.cwa.Todo_List.repository;

import com.cwa.Todo_List.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByCompleted(Boolean completed);

    List<Todo> findByPriority(Todo.Priority priority);

    List<Todo> findByDueDateBefore(LocalDateTime date);

    @Query("SELECT t FROM Todo t WHERE t.dueDate BETWEEN :start AND :end")
    List<Todo> findByDueDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT t FROM Todo t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Todo> searchByKeyword(String keyword);
}