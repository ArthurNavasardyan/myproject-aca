package com.aca.myprojectaca.controller;

import com.aca.myprojectaca.dto.*;
import com.aca.myprojectaca.repository.UserRepository;
import com.aca.myprojectaca.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestController {

    @Autowired
    SearchService searchService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    DeleteService deleteService;


    @GetMapping("/user/searchAuthor")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<UserResponseAuthorDto> getSongs(@RequestBody UserRequestAuthorDto userRequestAuthorDto){
        return searchService.getSongs(userRequestAuthorDto);
    }

    @GetMapping("/user/searchTitle")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<UserResponseTitleDto> getSongs(@RequestBody UserRequestTitleDto userRequestTitleDto){
        return searchService.getLyricsAuthor(userRequestTitleDto);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminApi(){return "Admin API";
    }

}
