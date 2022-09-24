package com.example.contenttest.repository;

import com.example.contenttest.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository <Content, Long>{
}
