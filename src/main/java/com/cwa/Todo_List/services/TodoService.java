package com.cwa.Todo_List.services;

import com.cwa.Todo_List.dto.CreateTodoRequest;
import com.cwa.Todo_List.dto.TodoDTO;
import com.cwa.Todo_List.dto.UpdateTodoRequest;
import com.cwa.Todo_List.entities.Todo;
import com.cwa.Todo_List.exception.TodoNotFoundException;
import com.cwa.Todo_List.mapper.TodoMapper;
import com.cwa.Todo_List.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoDTO createTodo(CreateTodoRequest request) {
        log.info("Creating new todo: {}", request.getTitle());

        Todo todo = todoMapper.toEntity(request);
        if (todo.getPriority() == null) {
            todo.setPriority(Todo.Priority.MEDIUM);
        }
        if (todo.getCompleted() == null) {
            todo.setCompleted(false);
        }

        Todo savedTodo = todoRepository.save(todo);
        log.info("Todo created successfully with id: {}", savedTodo.getId());

        return todoMapper.toDTO(savedTodo);
    }

    @Transactional(readOnly = true)
    public List<TodoDTO> getAllTodos() {
        log.info("Fetching all todos");
        return todoRepository.findAll()
                .stream()
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TodoDTO getTodoById(Long id) {
        log.info("Fetching todo with id: {}", id);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
        return todoMapper.toDTO(todo);
    }

    public TodoDTO updateTodo(Long id, UpdateTodoRequest request) {
        log.info("Updating todo with id: {}", id);

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todoMapper.updateEntityFromDTO(request, todo);
        Todo updatedTodo = todoRepository.save(todo);

        log.info("Todo updated successfully with id: {}", id);
        return todoMapper.toDTO(updatedTodo);
    }

    public void deleteTodo(Long id) {
        log.info("Deleting todo with id: {}", id);

        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }

        todoRepository.deleteById(id);
        log.info("Todo deleted successfully with id: {}", id);
    }

    @Transactional(readOnly = true)
    public List<TodoDTO> getTodosByStatus(Boolean completed) {
        log.info("Fetching todos by status: {}", completed);
        return todoRepository.findByCompleted(completed)
                .stream()
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TodoDTO> getTodosByPriority(Todo.Priority priority) {
        log.info("Fetching todos by priority: {}", priority);
        return todoRepository.findByPriority(priority)
                .stream()
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TodoDTO> getOverdueTodos() {
        log.info("Fetching overdue todos");
        return todoRepository.findByDueDateBefore(LocalDateTime.now())
                .stream()
                .filter(todo -> !todo.getCompleted())
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TodoDTO> searchTodos(String keyword) {
        log.info("Searching todos with keyword: {}", keyword);
        return todoRepository.searchByKeyword(keyword)
                .stream()
                .map(todoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TodoDTO toggleTodoStatus(Long id) {
        log.info("Toggling status for todo with id: {}", id);

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        todo.setCompleted(!todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);

        log.info("Todo status toggled successfully for id: {}", id);
        return todoMapper.toDTO(updatedTodo);
    }
}