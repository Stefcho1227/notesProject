package org.example.notesproject.service.contracts;

import org.example.notesproject.dtos.in.TodoItemInDTO;
import org.example.notesproject.dtos.in.UserInDTO;
import org.example.notesproject.models.TodoItem;
import org.example.notesproject.models.User;

import java.util.List;

public interface TodoItemService {
    TodoItem create(TodoItemInDTO todoItemInDTO);
    List<TodoItem> findAll();
    TodoItem find(Integer id);
    TodoItem update(Integer id, TodoItemInDTO todoItemInDTO);
    void delete(Integer id);
}
