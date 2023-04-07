import * as express from "express";
import { uuid } from "uuidv4";
import { dapr } from "../util/util.js";
const router = express.Router();

export const createStateController = router.post("/", async (req, res) => {
  try {
    const data = req.body.data;
    const orderId = data.orderId;

    const state = [
      {
        key: uuid(),
        value: data,
      },
    ];
    await dapr.client.state.save(dapr.stateStoreName, state);

    res.status(200).json({ msg: `Created order with Order ID: ${orderId}` });
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: error });
  }
});
