package com.restaurant.picker.apiservice.feign;

import com.restaurant.picker.apiservice.dto.RestaurantDTO;
import com.restaurant.picker.apiservice.model.SearchRestaurantRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "restaurant-service", url = "http://localhost:8080/api")
public interface RestaurantClient {

    @PostMapping(path = "/restaurants")
    List<RestaurantDTO> searchRestaurants(@RequestBody SearchRestaurantRequest searchRestaurantRequest);

    @GetMapping(path = "/restaurants/{id}")
    RestaurantDTO fetchRestaurant(@PathVariable String id);
}
