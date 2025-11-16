package com.cwa.Todo_List.services;

import com.cwa.Todo_List.dto.CreateTodoRequest;
import com.cwa.Todo_List.dto.TodoDTO;
import com.cwa.Todo_List.dto.UpdateTodoRequest;
import com.cwa.Todo_List.entities.Todo;

import java.util.List;

/**
 * Interface du service de gestion des tâches
 */
public interface TodoService {
    TodoDTO createTodo(CreateTodoRequest request);
    List<TodoDTO> getAllTodos();
    TodoDTO getTodoById(Long id);
    TodoDTO updateTodo(Long id, UpdateTodoRequest request);
    void deleteTodo(Long id);
    List<TodoDTO> getTodosByStatus(Boolean completed);
    List<TodoDTO> getTodosByPriority(Todo.Priority priority);
    List<TodoDTO> getOverdueTodos();
    List<TodoDTO> searchTodos(String keyword);
    TodoDTO toggleTodoStatus(Long id);
}