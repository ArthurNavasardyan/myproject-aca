package com.aca.myprojectaca.builder;

import com.aca.myprojectaca.dto.AuthorResponseDto;
import com.aca.myprojectaca.entity.Author;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorResponseBuilder {

    public AuthorResponseDto buildAuthorResponseDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setAuthorName(author.getAuthorName());
        authorResponseDto.setAuthorId(author.getId());
        authorResponseDto.setSongs(author
                .getSongs()
                .stream()
                .map(SongResponseBuilder::buildSongResponseDto)
                .collect(Collectors.toList()));
        return authorResponseDto;
    }
}
