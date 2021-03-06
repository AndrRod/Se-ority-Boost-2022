package com.example.Seniority.Boost2.repository;

import com.example.Seniority.Boost2.entity.Text;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
    Page<Text> findAll(Pageable pageable);
    @Query("SELECT t FROM Text t WHERE t.chars = :chars")
    Page<Text> findAllByPageableAndChars(Pageable pageable, int chars);
    List<Text> findByText(String text);
}
