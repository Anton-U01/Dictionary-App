package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> findAllWords() {
        return this.wordRepository.findAll();
    }
}
