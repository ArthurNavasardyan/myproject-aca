package com.aca.myprojectaca.controller;


import com.aca.myprojectaca.dto.AuthorCreateRequestDto;
import com.aca.myprojectaca.dto.AuthorResponseDto;
import com.aca.myprojectaca.service.AuthorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public AuthorResponseDto createAuthor(@RequestBody AuthorCreateRequestDto authorCreateRequestDto) {
        return authorService.createAuthor(authorCreateRequestDto);
    }
}


