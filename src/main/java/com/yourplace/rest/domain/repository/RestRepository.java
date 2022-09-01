package com.yourplace.rest.domain.repository;

import com.yourplace.rest.domain.entity.Rest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestRepository extends JpaRepository<Rest, Integer> {
    List<Rest> findByRestNameContaining(String keyword);
}
