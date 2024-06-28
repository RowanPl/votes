package org.example.votes.repository;

import org.example.votes.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findVoteByUsername(String username);
}
