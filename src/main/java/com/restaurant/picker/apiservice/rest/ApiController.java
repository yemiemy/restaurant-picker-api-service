package com.restaurant.picker.apiservice.rest;

import com.restaurant.picker.apiservice.dto.RestaurantVoteDTO;
import com.restaurant.picker.apiservice.feign.VotingClient;
import com.restaurant.picker.apiservice.model.RestaurantVoteRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1")
public class ApiController {

    private final VotingClient votingClient;

    @PostMapping(path = "/vote-restaurants")
    public ResponseEntity<RestaurantVoteDTO> voteRestaurants(@Valid @RequestBody List<RestaurantVoteRequest> request) {
        var restaurantVoteDTO = votingClient.voteRestaurants(request);
        return ResponseEntity.ok(restaurantVoteDTO);
    }
}
