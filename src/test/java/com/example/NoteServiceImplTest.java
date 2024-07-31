package com.example;

import com.example.data.entity.NoteEntity;
import com.example.data.repository.NoteRepository;
import com.example.service.service.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class NoteServiceImplTest {

    @Autowired
    private NoteServiceImpl noteService;

    @Autowired
    private NoteRepository noteRepository;

    private NoteEntity note1;
    private NoteEntity note2;
    private NoteEntity note3;

    @BeforeEach
    public void setUp() {
        noteRepository.deleteAll();

        note1 = noteService.add(new NoteEntity("John", "Meeting notes"));
        note2 = noteService.add(new NoteEntity("Alice", "Task list"));
        note3 = noteService.add(new NoteEntity("Bob", "Project ideas"));
    }

    @Test
    void testAdd() {
        NoteEntity noteToAdd = new NoteEntity("Albert", "Home work");
        NoteEntity addedNote = noteService.add(noteToAdd);
        assertNotNull(addedNote.getId());
        assertEquals(noteToAdd.getTitle(), addedNote.getTitle());
        assertEquals(noteToAdd.getContent(), addedNote.getContent());
    }

    @Test
    void testListAll() {
        List<NoteEntity> allNotes = noteService.listAll();
        assertEquals(3, allNotes.size());
    }

    @Test
    void testDeleteByIdShouldThrowNotFoundException() {
        assertThrows(RuntimeException.class, () -> noteService.deleteById(999L));
    }

    @Test
    void testDeleteById() {
        List<NoteEntity> allNotesBeforeDelete = noteService.listAll();
        assertEquals(3, allNotesBeforeDelete.size());

        noteService.deleteById(note2.getId());

        List<NoteEntity> allNotesAfterDelete = noteService.listAll();
        assertEquals(2, allNotesAfterDelete.size());
    }

    @Test
    void testUpdateShouldThrowNotFoundException() {
        NoteEntity noteToUpdate = new NoteEntity("Albert", "Home work");
        noteToUpdate.setId(999L); //set a non-existent ID
        assertThrows(RuntimeException.class, () -> noteService.update(noteToUpdate));
    }

    @Test
    void testUpdate() {
        NoteEntity noteToUpdate = noteService.getById(note2.getId());
        noteToUpdate.setContent("New content");
        noteService.update(noteToUpdate);
        assertEquals("New content", noteService.getById(note2.getId()).getContent());
    }

    @Test
    void testGetByIdShouldThrowNotFoundException() {
        assertThrows(RuntimeException.class, () -> noteService.getById(999L));
    }

    @Test
    void testGetById() {
        NoteEntity note = noteService.getById(note2.getId());
        assertEquals(note2.getId(), note.getId());
    }
}
