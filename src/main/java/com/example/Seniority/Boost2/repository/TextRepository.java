package com.example.Seniority.Boost2.repository;

import com.example.Seniority.Boost2.entites.Text;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findByChars(int Chars);
    Page<Text> findAll(Pageable pageable);

    boolean existsByHash(String hash);

}
