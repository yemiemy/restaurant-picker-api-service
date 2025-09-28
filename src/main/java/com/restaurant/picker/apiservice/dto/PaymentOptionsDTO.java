package com.restaurant.picker.apiservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentOptionsDTO {
    private boolean acceptsCreditCards;
    private boolean acceptsCashOnly;
}
