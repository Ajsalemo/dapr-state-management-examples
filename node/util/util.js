const daprPort = process.env.DAPR_HTTP_PORT || 3500;
const stateStoreName = "statestore";

export const stateUrl = `http://localhost:${daprPort}/v1.0/state/${stateStoreName}`;