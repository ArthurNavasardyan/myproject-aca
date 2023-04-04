package com.aca.myprojectaca.service;

import com.aca.myprojectaca.builder.SongResponseBuilder;
import com.aca.myprojectaca.dto.SongCreateRequestDto;
import com.aca.myprojectaca.dto.SongResponseDto;
import com.aca.myprojectaca.entity.Author;
import com.aca.myprojectaca.entity.Song;
import com.aca.myprojectaca.repository.AuthorRepository;
import com.aca.myprojectaca.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SongService {

    private final AuthorRepository authorRepository;
    private  final SongRepository songRepository;
    public SongService(AuthorRepository authorRepository, SongRepository songRepository) {
        this.authorRepository = authorRepository;
        this.songRepository = songRepository;
    }


    public SongResponseDto createSong(SongCreateRequestDto songCreateRequestDto) {
        Optional<Author> author = authorRepository.findById(songCreateRequestDto.getAuthorId());
        if (author.isEmpty()){
            throw new RuntimeException("There is no existing author with that id");
        }
        Song song = new Song();
        song.setAuthor(author.get());
        song.setLyrics(songCreateRequestDto.getLyrics());
        song.setTitle(songCreateRequestDto.getTitle());
        Song saveSong = songRepository.save(song);
        return SongResponseBuilder.buildSongResponseDto(saveSong);
    }
}
