package com.cwa.Todo_List.mapper;

import com.cwa.Todo_List.dto.CreateTodoRequest;
import com.cwa.Todo_List.dto.TodoDTO;
import com.cwa.Todo_List.dto.UpdateTodoRequest;
import com.cwa.Todo_List.entities.Todo;
import org.springframework.stereotype.Component;

/**
 * Manual implementation of TodoMapper
 * This replaces the MapStruct auto-generated implementation
 */
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public TodoDTO toDTO(Todo todo) {
        if (todo == null) {
            return null;
        }

        return TodoDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.getCompleted())
                .priority(todo.getPriority())
                .dueDate(todo.getDueDate())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }

    @Override
    public Todo toEntity(CreateTodoRequest request) {
        if (request == null) {
            return null;
        }

        return Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .build();
    }

    @Override
    public void updateEntityFromDTO(UpdateTodoRequest request, Todo todo) {
        if (request == null || todo == null) {
            return;
        }

        if (request.getTitle() != null) {
            todo.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            todo.setDescription(request.getDescription());
        }
        if (request.getCompleted() != null) {
            todo.setCompleted(request.getCompleted());
        }
        if (request.getPriority() != null) {
            todo.setPriority(request.getPriority());
        }
        if (request.getDueDate() != null) {
            todo.setDueDate(request.getDueDate());
        }
    }
}