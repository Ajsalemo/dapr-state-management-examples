import { DaprClient } from "@dapr/dapr";

const daprHost = "localhost";
const daprPort = process.env.DAPR_HTTP_PORT || 3500;

export const dapr = {
  client: new DaprClient(daprHost, daprPort),
  stateStoreName: "statestore",
};
