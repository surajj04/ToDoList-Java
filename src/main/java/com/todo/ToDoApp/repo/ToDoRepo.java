package com.todo.ToDoApp.repo;

import com.todo.ToDoApp.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo,Integer> {

}
