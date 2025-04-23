package org.example.notesproject.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.notesproject.dtos.in.TodoItemInDTO;
import org.example.notesproject.mappers.TodoItemMapper;
import org.example.notesproject.models.TodoItem;
import org.example.notesproject.repository.TodoItemRepository;
import org.example.notesproject.service.contracts.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {
    private final TodoItemMapper todoItemMapper;
    private final TodoItemRepository todoItemRepository;
    @Autowired
    TodoItemServiceImpl(TodoItemRepository todoItemRepository, TodoItemMapper todoItemMapper){
        this.todoItemRepository = todoItemRepository;
        this.todoItemMapper = todoItemMapper;
    }
    @Override
    public TodoItem create(TodoItemInDTO todoItemInDTO) {
        TodoItem item = todoItemMapper.fromDto(todoItemInDTO);
        return todoItemRepository.save(item);
    }

    @Override
    public List<TodoItem> findAll() {
        return todoItemRepository.findAll();
    }

    @Override
    public TodoItem find(Integer id) {
        return todoItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TodoItem not found"));
    }

    @Override
    public TodoItem update(Integer id, TodoItemInDTO todoItemInDTO) {
        TodoItem item = find(id);
        todoItemMapper.updateDto(item, todoItemInDTO);
        return todoItemRepository.save(item);
    }

    @Override
    public void delete(Integer id) {
        todoItemRepository.deleteById(id);
    }
}
