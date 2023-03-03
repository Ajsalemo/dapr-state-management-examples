import * as express from "express";
import { dapr } from "../util/util.js";
const router = express.Router();

export const getStateController = router.get("/:id", async (req, res) => {
  const orderId = req.params.id;

  try {
    const res = await dapr.client.state.get(dapr.stateStoreName, orderId);
    console.log(res)
    
    res.json({ orders: res });
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: error });
  }
});


