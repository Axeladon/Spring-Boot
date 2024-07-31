package com.example.service.service;

import com.example.data.entity.NoteEntity;
import com.example.data.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired private NoteRepository noteRepository;

    private static final String EXCEPTION_TEXT = "Note does not found";
    @Override
    public List<NoteEntity> listAll() {
        return noteRepository.findAll();
    }

    @Override
    public NoteEntity add(NoteEntity note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException(EXCEPTION_TEXT);
        }
        noteRepository.deleteById(id);
    }

    @Override
    public void update(NoteEntity note) {
        if (!noteRepository.existsById(note.getId())) {
            throw new RuntimeException(EXCEPTION_TEXT);
        }
        noteRepository.save(note);
    }

    @Override
    public NoteEntity getById(long id) {
        Optional<NoteEntity> note = noteRepository.findById(id);
        if (note.isPresent()) {
            return note.get();
        } else {
            throw new RuntimeException(EXCEPTION_TEXT);
        }
    }
}
