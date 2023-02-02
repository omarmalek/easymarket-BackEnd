package com.smartweb.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartweb.market.model.Catgory;

@Repository
public interface CatgoryRepository extends JpaRepository<Catgory, Integer> {

}
