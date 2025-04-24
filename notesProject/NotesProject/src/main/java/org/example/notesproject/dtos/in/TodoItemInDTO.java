package org.example.notesproject.dtos.in;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TodoItemInDTO {
    @Size(min = 2, max = 80, message = "Text should be between 2 and 80 characters long")
    private String text;
    private Boolean isDone;
    private LocalDateTime dueDate;
    private Integer ownerId;
    private Integer reminderId;

    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
