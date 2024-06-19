package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.entity.dto.AddWordDto;

import java.util.List;

public interface WordService {
    List<Word> findAllWords();

    boolean addWord(AddWordDto addWordDto);
}
