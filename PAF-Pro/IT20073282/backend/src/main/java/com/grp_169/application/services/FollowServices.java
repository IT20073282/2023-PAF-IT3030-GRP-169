package com.grp_169.application.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.grp_169.application.model.FollowModel;
import com.grp_169.application.repository.FollowRepository;

@Service
public class FollowServices {
    
    private FollowRepository followRepository;

    public FollowServices(FollowRepository followRepository){
        this.followRepository = followRepository;
    }

    //Adding new Follower
    public FollowModel addFollower(FollowModel followModel){
        return followRepository.save(followModel);
    }

    //View all Followers
    public List<FollowModel> getAll(){
        return followRepository.findAll();
    }

    //View Follower by ID
    public FollowModel getById(Integer id){
        return followRepository.findById(id).get();
    }

    //Updating all fields of Follower
    public FollowModel updateFollower(Integer id, FollowModel followModel) {

        FollowModel updateFollowModel = followRepository.findById(id).get(); // DB
        
        updateFollowModel.setFid(followModel.getFid());
        updateFollowModel.setIsFollow(followModel.getIsFollow());
        updateFollowModel.setIsFriend(followModel.getIsFriend());
        
        return followRepository.save(updateFollowModel);
    }


    //Updating requested fields of Follower
    public FollowModel updateByField(Integer id, Map<String, Object> data){

        Optional<FollowModel> findFollower = followRepository.findById(id);

        if(findFollower.isPresent()){
            data.forEach((key, value) -> {
                Field f = ReflectionUtils.findField(FollowModel.class, key);
                f.setAccessible(true);
                ReflectionUtils.setField(f, findFollower.get(), Integer.parseInt( (String) value));
            });
            return followRepository.save(findFollower.get());
        }
        System.out.println(data);
        return null; 
        
    }

    
    //Delete a Follower
    public void deleteFollwer(Integer id) throws AccountNotFoundException{
        if (!followRepository.existsById(id)){
            throw new AccountNotFoundException();
            
        }
        followRepository.deleteById(id);
    }

    
}
