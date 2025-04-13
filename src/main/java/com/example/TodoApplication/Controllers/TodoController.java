package com.example.TodoApplication.Controllers;

import com.example.TodoApplication.Entity.Todo;
import com.example.TodoApplication.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller

public class TodoController {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // List todos for the logged-in user
    @RequestMapping("/todo-list")
    public String returnTodos(ModelMap model) {

        List<Todo> todos = todoRepository.findAll();
        model.put("todos", todos);
        return "ListTodos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoForm(ModelMap model) {
        model.put("todo", new Todo());
        return "addTodo";
    }


    // Add new todo (POST)
    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "addTodo";
        }
        todoRepository.save(todo);
        return "redirect:/todo-list";
    }

    // Delete todo
    @RequestMapping("/delete-todo")
    public String deleteTodo(@RequestParam Integer id) {
        todoRepository.deleteById(id);
        return "redirect:/todo-list";
    }

    // Show update form
    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoForm(@RequestParam Integer id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
         model.put("todo", todo);
        return "addTodo";
    }

    // Update todo (POST)
    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "addTodo";
        }
        todoRepository.save(todo);
        return "redirect:/todo-list";
    }
    @RequestMapping(value="/logout")
    public String logout() {
        return "redirect:/log";
    }
}
