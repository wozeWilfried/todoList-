package com.cwa.Todo_List.mapper;

import com.cwa.Todo_List.dto.CreateTodoRequest;
import com.cwa.Todo_List.dto.TodoDTO;
import com.cwa.Todo_List.dto.UpdateTodoRequest;
import com.cwa.Todo_List.entities.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * MapStruct mapper for converting between Todo entities and DTOs
 * Handles bidirectional conversion and partial updates
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TodoMapper {

    /**
     * Converts a Todo entity to TodoDTO
     *
     * @param todo The entity to convert
     * @return The corresponding DTO
     */
    TodoDTO toDTO(Todo todo);

    /**
     * Converts a creation request to a Todo entity
     *
     * @param request The creation request
     * @return The created Todo entity
     */
    Todo toEntity(CreateTodoRequest request);

    /**
     * Updates an existing Todo entity with data from an update request
     * Null values in the request are ignored
     *
     * @param request The request containing the new values
     * @param todo The entity to update
     */
    void updateEntityFromDTO(UpdateTodoRequest request, @MappingTarget Todo todo);
}