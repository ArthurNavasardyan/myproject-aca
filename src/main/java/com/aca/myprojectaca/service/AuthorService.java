package com.aca.myprojectaca.service;

import com.aca.myprojectaca.builder.AuthorResponseBuilder;
import com.aca.myprojectaca.dto.AuthorCreateRequestDto;
import com.aca.myprojectaca.dto.AuthorResponseDto;
import com.aca.myprojectaca.entity.Author;
import com.aca.myprojectaca.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorResponseBuilder authorResponseBuilder;

    public AuthorService(AuthorRepository authorRepository, AuthorResponseBuilder authorResponseBuilder) {
        this.authorRepository = authorRepository;
        this.authorResponseBuilder = authorResponseBuilder;
    }

    public AuthorResponseDto createAuthor(AuthorCreateRequestDto authorCreateRequestDto) {
        authorRepository.findAll().stream().map(el -> {
            if (el.getAuthorName().equals(authorCreateRequestDto.getAuthorName())) {
                throw new RuntimeException("the author of this exists");
            }
            return el;
        }).collect(Collectors.toList());
        Author author = new Author();
        author.setAuthorName(authorCreateRequestDto.getAuthorName());
        author.setSongs(new ArrayList<>());
        Author saveAuthor = authorRepository.save(author);
        return authorResponseBuilder.buildAuthorResponseDto(saveAuthor);
    }
}
