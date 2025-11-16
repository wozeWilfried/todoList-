package com.cwa.Todo_List.dto;

import com.cwa.Todo_List.entities.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Todo Data Transfer Object")
public class TodoDTO {
    @Schema(description = "Todo unique identifier", example = "1")
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    @Schema(description = "Todo title", example = "Complete project documentation", required = true)
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Todo description", example = "Write comprehensive documentation for the TodoList API")
    private String description;

    @Schema(description = "Completion status", example = "false")
    private Boolean completed;

    @Schema(description = "Todo priority level", example = "HIGH")
    private Todo.Priority priority;

    @Schema(description = "Due date for the todo", example = "2025-12-31T23:59:59")
    private LocalDateTime dueDate;

    @Schema(description = "Creation timestamp", example = "2025-11-16T10:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp", example = "2025-11-16T15:45:00")
    private LocalDateTime updatedAt;
}
