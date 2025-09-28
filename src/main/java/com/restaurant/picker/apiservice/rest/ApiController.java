package com.restaurant.picker.apiservice.rest;

import com.restaurant.picker.apiservice.dto.RestaurantDTO;
import com.restaurant.picker.apiservice.dto.RestaurantVoteDTO;
import com.restaurant.picker.apiservice.feign.RestaurantClient;
import com.restaurant.picker.apiservice.feign.VotingClient;
import com.restaurant.picker.apiservice.model.RestaurantVoteRequest;
import com.restaurant.picker.apiservice.model.SearchRestaurantRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1")
public class ApiController {

    private final VotingClient votingClient;
    private final RestaurantClient restaurantClient;

    @PostMapping(path = "/restaurants")
    public ResponseEntity<List<RestaurantDTO>> searchRestaurants(@Valid @RequestBody SearchRestaurantRequest searchRestaurantRequest) {
        var restaurants = restaurantClient.searchRestaurants(searchRestaurantRequest);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping(path = "/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> fetchRestaurant(@Valid @PathVariable String id) {
        RestaurantDTO restaurant = restaurantClient.fetchRestaurant(id);
        if  (restaurant == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping(path = "/vote-restaurants")
    public ResponseEntity<RestaurantVoteDTO> voteRestaurants(@Valid @RequestBody List<RestaurantVoteRequest> request) {
        var restaurantVoteDTO = votingClient.voteRestaurants(request);
        return ResponseEntity.ok(restaurantVoteDTO);
    }
}
