package com.cwa.Todo_List.dto;

import com.cwa.Todo_List.entities.Todo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request object for updating an existing Todo")
public class UpdateTodoRequest {
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    @Schema(description = "Todo title", example = "Updated task title")
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Todo description", example = "Updated description")
    private String description;

    @Schema(description = "Completion status", example = "true")
    private Boolean completed;

    @Schema(description = "Todo priority level", example = "URGENT")
    private Todo.Priority priority;

    @Schema(description = "Due date for the todo", example = "2025-12-31T23:59:59")
    private LocalDateTime dueDate;
}
