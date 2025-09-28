package com.restaurant.picker.apiservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OpeningHourDTO {
    private boolean openNow;
    private List<String> weekdayDescriptions;
}
