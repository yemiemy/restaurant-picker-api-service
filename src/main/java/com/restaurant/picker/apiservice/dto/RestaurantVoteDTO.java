package com.restaurant.picker.apiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantVoteDTO {
    private String restaurantId;
    private Long numberOfVotes;
    private Double rating;
}
