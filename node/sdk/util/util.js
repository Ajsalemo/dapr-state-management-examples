import { DaprClient } from "@dapr/dapr";

export const dapr = {
  client: new DaprClient(daprHost, daprPort),
  daprPort: process.env.DAPR_HTTP_PORT || 3500,
  stateStoreName: "statestore",
  daprHost: "127.0.0.1",
};
