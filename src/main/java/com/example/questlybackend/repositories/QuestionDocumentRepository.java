package com.example.questlybackend.repositories;

import com.example.questlybackend.models.QuestionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDocumentRepository extends ElasticsearchRepository<QuestionDocument, Long>{
    List<QuestionDocument> findByTitleContainingOrContentContaining(String title, String content);
}
