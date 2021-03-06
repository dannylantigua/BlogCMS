/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.daoImpl;

import com.sg.blogcms.dao.TagsDao;
import com.sg.blogcms.mappers.Mappers.PostsMapper;
import com.sg.blogcms.mappers.Mappers.TagsMapper;
import com.sg.blogcms.model.Posts;
import com.sg.blogcms.model.postsTags;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author dannylantigua
 */
public class TagsDaoDbImpl implements TagsDao {

    // creating jdbc template and storing it
    JdbcTemplate jdbcTemplate;

    //using setter injection to set the template
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_GET_ALL_TAGS
            = "select * from PostsTags where postId = ";
    
    private static final String SQL_GET_10_TAGS
            = "select * from PostsTags limit 10";

    private static final String SQL_SAVE_TAGS
            = "insert into PostsTags (postId, Tag) values (?, ?)";
    
    private static final String SQL_REMOVE_TAGS
            = "delete from PostsTags where postId = ? and Tag = ?";

    private static final String SQL_GET_POSTS_BY_TAG
            = " SELECT * FROM Posts p JOIN postsTags pt ON p.recordId = pt.postId WHERE pt.postId = ";
    
    @Override
    public List<postsTags> getAllTagsByPosts(int postId) {
        final String newGetAllTagsSQL = SQL_GET_ALL_TAGS + postId;
        return jdbcTemplate.query(newGetAllTagsSQL, new TagsMapper());
    }
    
    @Override
    public List<Posts> getPostsByTag(int id){
        return jdbcTemplate.query(SQL_GET_POSTS_BY_TAG + id, new PostsMapper());
    }

    @Override
    public void savePostTags(List<postsTags> tags) {
        for (postsTags currentTag : tags) {
            jdbcTemplate.update(SQL_SAVE_TAGS,
                    currentTag.getPostId(),
                    currentTag.getTag());
        }
    }

    @Override
    public void removeTag(postsTags tag) {
        jdbcTemplate.update(SQL_REMOVE_TAGS, tag.getPostId(), tag.getTag());
    }

    @Override
    public List<postsTags> getLast10Tags() {
        return jdbcTemplate.query(SQL_GET_10_TAGS, new TagsMapper());
    }

}
