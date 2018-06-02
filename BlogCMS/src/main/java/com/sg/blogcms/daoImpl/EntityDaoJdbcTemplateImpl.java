/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.daoImpl;

import com.sg.blogcms.dao.EntityDao;
import com.sg.blogcms.mappers.Mappers.EntityMapper;
import com.sg.blogcms.model.Entity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kmlnd
 */
public class EntityDaoJdbcTemplateImpl implements EntityDao {

    // creating jdbc template and storing it
    JdbcTemplate jdbcTemplate;

    //using setter injection to set the template
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // PREPARED STATEMENTS
    private static final String SQL_INSERT_ENTITY
            = " INSERT INTO Entity (FirstName, LastName, EMAIL , PhoneNumber,AboutMe,UserName,passwd,isAdmin , enabled) "
            + " VALUES ( ? , ? , ? , ? , ? , ? , ?, ? , 1)";

    private static final String SQL_SELECT_ALL_ENTITY
            = " SELECT * FROM Entity ";

    private static final String SQL_DELETE_BY_ID
            = " DELETE from Entity WHERE recordId = ?";

    private static final String SQL_SELECT_BY_ID
            = " SELECT * from Entity WHERE recordId = ?";

    private static final String SQL_SELECT_ALL_BY_EMAIL
            = " Select * FROM Entity where Email = ? ";

    private static final String SQL_SELECT_BY_PASSWORD
            = " SELECT * FROM Entity WHERE passwd = ? ";

    private static final String SQL_GET_ENTITY_BY_USERNAME
            = " select * from Entity where UserName = ?";

    /*=============================================================================
        AUTHORITY PREPARED STATEMENTS
    ==============================================================================*/
    private static final String SQL_INSERT_AUTHORITY
            = " INSERT INTO authorities (UserName , authority) values ( ? , ?)";

    private static final String SQL_DELETE_AUTHORITY
            = " delete from authorities where UserName = ? ";

    //Might have to refactor entity methods to include authorities
    @Override
    public Entity getEntityById(int entityId) {

        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new EntityMapper(), entityId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Entity getEntityByUserName(String username) {
        try {
        return jdbcTemplate.queryForObject(SQL_GET_ENTITY_BY_USERNAME, new EntityMapper(), username);
        } catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public void removeEntityById(int entityId) {
        Entity currentEntity = this.getEntityById(entityId);
        jdbcTemplate.update(SQL_DELETE_AUTHORITY, currentEntity.getUserName());
        jdbcTemplate.update(SQL_DELETE_BY_ID, entityId);
    }

    @Override
    public Entity updateEntityById(int entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity editEntity(Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entity> getAllEntities() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ENTITY, new EntityMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Entity addEntity(Entity entity) {
        jdbcTemplate.update(SQL_INSERT_ENTITY,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getAboutMe(),
                entity.getUserName(),
                entity.getPassword(),
                entity.isIsAdmin()
        );

        int entityId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        entity.setRecordId(entityId);

        // inserting the users roles
        ArrayList<String> authorities = entity.getAuthorities();

        for (String authortity : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, entity.getUserName(), authortity);
        }

        return null;
    }

    @Override
    public Entity getEntityByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ALL_BY_EMAIL, new EntityMapper(), email);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Entity getEntityByPassword(String password) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_PASSWORD, new EntityMapper(), password);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

}
