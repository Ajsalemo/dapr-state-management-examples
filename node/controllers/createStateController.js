import * as express from "express";
import { stateUrl } from "../util/util.js";

const router = express.Router();

export const createStateController = router.post("/", async (req, res) => {
  const data = req.body.data;
  const orderId = data.orderId;
  console.log(`New order ID: ${orderId}`);

  const state = [
    {
      key: "order",
      value: data,
    },
  ];

  try {
    const response = await fetch(stateUrl, {
      method: "POST",
      body: JSON.stringify(state),
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw "Failed to persist state.";
    }

    res.status(200).json({ msg: `Created order with Order ID: ${orderId}` });
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: error });
  }
});
