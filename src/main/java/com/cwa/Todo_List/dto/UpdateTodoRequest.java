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
@Schema(description = "Request object for updating an existing todo. All fields are optional.")
public class UpdateTodoRequest {

    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    @Schema(description = "New title for the task", example = "Updated: Buy groceries and cook dinner", maxLength = 200)
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Updated description of the task", example = "Buy ingredients and prepare dinner for the family", maxLength = 1000)
    private String description;

    @Schema(description = "New completion status", example = "true")
    private Boolean completed;

    @Schema(description = "Updated priority level", example = "URGENT", allowableValues = {"LOW", "MEDIUM", "HIGH", "URGENT"})
    private Todo.Priority priority;

    @Schema(description = "New due date and time", example = "2025-12-01T20:00:00", type = "string", format = "date-time")
    private LocalDateTime dueDate;
}