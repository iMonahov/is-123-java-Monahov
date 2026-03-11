package com.example.is_123_java_Monahov.repository;

import com.example.is_123_java_Monahov.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
