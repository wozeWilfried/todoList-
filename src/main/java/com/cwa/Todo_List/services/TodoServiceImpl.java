package com.cwa.Todo_List.services;

import com.cwa.Todo_List.dto.CreateTodoRequest;
import com.cwa.Todo_List.dto.TodoDTO;
import com.cwa.Todo_List.dto.UpdateTodoRequest;
import com.cwa.Todo_List.entities.Todo;
import com.cwa.Todo_List.exception.TodoNotFoundException;
import com.cwa.Todo_List.mapper.TodoMapper;
import com.cwa.Todo_List.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the todo management service
 * Contains all the business logic of the application
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    /**
     * Creates a new todo
     *
     * @param request The creation request containing todo data
     * @return The created todo as DTO
     */
    @Override
    public TodoDTO createTodo(CreateTodoRequest request) {
        Todo todo = todoMapper.toEntity(request);
        // Set default values if necessary
        if (todo.getCompleted() == null) {
            todo.setCompleted(false);
        }
        if (todo.getPriority() == null) {
            todo.setPriority(Todo.Priority.MEDIUM);
        }
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toDTO(savedTodo);
    }

    /**
     * Retrieves all todos
     *
     * @return List of all todos as DTOs
     */
    @Override
    @Transactional(readOnly = true)
    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a todo by its ID
     *
     * @param id The todo identifier
     * @return The found todo as DTO
     * @throws TodoNotFoundException if the todo doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
        return todoMapper.toDTO(todo);
    }

    /**
     * Updates an existing todo
     *
     * @param id The todo identifier
     * @param request The update request containing new data
     * @return The updated todo as DTO
     * @throws TodoNotFoundException if the todo doesn't exist
     */
    @Override
    public TodoDTO updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todoMapper.updateEntityFromDTO(request, todo);
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.toDTO(updatedTodo);
    }

    /**
     * Deletes a todo
     *
     * @param id The todo identifier
     * @throws TodoNotFoundException if the todo doesn't exist
     */
    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteById(id);
    }

    /**
     * Retrieves todos filtered by completion status
     *
     * @param completed The completion status (true/false)
     * @return List of filtered todos as DTOs
     */
    @Override
    @Transactional(readOnly = true)
    public List<TodoDTO> getTodosByStatus(Boolean completed) {
        return todoRepository.findByCompleted(completed).stream()
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves todos filtered by priority
     *
     * @param priority The priority level
     * @return List of filtered todos as DTOs
     */
    @Override
    @Transactional(readOnly = true)
    public List<TodoDTO> getTodosByPriority(Todo.Priority priority) {
        return todoRepository.findByPriority(priority).stream()
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all overdue todos
     * A todo is overdue if it's not completed and its due date has passed
     *
     * @return List of overdue todos as DTOs
     */
    @Override
    @Transactional(readOnly = true)
    public List<TodoDTO> getOverdueTodos() {
        LocalDateTime now = LocalDateTime.now();
        return todoRepository.findByDueDateBefore(now).stream()
                .filter(todo -> !todo.getCompleted())
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Searches todos by keyword in title or description
     *
     * @param keyword The search keyword
     * @return List of matching todos as DTOs
     */
    @Override
    @Transactional(readOnly = true)
    public List<TodoDTO> searchTodos(String keyword) {
        return todoRepository.searchByKeyword(keyword).stream()
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Toggles the completion status of a todo
     * Changes completed to not completed and vice versa
     *
     * @param id The todo identifier
     * @return The updated todo as DTO
     * @throws TodoNotFoundException if the todo doesn't exist
     */
    @Override
    public TodoDTO toggleTodoStatus(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todo.setCompleted(!todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.toDTO(updatedTodo);
    }
}