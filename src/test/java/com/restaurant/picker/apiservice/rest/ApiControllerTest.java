package com.restaurant.picker.apiservice.rest;

import com.restaurant.picker.apiservice.dto.RestaurantVoteDTO;
import com.restaurant.picker.apiservice.feign.VotingClient;
import com.restaurant.picker.apiservice.model.RestaurantVoteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ApiControllerTest {

    @Autowired
    private ApiController apiController;

    @MockitoBean
    private VotingClient votingClient;

    @Test
    public void testSearchRestaurants_Success() {
        // arrange
        List<RestaurantVoteRequest> restaurantVoteRequests = List.of(
                RestaurantVoteRequest.builder().restaurantId("some-restaurant1").numberOfVotes(7L).build(),
                RestaurantVoteRequest.builder().restaurantId("some-restaurant2").numberOfVotes(4L).build(),
                RestaurantVoteRequest.builder().restaurantId("some-restaurant3").numberOfVotes(2L).build(),
                RestaurantVoteRequest.builder().restaurantId("some-restaurant4").numberOfVotes(8L).build()
        );
        when(votingClient.voteRestaurants(restaurantVoteRequests)).thenReturn(RestaurantVoteDTO.builder().restaurantId("some-restaurant4").numberOfVotes(8L).build());

        // act
        RestaurantVoteDTO result = apiController.voteRestaurants(restaurantVoteRequests).getBody();

        // assert
        assertThat(result).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo("some-restaurant4");
        assertThat(result.getNumberOfVotes()).isEqualTo(8);
    }

    @Test
    public void shouldReturnHighestRatingWhenNumOfVotesAreEqual() {

        // arrange
        List<RestaurantVoteRequest> restaurantVoteRequests = List.of(
                RestaurantVoteRequest.builder().restaurantId("some-restaurant1").numberOfVotes(2L).rating(4.6).build(),
                RestaurantVoteRequest.builder().restaurantId("some-restaurant2").numberOfVotes(2L).rating(4.1).build(),
                RestaurantVoteRequest.builder().restaurantId("some-restaurant3").numberOfVotes(2L).rating(4.0).build(),
                RestaurantVoteRequest.builder().restaurantId("some-restaurant4").numberOfVotes(2L).rating(4.5).build()
        );
        when(votingClient.voteRestaurants(restaurantVoteRequests)).thenReturn(RestaurantVoteDTO.builder().restaurantId("some-restaurant1").numberOfVotes(2L).rating(4.6).build());

        // act
        RestaurantVoteDTO response = apiController.voteRestaurants(restaurantVoteRequests).getBody();

        // assert
        assertThat(response).isNotNull();
        assertThat(response.getRestaurantId()).isEqualTo("some-restaurant1");
    }
}