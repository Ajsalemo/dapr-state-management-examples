package com.azure.dapr.Entities.State;

import java.util.UUID;

import com.azure.dapr.Entities.Order.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class State {
    public UUID key;
    public Order data;
}
