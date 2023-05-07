package com.grp_169.application.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="Follow")

public class FollowModel{
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column (name = "FollowerID")
    private String fid;

    private Integer isFollow;

    private Integer isFriend;



    public FollowModel() {
    }

    


    public FollowModel( String fid, Integer isFollow, Integer isFriend) {
        this.fid = fid;
        this.isFollow = isFollow;
        this.isFriend = isFriend;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Integer getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    public Integer getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }

    

    

}
