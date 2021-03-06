/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.service;

import com.sg.blogcms.model.Entity;
import java.util.List;

/**
 *
 * @author kmlnd
 */
public interface EntityService {

    Entity addEntity(Entity entity);

    Entity getEntityById(int entityId);

    void removeEntityById(int entityId);

    void updateEntity(Entity e);

    Entity editEntity(Entity entity);

    List<Entity> getAllEntities();

    Entity getEntityByEmail(String email);

    Entity getEntityByPassword(String password);
    
    Entity getEntityByUserName(String username);
}
