package com.azure.dapr.Entities.State;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class State {
    public State(UUID uuid, Object order) {
    }
    public UUID key;
    public Object value;
}
