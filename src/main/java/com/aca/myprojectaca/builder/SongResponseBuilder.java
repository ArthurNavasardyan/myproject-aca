package com.aca.myprojectaca.builder;

import com.aca.myprojectaca.dto.SongResponseDto;
import com.aca.myprojectaca.entity.Song;

public class SongResponseBuilder {

    public static SongResponseDto buildSongResponseDto(Song song){
        SongResponseDto songResponseDto = new SongResponseDto();
        songResponseDto.setSongId(song.getId());
        songResponseDto.setAuthorName(song.getAuthor().getAuthorName());
        songResponseDto.setLyrics(song.getLyrics());
        songResponseDto.setTitle(song.getTitle());
        return songResponseDto;
    }
}
