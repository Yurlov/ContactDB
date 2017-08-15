package com.viktor.yurlov.dao;

import com.viktor.yurlov.entity.Contact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;


/**
 * Created by Viktor on 15.08.2017.
 */
public interface ContactDAO {

    @Insert("INSERT INTO contacts (id,name) VALUES(Null,#{name})")
    int create(Contact entity);

    @Select("SELECT * FROM contacts")
    List<Contact> getAll();

    @Select("SELECT * FROM contacts WHERE id = #{id}")
    Contact findById(long id);

    @Select("SELECT * FROM contacts WHERE id  BETWEEN #{0} AND #{1}")
    List<Contact> selectionFromRange(long start, long finish);

    @Select("SELECT MAX(ID) FROM contacts")
    Long findMaxId();

}
