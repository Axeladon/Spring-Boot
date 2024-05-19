package com.example.service.service;

import com.example.data.entity.Note;
import com.example.data.repository.FakeDatabase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private static final String EXCEPTION_TEXT = "Note does not found";
    private final FakeDatabase fakeDatabase;
    public NoteServiceImpl(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }
    @Override
    public List<Note> listAll() {
        return fakeDatabase.getAllNotes();
    }

    @Override
    public Note add(Note note) {
        return fakeDatabase.createNote(note);
    }

    @Override
    public void deleteById(long id) {
        if (!fakeDatabase.deleteById(id)) {
            throw new RuntimeException(EXCEPTION_TEXT);
        }
    }

    @Override
    public void update(Note note) {
        if (!fakeDatabase.update(note)) {
            throw new RuntimeException(EXCEPTION_TEXT);
        }
    }

    @Override
    public Note getById(long id) {
        Note note = fakeDatabase.getById(id);
        if (note == null) {
            throw new RuntimeException(EXCEPTION_TEXT);
        } else {
            return note;
        }
    }
}
