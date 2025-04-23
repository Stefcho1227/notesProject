package org.example.notesproject.mappers;

import org.example.notesproject.dtos.in.NoteInDTO;
import org.example.notesproject.dtos.in.TodoItemInDTO;
import org.example.notesproject.models.Note;
import org.example.notesproject.models.TodoItem;
import org.springframework.stereotype.Component;

@Component
public class TodoItemMapper {
    public TodoItem fromDto(TodoItemInDTO dto) {
        TodoItem todoItem = new TodoItem();
        todoItem.setText(dto.getText());
        if(dto.getIsDone() == null){
            todoItem.setIsDone(false);
        } else{
            todoItem.setIsDone(dto.getIsDone());
        }
        todoItem.setDueDate(dto.getDueDate());

        return todoItem;
    }
    public void updateDto(TodoItem todoItem, TodoItemInDTO dto){
        if(dto.getText() != null){
            todoItem.setText(dto.getText());
        }
        if(dto.getIsDone() != null){
            todoItem.setIsDone(dto.getIsDone());
        }
        if(dto.getDueDate() != null){
            todoItem.setDueDate(dto.getDueDate());
        }
    }
}
