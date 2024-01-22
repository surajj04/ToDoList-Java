package com.todo.ToDoApp.controller;

import com.todo.ToDoApp.model.ToDo;
import com.todo.ToDoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ToDoController {
    @Autowired
    private ToDoService service;

    @GetMapping({"/", "viewToDoList"})
    public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", service.getAllToDoItems());
        model.addAttribute("message", message);

        return "ViewToDoList";
    }

    @GetMapping("/updateToDoStatus/{id}")
    public String updateToDoStatus(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (service.updateStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Update Success");
            return "redirect:/viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message", "Update Failure");
        return "redirect:/viewToDoList";
    }

    @GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new ToDo());
		
		return "AddToDoItem";
	}
    
    @PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(service.saveorUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			System.out.println("\n This todo: ");
			System.out.println(todo);
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addToDoItem";
	}

    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Integer id,Model model) {
        model.addAttribute("todo", service.getToDoItemById(id));
        return "EditToDoItem";
    }
    
    @PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(service.saveorUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editToDoItem/" + todo.getId();
	}

    @GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		if (service.deleteToDoItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/viewToDoList";
	}

}
