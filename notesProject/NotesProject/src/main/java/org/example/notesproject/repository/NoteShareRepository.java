package org.example.notesproject.repository;

import org.example.notesproject.models.NoteShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteShareRepository extends JpaRepository<NoteShare, Integer> {
}
