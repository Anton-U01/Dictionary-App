package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.entity.dto.AddWordDto;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.CurrentUser;
import com.dictionaryapp.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    public WordServiceImpl(WordRepository wordRepository, ModelMapper modelMapper, CurrentUser currentUser, LanguageRepository languageRepository, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Word> findAllWords() {
        return this.wordRepository.findAll();
    }

    @Override
    public boolean addWord(AddWordDto addWordDto) {
        Word word = modelMapper.map(addWordDto, Word.class);
        Optional<Language> optionalLanguage = languageRepository.findByLanguageName(LanguageEnum.valueOf(addWordDto.getLanguage()));
        if(optionalLanguage.isEmpty()){
            return false;
        }
        Language language = optionalLanguage.get();
        Optional<User> optionalUser = userRepository.findById(currentUser.getId());
        if(optionalUser.isEmpty()){
            return false;
        }
        User author = optionalUser.get();

        word.setAddedBy(author);
        word.setLanguage(language);
        wordRepository.saveAndFlush(word);

        return true;
    }
}
