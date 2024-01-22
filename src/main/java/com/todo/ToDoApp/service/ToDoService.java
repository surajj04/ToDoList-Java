package com.todo.ToDoApp.service;

import com.todo.ToDoApp.model.ToDo;
import com.todo.ToDoApp.repo.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {
    @Autowired
    ToDoRepo repo;

    public List<ToDo> getAllToDoItems() {
        ArrayList<ToDo> todoList = new ArrayList();
        repo.findAll().forEach(toDo -> todoList.add(toDo));
        return todoList;
    }

    public ToDo getToDoItemById(Integer id) {
        return repo.findById(id).get();
    }

    public boolean updateStatus(Integer id) {
        ToDo todo = getToDoItemById(id);
        todo.setStatus("Completed");
        return saveorUpdateToDoItem(todo);
    }

    public boolean saveorUpdateToDoItem(ToDo todo) {
        ToDo updateObj = repo.save(todo);
        if (getToDoItemById(updateObj.getId()) != null) {
            return true;
        }
        return false;

    }

    public boolean deleteToDoItem(Integer id) {
        repo.deleteById(id);
        if (repo.findById(id) != null) {
            return true;
        }
        return false;
    }


}
