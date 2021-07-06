package com.unurnment.chat.repository;

import com.unurnment.chat.model.Massage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MassageRepo extends CrudRepository<Massage,Long>{
    List<Massage> findByTagOrTextContaining(String tag,String text);
}
