import * as express from "express";
import { dapr } from "../util/util.js";

const router = express.Router();

export const deleteStateController = router.delete("/:id", async (req, res) => {
  const orderId = req.params.id;
  try {
    await dapr.client.state.delete(dapr.stateStoreName, orderId);

    res.status(200).json({ msg: `Deleted order with Order ID: ${orderId}` });
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: error });
  }
});
