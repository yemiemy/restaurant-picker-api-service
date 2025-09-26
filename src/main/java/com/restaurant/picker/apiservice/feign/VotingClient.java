package com.restaurant.picker.apiservice.feign;

import com.restaurant.picker.apiservice.dto.RestaurantVoteDTO;
import com.restaurant.picker.apiservice.model.RestaurantVoteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "voting-service", url = "http://localhost:8081/api")
public interface VotingClient {

    @PostMapping("/vote-restaurants")
    RestaurantVoteDTO voteRestaurants(@RequestBody List<RestaurantVoteRequest> request);
}
