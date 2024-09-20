package org.example.votes.service;

import org.example.votes.dto.input.VoteInputDto;
import org.example.votes.dto.output.VoteOutputDto;
import org.example.votes.models.Vote;
import org.example.votes.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.example.votes.dto.mapper.VoteMapper.*;
import static org.example.votes.dto.mapper.VoteMapper.voteFromModelToOutput;

@Service
public class VoteService {
    // Repository

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    // GetAll
    public List<VoteOutputDto> getAllVotes() {
        List<Vote> voteList = voteRepository.findAll();
        System.out.println("this is the votelist");
        return voteModelListToOutputList(voteList);
    }

    // Get ONE
    public VoteOutputDto getVoteByUsername(String username) {
        Optional<Vote> oVote = voteRepository.findVoteByUsername(username);
        if (oVote.isPresent()){
            return voteFromModelToOutput(oVote.get());
        }
        else throw new RuntimeException("Username not found.");
    }

    // Create

    public VoteOutputDto createVote (VoteInputDto voteInputDto, String username) {
        Optional<Vote> oVote = voteRepository.findVoteByUsername(username);
        if (oVote.isEmpty()) {
            Vote vote = voteRepository.save(voteFromInputDtoToModel(voteInputDto, username));
            return voteFromModelToOutput(vote);
        }
        else throw new RuntimeException("Username has already voted, please wait for the next time.");

    }
}
