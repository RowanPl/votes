package org.example.votes.controller;


import org.example.votes.dto.input.VoteInputDto;
import org.example.votes.dto.output.VoteOutputDto;
import org.example.votes.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    //service
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    //mappings

    //get ALL
    @GetMapping
    public ResponseEntity<List<VoteOutputDto>> getAllVotes() {
        return ResponseEntity.ok(voteService.getAllVotes());
    }

    //get One
    @GetMapping("/{username}")
    public ResponseEntity<VoteOutputDto> getVoteByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(voteService.getVoteByUsername(username));
    }

    @PostMapping
    public ResponseEntity<VoteOutputDto> createVote(@RequestBody VoteInputDto voteInputDto, @AuthenticationPrincipal UserDetails userdetails){
        VoteOutputDto voteOutputDto = voteService.createVote(voteInputDto, userdetails.getUsername());
      //URI
        return ResponseEntity.created(null).body(voteOutputDto);
    }


}
