import * as express from "express";
import { dapr } from "../util/util.js";
const router = express.Router();

export const getStateController = router.get("/:id", async (req, res) => {
  const orderId = req.params.id;

  try {
    const getState = await dapr.client.state.get(dapr.stateStoreName, orderId);
    const orders = getState.toString()
    
    res.json({ orders: orders });
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: error });
  }
});
