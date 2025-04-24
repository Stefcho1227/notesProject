package org.example.notesproject.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.notesproject.dtos.in.ReminderInDTO;
import org.example.notesproject.mappers.ReminderMapper;
import org.example.notesproject.models.Note;
import org.example.notesproject.models.Reminder;
import org.example.notesproject.models.User;
import org.example.notesproject.repository.NoteRepository;
import org.example.notesproject.repository.ReminderRepository;
import org.example.notesproject.repository.UserRepository;
import org.example.notesproject.service.contracts.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReminderServiceImpl implements ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public ReminderServiceImpl(ReminderRepository reminderRepository, ReminderMapper reminderMapper,
                               UserRepository userRepository, NoteRepository noteRepository) {
        this.reminderRepository = reminderRepository;
        this.reminderMapper = reminderMapper;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }
    @Override
    public Reminder create(ReminderInDTO reminderInDTO) {
        Reminder createReminder = reminderMapper.fromDto(reminderInDTO);
        if (reminderInDTO.getCreatorId() == null) {
            throw new IllegalArgumentException("Note must have an creatorId");
        }
        User creator = userRepository.findById(reminderInDTO.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        createReminder.setCreator(creator);
        if (reminderInDTO.getNoteId() == null) {
            throw new IllegalArgumentException("Note must have an noteId");
        }
        Note note = noteRepository.findById(reminderInDTO.getNoteId())
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
        createReminder.setNote(note);
        return reminderRepository.save(createReminder);
    }

    @Override
    public Reminder update(Integer id, ReminderInDTO dto) {
        Reminder reminderToUpdate = find(id);
        reminderMapper.updateDto(reminderToUpdate, dto);
        return reminderRepository.save(reminderToUpdate);
    }

    @Override
    public Reminder find(Integer id) {
        return reminderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reminder not found"));
    }

    @Override
    public List<Reminder> findAll() {
        return reminderRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        reminderRepository.deleteById(id);
    }
}
