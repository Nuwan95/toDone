package com.example.todo.controller;

import com.example.todo.entity.Task;
import com.example.todo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String viewTasks(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskService.getInCompletedTodayTasks());
        model.addAttribute("completedTasks", taskService.getCompletedTasks());
        return "index";
    }


    @PostMapping("/add-task")
    public String addTask(@ModelAttribute Task task) {
        System.out.println(task.getTaskDate());
        taskService.saveTask(task);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/"; // Redirect to the tasks list page
    }

    @PostMapping("/update-task-status/{id}")
    public String updateTaskStatus(@PathVariable Long id, @RequestParam Boolean completed) {

        taskService.updateTaskStatus(id, completed);
        return "redirect:/";
    }
}
