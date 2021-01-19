package com.axsosplatform.axsosplatform.repository;

import com.axsosplatform.axsosplatform.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
    List<Tag> findALL();
}