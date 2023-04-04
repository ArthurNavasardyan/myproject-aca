package com.aca.myprojectaca.controller;


import com.aca.myprojectaca.dto.SongCreateRequestDto;
import com.aca.myprojectaca.dto.SongResponseDto;
import com.aca.myprojectaca.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/song")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }
    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public SongResponseDto createSong(@RequestBody SongCreateRequestDto songCreateRequestDto){
        return songService.createSong(songCreateRequestDto);
    }
}
