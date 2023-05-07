package com.grp_169.application.refactor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.grp_169.application.controller.FollowController;
import com.grp_169.application.model.FollowModel;

@Configuration
public class FollowRefactor  implements RepresentationModelAssembler<FollowModel, EntityModel<FollowModel>>{

    @Override
    public EntityModel<FollowModel> toModel(FollowModel followModel) {
        EntityModel<FollowModel> EntityFollowModel =EntityModel.of(followModel);
        
        EntityFollowModel.add(linkTo(methodOn(FollowController.class).getById(followModel.getId())).withSelfRel());
        EntityFollowModel.add(linkTo(methodOn(FollowController.class).update(followModel.getId(), null)).withRel("update"));
        EntityFollowModel.add(linkTo(methodOn(FollowController.class).changeDetail(followModel.getId(), null)).withRel("patch"));
        EntityFollowModel.add(linkTo(methodOn(FollowController.class).getAllRec()).withRel(IanaLinkRelations.COLLECTION));

        return EntityFollowModel;
    }
    
}
