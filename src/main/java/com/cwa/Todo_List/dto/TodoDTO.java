package com.cwa.Todo_List.dto;

import com.cwa.Todo_List.entities.Todo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Todo Data Transfer Object - Represents a task in the system")
public class TodoDTO {

    @Schema(description = "Unique identifier of the todo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    @Schema(description = "Title of the todo task", example = "Complete project documentation", required = true, maxLength = 200)
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Detailed description of the todo", example = "Write comprehensive documentation for the TodoList API including all endpoints", maxLength = 1000)
    private String description;

    @Schema(description = "Indicates whether the todo is completed", example = "false", defaultValue = "false")
    private Boolean completed;

    @Schema(description = "Priority level of the todo", example = "HIGH", allowableValues = {"LOW", "MEDIUM", "HIGH", "URGENT"}, defaultValue = "MEDIUM")
    private Todo.Priority priority;

    @Schema(description = "Due date and time for completing the todo", example = "2025-12-31T23:59:59", type = "string", format = "date-time")
    private LocalDateTime dueDate;

    @Schema(description = "Timestamp when the todo was created", example = "2025-11-19T10:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the todo was last updated", example = "2025-11-19T15:45:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;
}