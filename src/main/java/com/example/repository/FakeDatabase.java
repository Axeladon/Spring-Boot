package com.example.repository;

import com.example.entity.Note;
import com.example.util.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FakeDatabase {
    private final IdGenerator idGenerator = new IdGenerator();
    private final List<Note> notes = new ArrayList<>();

    public Note createNote(Note note) {
        note.setId(idGenerator.getNextId());
        notes.add(note);
        return note;
    }

    public List<Note> getAllNotes() {
        return notes;
    }

    public boolean deleteById(long id) {
        return notes.removeIf(note -> note.getId() == id);
    }

    public boolean update(Note note) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(note.getId())) {
                notes.set(i, note);
                return true;
            }
        }
        return false;
    }

    public Note getById(long id) {
        Optional<Note> optionalNote = notes.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst();
        return optionalNote.orElse(null);
    }
}

