package org.example.notesproject.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.notesproject.dtos.in.TodoItemInDTO;
import org.example.notesproject.mappers.TodoItemMapper;
import org.example.notesproject.models.Note;
import org.example.notesproject.models.TodoItem;
import org.example.notesproject.repository.NoteRepository;
import org.example.notesproject.repository.TodoItemRepository;
import org.example.notesproject.service.contracts.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {
    private final TodoItemMapper todoItemMapper;
    private final TodoItemRepository todoItemRepository;
    private final NoteRepository noteRepository;
    @Autowired
    TodoItemServiceImpl(TodoItemRepository todoItemRepository, TodoItemMapper todoItemMapper, NoteRepository noteRepository){
        this.todoItemRepository = todoItemRepository;
        this.todoItemMapper = todoItemMapper;
        this.noteRepository = noteRepository;
    }
    @Override
    public TodoItem create(TodoItemInDTO todoItemInDTO) {
        TodoItem item = todoItemMapper.fromDto(todoItemInDTO);
        if (todoItemInDTO.getNoteId() == null) {
            throw new IllegalArgumentException("Note must have an noteId");
        }
        Note note = noteRepository.findById(todoItemInDTO.getNoteId())
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
        item.setNote(note);
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
