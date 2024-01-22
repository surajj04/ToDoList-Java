package com.todo.ToDoApp.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.micrometer.common.lang.NonNull;

@Entity
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nonnull
    private Integer id;
    @Column
    @Nonnull
    private String title;
    @Column
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column
    @Nonnull
    private String status;

    public ToDo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
