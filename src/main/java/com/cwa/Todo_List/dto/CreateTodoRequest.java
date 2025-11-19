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
@Schema(description = "Request object for creating a new todo task")
public class CreateTodoRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    @Schema(description = "Title of the task", example = "Buy groceries", required = true, maxLength = 200)
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Schema(description = "Detailed description of the task", example = "Buy milk, eggs, bread, and fruits from the supermarket", maxLength = 1000)
    private String description;

    @Schema(description = "Due date and time for the task", example = "2025-11-30T18:00:00", type = "string", format = "date-time")
    private LocalDateTime dueDate;

    @Schema(description = "Priority level of the task", example = "HIGH", allowableValues = {"LOW", "MEDIUM", "HIGH", "URGENT"}, defaultValue = "MEDIUM")
    private Todo.Priority priority;
}