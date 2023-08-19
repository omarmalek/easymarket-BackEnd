package com.smartweb.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartweb.market.model.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer> {

}
