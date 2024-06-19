package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Word;

import java.util.List;

public interface WordService {
    List<Word> findAllWords();
}
