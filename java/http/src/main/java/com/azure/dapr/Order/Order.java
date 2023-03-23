package com.azure.dapr.Order;


import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    public UUID key;
    public Object data;
}
