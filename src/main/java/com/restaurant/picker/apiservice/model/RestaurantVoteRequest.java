package com.restaurant.picker.apiservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantVoteRequest {
    @NotBlank(message = "restaurantId is required")
    private String restaurantId;
    @NotNull(message = "numberOfVotes is required")
    private Long numberOfVotes;
    private Double rating;
}
