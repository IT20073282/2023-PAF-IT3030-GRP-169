package com.grp_169.application.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


import javax.security.auth.login.AccountNotFoundException;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grp_169.application.model.FollowModel;
import com.grp_169.application.refactor.FollowRefactor;
import com.grp_169.application.services.FollowServices;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/{pid}/follow")
public class FollowController {
    
    private FollowServices followServices;
    private FollowRefactor assembler;

    public FollowController(FollowServices followServices, FollowRefactor assembler) {
        this.followServices = followServices;
        this.assembler = assembler;
    }

    //Add Follower (Create part)
    @PostMapping
    public ResponseEntity<EntityModel<FollowModel>> add(@RequestBody FollowModel followModel){
        FollowModel addFollow = followServices.addFollower(followModel);

        EntityModel<FollowModel> entityModel = assembler.toModel(followModel);

        return ResponseEntity.created(linkTo(methodOn(FollowController.class).getById(addFollow.getId())).toUri()).body(entityModel);
    }


    //Check All (Read part)
    @GetMapping
    public  ResponseEntity<CollectionModel<EntityModel<FollowModel>>> getAllRec(){
        List<FollowModel> listFollowModel = followServices.getAll();

        if (listFollowModel.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<FollowModel>> collectionModel = assembler.toCollectionModel(listFollowModel);

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }


    //Check By ID (Read/ Search part)
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel <FollowModel>> getById(@PathVariable("id") Integer id){

        try{
            FollowModel followModel = followServices.getById(id);

            EntityModel<FollowModel> entityModel = assembler.toModel(followModel);

            return new ResponseEntity<>(entityModel, HttpStatus.OK);
            
        }catch(NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        
    }


    //Update follower by ID (Update part)
    @PutMapping ("/{id}/update")
    public ResponseEntity<EntityModel<FollowModel>> update(@PathVariable("id") Integer id, @RequestBody FollowModel followModel){
        FollowModel updateFollow = followServices.updateFollower(id, followModel);

        EntityModel<FollowModel> entityModel = assembler.toModel(updateFollow);  

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }


    
    //Partial Update by ID
    @PatchMapping("/{id}/patch")
    public ResponseEntity<EntityModel<FollowModel>> changeDetail(@PathVariable int id,@RequestBody Map<String, Object> fields){
        FollowModel updateByField =followServices.updateByField(id, fields);

        EntityModel<FollowModel> entityModel = assembler.toModel(updateByField);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);

    }

    //Delete Follower
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable ("id") Integer id){
        try{
            followServices.deleteFollwer(id);

            return ResponseEntity.noContent().build();
        }
        catch(AccountNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        
    }

    
}
