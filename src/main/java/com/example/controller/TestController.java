package com.example.controller;

import com.example.data.repository.FakeDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TestController {
    @GetMapping("test")
    public String noteList(Model model) {
        List<String> notes = Arrays.asList("Test 1", "Test 2", "Test 3");
        model.addAttribute("notes", notes);
        return "testTemplate";
    }
}

