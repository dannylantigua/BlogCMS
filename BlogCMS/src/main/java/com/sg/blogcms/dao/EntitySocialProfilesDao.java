/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.model.Entity;
import com.sg.blogcms.model.EntitySocialProfiles;
import java.util.List;

/**
 *
 * @author Kenny
 */
public interface EntitySocialProfilesDao {
   
    EntitySocialProfiles addEntitySocialProfiles(EntitySocialProfiles entitySocialProfiles);
   
    void deleteEntitySocialProfiles(EntitySocialProfiles entitySocialProfiles);
        
    void updateEntitySocialProfiles(EntitySocialProfiles entitySocialProfiles);
    
    EntitySocialProfiles getEntitySocialProfilesById(int id);
    
    List<EntitySocialProfiles> getAllEntitySocialProfiles();
    
}
