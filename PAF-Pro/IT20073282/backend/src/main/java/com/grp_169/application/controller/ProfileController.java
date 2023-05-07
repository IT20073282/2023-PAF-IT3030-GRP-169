package com.grp_169.application.controller;


import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grp_169.application.model.FollowModel;
import com.grp_169.application.model.ProfileModel;
import com.grp_169.application.refactor.FollowRefactor;
import com.grp_169.application.services.ProfileService;

@RestController
@RequestMapping ("/api/profile")
public class ProfileController {

    public ProfileService profileService;
    public FollowRefactor assembler;

    

    public ProfileController(ProfileService profileService, FollowRefactor followRefactor) {
        this.profileService = profileService;
        this.assembler = followRefactor;
    }



    @GetMapping
    public  List<ProfileModel> getAllRec(){
        return profileService.getall();

        
    }
    
}
