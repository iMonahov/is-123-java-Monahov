package com.example.is_123_java_Monahov.repository;

import com.example.is_123_java_Monahov.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
