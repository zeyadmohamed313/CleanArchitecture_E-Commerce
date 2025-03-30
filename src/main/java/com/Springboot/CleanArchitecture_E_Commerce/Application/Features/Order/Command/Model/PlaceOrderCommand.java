package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Order.Command.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceOrderCommand {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    private List<OrderItemRequest> items;
}