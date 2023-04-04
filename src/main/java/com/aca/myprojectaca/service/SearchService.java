package com.aca.myprojectaca.service;

import com.aca.myprojectaca.dto.UserRequestAuthorDto;
import com.aca.myprojectaca.dto.UserRequestTitleDto;
import com.aca.myprojectaca.dto.UserResponseAuthorDto;
import com.aca.myprojectaca.dto.UserResponseTitleDto;
import com.aca.myprojectaca.entity.Author;
import com.aca.myprojectaca.entity.Song;
import com.aca.myprojectaca.repository.AuthorRepository;
import com.aca.myprojectaca.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchService {

    @Autowired
    SongRepository songRepository;
    @Autowired
    AuthorRepository authorRepository;

    public List<UserResponseAuthorDto> getSongs(UserRequestAuthorDto userRequestAuthorDto) {

        Author author = authorRepository.findByAuthorName(userRequestAuthorDto.getAuthorName()).orElseThrow(() ->
                new RuntimeException("No author with that name"));

        List<UserResponseAuthorDto> userResponseAuthorDtos = new ArrayList<>();
        List<Song> songs = songRepository.findAll().stream()
                .filter(song -> song.getAuthor().getId() == author.getId())
                .map(song -> {
                    UserResponseAuthorDto userResponseAuthorDto =
                            new UserResponseAuthorDto(song.getTitle(), song.getLyrics());
                    userResponseAuthorDtos.add(userResponseAuthorDto);
                    return song;
                })
                .collect(Collectors.toList());
        return userResponseAuthorDtos;
    }

    public List<UserResponseTitleDto> getLyricsAuthor(UserRequestTitleDto userRequestTitleDto) {

        Song song = songRepository.findByTitle(userRequestTitleDto.getTitle()).orElseThrow(() ->
                new RuntimeException("No title with that name"));

        List<UserResponseTitleDto> userResponseTitleDtos = new ArrayList<>();
        List<Song> songs = songRepository.findAll().stream()
                .filter(s -> s.getTitle() == song.getTitle())
                .map(s -> {
                    UserResponseTitleDto userResponseTitleDto =
                            new UserResponseTitleDto(s.getLyrics(),s.getAuthor().getAuthorName());
                    userResponseTitleDtos.add(userResponseTitleDto);
                    return s;
                })
                .collect(Collectors.toList());
        return userResponseTitleDtos;
    }
}
