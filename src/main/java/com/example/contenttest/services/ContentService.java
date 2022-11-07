package com.example.contenttest.services;

import com.example.contenttest.exception.ResourceNotFoundException;
import com.example.contenttest.model.Content;
import com.example.contenttest.model.DeleteResponse;
import com.example.contenttest.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Content updateContent(Long id ,Content content ){
        Content findContent = contentRepository.findById(id).orElse(null);
        findContent.setTitle(content.getTitle());
        findContent.setContent(content.getContent());
        return contentRepository.save(findContent);
    }

    public DeleteResponse deleteContent(long id) throws ResourceNotFoundException {
        contentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        contentRepository.deleteById(id);
        return new DeleteResponse("Delete Success");
    }
}
