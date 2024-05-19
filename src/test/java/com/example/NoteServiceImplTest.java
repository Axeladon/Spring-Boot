package com.example;

import com.example.data.entity.Note;
import com.example.data.repository.FakeDatabase;
import com.example.service.service.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceImplTest {
    private NoteServiceImpl noteService;

    @BeforeEach
    public void setUp() {
        FakeDatabase fakeDatabase = new FakeDatabase();
        noteService = new NoteServiceImpl(fakeDatabase);

        noteService.add(new Note("John", "Meeting notes"));
        noteService.add(new Note("Alice", "Task list"));
        noteService.add(new Note("Bob", "Project ideas"));
    }

    @Test
    void testAdd() {
        Note noteToAdd = new Note("Albert", "Home work");
        Note addedNote = noteService.add(noteToAdd);
        assertEquals(noteToAdd, addedNote);
    }

    @Test
    void testListAll() {
        List<Note> allNotes = noteService.listAll();
        assertEquals(3, allNotes.size());
    }

    @Test
    void testDeleteByIdShouldThrowsNotFoundException() {
        assertThrows(RuntimeException.class, () -> noteService.deleteById(4));
    }

    @Test
    void testDeleteById() {
        List<Note> allNotesBeforeDelete = noteService.listAll();
        assertEquals(3, allNotesBeforeDelete.size());

        noteService.deleteById(2);

        List<Note> allNotesAfterDelete = noteService.listAll();
        assertEquals(2, allNotesAfterDelete.size());
    }

    @Test
    void testUpdateShouldThrowsNotFoundException() {
        Note noteToUpdate = new Note("Albert", "Home work");
        assertThrows(RuntimeException.class, () -> noteService.update(noteToUpdate));
    }

    @Test
    void testUpdate() {
        Note noteToUpdate = noteService.getById(2);
        noteToUpdate.setContent("New context");
        noteService.update(noteToUpdate);
        assertEquals(noteService.getById(2), noteToUpdate);
    }

    @Test
    void testGetByIdShouldThrowsNotFoundException() {
        assertThrows(RuntimeException.class, () -> noteService.getById(4));
    }

    @Test
    void testGetById() {
        Note node = noteService.getById(2);
        assertEquals(noteService.listAll().get(1), node);
    }
}
