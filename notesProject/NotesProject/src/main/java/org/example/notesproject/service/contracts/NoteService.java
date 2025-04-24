package org.example.notesproject.service.contracts;

import org.example.notesproject.dtos.in.NoteInDTO;
import org.example.notesproject.dtos.in.UserInDTO;
import org.example.notesproject.models.Note;
import org.example.notesproject.models.User;

import java.util.List;

public interface NoteService {
    Note create(NoteInDTO noteInDTO);
    List<Note> findAll();
    Note find(Integer id);
    Note update(Integer id, NoteInDTO userInDTO);
    void delete(Integer id);
}
