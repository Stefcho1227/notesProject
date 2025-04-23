package org.example.notesproject.mappers;

import org.example.notesproject.dtos.in.NoteInDTO;
import org.example.notesproject.dtos.in.UserInDTO;
import org.example.notesproject.models.Note;
import org.example.notesproject.models.User;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public Note fromDto(NoteInDTO dto) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setIsPublic(dto.getIsPublic());
        return note;
    }
    public void updateDto(Note note, NoteInDTO dto){
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setIsPublic(dto.getIsPublic());
    }
}
