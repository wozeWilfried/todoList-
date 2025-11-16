package com.cwa.Todo_List.controllers;


import com.cwa.Todo_List.dto.CreateTodoRequest;
import com.cwa.Todo_List.dto.TodoDTO;
import com.cwa.Todo_List.dto.UpdateTodoRequest;
import com.cwa.Todo_List.entities.Todo;
import com.cwa.Todo_List.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.junit.jupiter.api.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Tag(name = "Todo Management", description = "APIs for managing todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    @Operation(summary = "Create a new todo", description = "Creates a new todo item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Todo created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<TodoDTO> createTodo(
            @Valid @RequestBody CreateTodoRequest request) {
        TodoDTO createdTodo = todoService.createTodo(request);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all todos", description = "Retrieves all todo items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved todos")
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get todo by ID", description = "Retrieves a specific todo by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo found"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<TodoDTO> getTodoById(
            @Parameter(description = "Todo ID", required = true)
            @PathVariable Long id) {
        TodoDTO todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a todo", description = "Updates an existing todo item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo updated successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<TodoDTO> updateTodo(
            @Parameter(description = "Todo ID", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateTodoRequest request) {
        TodoDTO updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a todo", description = "Deletes a todo item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Todo deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<Void> deleteTodo(
            @Parameter(description = "Todo ID", required = true)
            @PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{completed}")
    @Operation(summary = "Get todos by status", description = "Retrieves todos filtered by completion status")
    public ResponseEntity<List<TodoDTO>> getTodosByStatus(
            @Parameter(description = "Completion status", required = true)
            @PathVariable Boolean completed) {
        List<TodoDTO> todos = todoService.getTodosByStatus(completed);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/priority/{priority}")
    @Operation(summary = "Get todos by priority", description = "Retrieves todos filtered by priority level")
    public ResponseEntity<List<TodoDTO>> getTodosByPriority(
            @Parameter(description = "Priority level", required = true)
            @PathVariable Todo.Priority priority) {
        List<TodoDTO> todos = todoService.getTodosByPriority(priority);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/overdue")
    @Operation(summary = "Get overdue todos", description = "Retrieves all incomplete todos past their due date")
    public ResponseEntity<List<TodoDTO>> getOverdueTodos() {
        List<TodoDTO> todos = todoService.getOverdueTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/search")
    @Operation(summary = "Search todos", description = "Searches todos by keyword in title or description")
    public ResponseEntity<List<TodoDTO>> searchTodos(
            @Parameter(description = "Search keyword", required = true)
            @RequestParam String keyword) {
        List<TodoDTO> todos = todoService.searchTodos(keyword);
        return ResponseEntity.ok(todos);
    }

    @PatchMapping("/{id}/toggle")
    @Operation(summary = "Toggle todo status", description = "Toggles the completion status of a todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status toggled successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<TodoDTO> toggleTodoStatus(
            @Parameter(description = "Todo ID", required = true)
            @PathVariable Long id) {
        TodoDTO todo = todoService.toggleTodoStatus(id);
        return ResponseEntity.ok(todo);
    }
}