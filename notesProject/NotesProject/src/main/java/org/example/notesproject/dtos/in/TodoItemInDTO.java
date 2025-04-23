package org.example.notesproject.dtos.in;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TodoItemInDTO {
    @Size(min = 2, max = 80, message = "Text should be between 2 and 80 characters long")
    private String text;
    private Boolean isDone;
    private LocalDateTime dueDate;
    private Integer noteId;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
