package com.cwa.Todo_List.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Standard error response object")
public class ErrorResponse {

    @Schema(description = "Timestamp when the error occurred", example = "2025-11-19T14:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "404")
    private int status;

    @Schema(description = "Error type", example = "Not Found")
    private String error;

    @Schema(description = "Detailed error message", example = "Todo not found with id: 123")
    private String message;

    @Schema(description = "Request path that caused the error", example = "/api/v1/todos/123")
    private String path;

    @Schema(description = "Validation errors (if any)", example = "{\"title\": \"Title cannot be empty\"}")
    private Map<String, String> validationErrors;
}