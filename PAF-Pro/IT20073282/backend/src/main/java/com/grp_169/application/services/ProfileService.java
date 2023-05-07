package com.grp_169.application.services;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


import com.grp_169.application.model.FollowModel;
import com.grp_169.application.model.ProfileModel;
import com.grp_169.application.repository.FollowRepository;
import com.grp_169.application.repository.ProfileRepository;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    
    public List<ProfileModel> getall(){
        return profileRepository.findAll();
    }
   
    
    
}
