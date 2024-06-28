package org.example.votes.dto.mapper;

import org.example.votes.dto.input.VoteInputDto;
import org.example.votes.dto.output.VoteOutputDto;
import org.example.votes.models.Vote;

import java.util.ArrayList;
import java.util.List;

public class VoteMapper {

    //from dto to model
    public static Vote voteFromInputDtoToModel(VoteInputDto voteInputDto, String username){
        Vote vote = new Vote();
        vote.setPartij(voteInputDto.getPartij());
        vote.setUsername(username);
        return vote;
    }



    // from model to dto
    public static VoteOutputDto voteFromModelToOutput(Vote vote){
        VoteOutputDto voteOutputDto = new VoteOutputDto();
        voteOutputDto.setId(vote.getId());
        voteOutputDto.setUsername(vote.getUsername());
        voteOutputDto.setPartij(vote.getPartij());
        return voteOutputDto;
    }

    // from list to list
    public static List<VoteOutputDto> voteModelListToOutputList(List<Vote> votes){
        List<VoteOutputDto> voteOutputDtoList = new ArrayList<>();

//        for loop
//        for(Vote vote : votes){
//            voteOutputDtoList.add(voteFromModelToOutput(vote));
//        }

//        lambda
        votes.forEach( (vote) -> voteOutputDtoList.add(voteFromModelToOutput(vote)));
        return voteOutputDtoList;
     }



}
