package com.matsta25.todosbackend.rest;

import com.matsta25.todosbackend.entity.Todo;
import com.matsta25.todosbackend.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoRestController {

    private TodoService todoService;

    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    public List<Todo> findAll() {
        return this.todoService.findAll();
    }

    @GetMapping("/todo/{todoId}")
    public Todo getTodo(@PathVariable int todoId) {
        Todo theTodo = this.todoService.findById(todoId);

        if (theTodo == null) {
            throw new RuntimeException("Did not find todo with id - " + todoId);
        }

        return theTodo;
    }

    @PostMapping("/todo")
    public Todo save(@RequestBody Todo theTodo) {
        theTodo.setId(0);
        this.todoService.save(theTodo);
        return theTodo;
    }

    @PutMapping("/todo")
    public Todo update(@RequestBody Todo theTodo){
        this.todoService.save(theTodo);
        return theTodo;
    }

    @DeleteMapping("/todo/{todoId}")
    public String deleteTodo(@PathVariable int todoId) {
        Todo tempTodo = this.todoService.findById(todoId);

        if(tempTodo == null){
            throw new RuntimeException("Did not find todo with id - " + todoId);
        }

        this.todoService.deleteById(todoId);

        return "todo with Id - " + todoId + " deleted";
    }

}
