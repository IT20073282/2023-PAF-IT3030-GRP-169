package com.grp_169.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grp_169.application.model.ProfileModel;

public interface ProfileRepository extends JpaRepository <ProfileModel, Integer>{
    
}
