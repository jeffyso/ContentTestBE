package com.example.contenttest.controller;

import com.example.contenttest.exception.ResourceNotFoundException;
import com.example.contenttest.model.Content;
import com.example.contenttest.repository.ContentRepository;
import com.example.contenttest.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;


    @PostMapping(value = "/addContent")
    public Content addContent(@Valid @RequestBody Content content) {
        return contentService.saveContent(content);
    }

    @GetMapping(value = "/getContent")
    public List<Content> getContent(){
        return contentService.getAllContent();
    }

    @GetMapping(value = "/getContentById/{id}")
    public Content getContentById(@PathVariable(name = "id") long id){
        return contentService.getContentById(id);
    }

    @PutMapping(value = "/updateContent")
    public Content updateContent(@RequestBody Content content){
        return contentService.updateContent(content);
    }

    @DeleteMapping(value = "/deleteContent/{id}")
    public String delete(@PathVariable(name = "id") long id) throws ResourceNotFoundException {
        return contentService.deleteContent(id);
    }
}
