package com.example.contenttest.services;

import com.example.contenttest.exception.ResourceNotFoundException;
import com.example.contenttest.model.Content;
import com.example.contenttest.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public Content saveContent(Content content) {
       return contentRepository.save(content);
    }

    public List<Content> getAllContent(){
        return contentRepository.findAll();
    }

    public Content getContentById(long id){
        return contentRepository.findById(id).orElse(null);
    }

    public Content updateContent(Content content){
        Content findContent = contentRepository.findById(content.getId()).orElse(null);
        findContent.setTitle(content.getTitle());
        findContent.setContent(content.getContent());
        return contentRepository.save(findContent);
    }

    public String deleteContent(long id) throws ResourceNotFoundException {
        contentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        contentRepository.deleteById(id);
        return "Delete Success";
    }
}
