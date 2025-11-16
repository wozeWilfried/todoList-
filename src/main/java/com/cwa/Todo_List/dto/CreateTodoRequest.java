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

/**
 * DTO pour la création d'une nouvelle tâche
 * Contient les données minimales nécessaires pour créer un Todo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Requête pour créer une nouvelle tâche")
public class CreateTodoRequest {

    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(min = 1, max = 200, message = "Le titre doit contenir entre 1 et 200 caractères")
    @Schema(description = "Titre de la tâche", example = "Acheter du pain", required = true)
    private String title;

    @Size(max = 1000, message = "La description ne peut pas dépasser 1000 caractères")
    @Schema(description = "Description détaillée de la tâche", example = "Aller à la boulangerie avant 18h")
    private String description;

    @Schema(description = "Date et heure d'échéance de la tâche", example = "2025-12-31T23:59:59")
    private LocalDateTime dueDate;

    @Schema(description = "Priorité de la tâche", example = "HIGH",
            allowableValues = {"LOW", "MEDIUM", "HIGH", "URGENT"})
    private Todo.Priority priority;
}