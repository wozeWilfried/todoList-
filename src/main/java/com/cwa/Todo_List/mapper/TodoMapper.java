package com.cwa.Todo_List.mapper;

import com.cwa.Todo_List.dto.CreateTodoRequest;
import com.cwa.Todo_List.dto.TodoDTO;
import com.cwa.Todo_List.dto.UpdateTodoRequest;
import com.cwa.Todo_List.entities.Todo;

/**
 * Interface du mapper pour la conversion entre entités Todo et DTOs
 * Implementation manuelle dans TodoMapperImpl
 */
public interface TodoMapper {

    /**
     * Convertit une entité Todo en TodoDTO
     *
     * @param todo L'entité à convertir
     * @return Le DTO correspondant
     */
    TodoDTO toDTO(Todo todo);

    /**
     * Convertit une requête de création en entité Todo
     *
     * @param request La requête de création
     * @return L'entité Todo créée
     */
    Todo toEntity(CreateTodoRequest request);

    /**
     * Met à jour une entité Todo existante avec les données d'une requête de mise à jour
     * Les valeurs null dans la requête sont ignorées
     *
     * @param request La requête contenant les nouvelles valeurs
     * @param todo L'entité à mettre à jour
     */
    void updateEntityFromDTO(UpdateTodoRequest request, Todo todo);
}