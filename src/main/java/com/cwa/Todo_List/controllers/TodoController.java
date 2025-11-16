package com.cwa.Todo_List.controllers;

import com.cwa.Todo_List.dto.CreateTodoRequest;
import com.cwa.Todo_List.dto.TodoDTO;
import com.cwa.Todo_List.dto.UpdateTodoRequest;
import com.cwa.Todo_List.entities.Todo;
import com.cwa.Todo_List.services.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing todos
 * Exposes API endpoints for CRUD operations and search functionality
 *
 * @author Woze Wilfried
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Tag(name = "Todo Management", description = "APIs for managing todos (create, read, update, delete)")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

    /**
     * Creates a new todo
     *
     * @param request The data for the todo to create
     * @return The created todo with HTTP status 201
     */
    @PostMapping
    @Operation(summary = "Create a new todo",
            description = "Creates a new todo with a title, optional description, priority, and due date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Todo created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided")
    })
    public ResponseEntity<TodoDTO> createTodo(
            @Valid @RequestBody CreateTodoRequest request) {
        TodoDTO createdTodo = todoService.createTodo(request);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    /**
     * Retrieves all todos
     *
     * @return The list of all todos
     */
    @GetMapping
    @Operation(summary = "Retrieve all todos",
            description = "Returns the complete list of all registered todos")
    @ApiResponse(responseCode = "200", description = "List of todos retrieved successfully")
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    /**
     * Retrieves a todo by its identifier
     *
     * @param id The todo identifier
     * @return The found todo
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a todo by ID",
            description = "Returns a specific todo based on its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo found"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<TodoDTO> getTodoById(
            @Parameter(description = "Unique identifier of the todo", required = true)
            @PathVariable Long id) {
        TodoDTO todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    /**
     * Updates an existing todo
     *
     * @param id The identifier of the todo to modify
     * @param request The new todo data
     * @return The updated todo
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a todo",
            description = "Modifies the information of an existing todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo updated successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    public ResponseEntity<TodoDTO> updateTodo(
            @Parameter(description = "Identifier of the todo to modify", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateTodoRequest request) {
        TodoDTO updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(updatedTodo);
    }

    /**
     * Deletes a todo
     *
     * @param id The identifier of the todo to delete
     * @return An empty response with status code 204
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a todo",
            description = "Permanently deletes a todo by its identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Todo deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<Void> deleteTodo(
            @Parameter(description = "Identifier of the todo to delete", required = true)
            @PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves todos filtered by status
     *
     * @param completed The completion status (true = completed, false = in progress)
     * @return The list of filtered todos
     */
    @GetMapping("/status/{completed}")
    @Operation(summary = "Retrieve todos by status",
            description = "Filters todos by their completion status")
    @ApiResponse(responseCode = "200", description = "Filtered list retrieved successfully")
    public ResponseEntity<List<TodoDTO>> getTodosByStatus(
            @Parameter(description = "Completion status (true/false)", required = true)
            @PathVariable Boolean completed) {
        List<TodoDTO> todos = todoService.getTodosByStatus(completed);
        return ResponseEntity.ok(todos);
    }

    /**
     * Retrieves todos filtered by priority
     *
     * @param priority The priority level (LOW, MEDIUM, HIGH, URGENT)
     * @return The list of todos with the specified priority
     */
    @GetMapping("/priority/{priority}")
    @Operation(summary = "Retrieve todos by priority",
            description = "Filters todos by their priority level")
    @ApiResponse(responseCode = "200", description = "Filtered list retrieved successfully")
    public ResponseEntity<List<TodoDTO>> getTodosByPriority(
            @Parameter(description = "Priority level (LOW, MEDIUM, HIGH, URGENT)", required = true)
            @PathVariable Todo.Priority priority) {
        List<TodoDTO> todos = todoService.getTodosByPriority(priority);
        return ResponseEntity.ok(todos);
    }

    /**
     * Retrieves all overdue todos
     *
     * @return The list of incomplete todos past their due date
     */
    @GetMapping("/overdue")
    @Operation(summary = "Retrieve overdue todos",
            description = "Returns all incomplete todos past their due date")
    @ApiResponse(responseCode = "200", description = "List of overdue todos retrieved")
    public ResponseEntity<List<TodoDTO>> getOverdueTodos() {
        List<TodoDTO> todos = todoService.getOverdueTodos();
        return ResponseEntity.ok(todos);
    }

    /**
     * Searches todos by keyword
     *
     * @param keyword The keyword to search in title or description
     * @return The list of matching todos
     */
    @GetMapping("/search")
    @Operation(summary = "Search todos",
            description = "Searches todos by keyword in title or description")
    @ApiResponse(responseCode = "200", description = "Search results returned")
    public ResponseEntity<List<TodoDTO>> searchTodos(
            @Parameter(description = "Search keyword", required = true)
            @RequestParam String keyword) {
        List<TodoDTO> todos = todoService.searchTodos(keyword);
        return ResponseEntity.ok(todos);
    }

    /**
     * Toggles the completion status of a todo
     *
     * @param id The todo identifier
     * @return The todo with its status inverted
     */
    @PatchMapping("/{id}/toggle")
    @Operation(summary = "Toggle todo status",
            description = "Inverts the completion status of a todo (completed ↔ not completed)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status toggled successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<TodoDTO> toggleTodoStatus(
            @Parameter(description = "Todo identifier", required = true)
            @PathVariable Long id) {
        TodoDTO todo = todoService.toggleTodoStatus(id);
        return ResponseEntity.ok(todo);
    }
}