package com.example.controller;

import com.example.data.entity.NoteEntity;
import com.example.service.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NoteController {
    private static final String REDIRECT_TO_LIST = "redirect:/note/list";
    private NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("note/list")
    public ModelAndView getNoteList() {
        List<NoteEntity> noteList = noteService.listAll();
        ModelAndView modelAndView = new ModelAndView("note/getList");
        modelAndView.addObject("notes", noteList);
        return modelAndView;
    }

    @PostMapping("note/create")
    public String createNote(@Valid NoteEntity note, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("validationErrors", result.getAllErrors());
        } else {
            noteService.add(note);
        }
        return REDIRECT_TO_LIST;
    }


    @PostMapping("note/delete")
    public String deleteNote(@RequestParam("id") Long noteId) {
        noteService.deleteById(noteId);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("note/edit")
    public ModelAndView showEditNotePage(@RequestParam("id") Long noteId) {
        ModelAndView modelAndView = new ModelAndView("note/editPage");
        modelAndView.addObject("noteId", noteId);
        return modelAndView;
    }

    @PostMapping("note/edit")
    public String editNote(@ModelAttribute NoteEntity note) {
        noteService.update(note);
        return REDIRECT_TO_LIST;
    }
}
