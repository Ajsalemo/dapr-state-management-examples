import * as express from "express";
import { uuid } from "uuidv4";
import { stateUrl } from "../util/util.js";

const router = express.Router();

export const createStateController = router.post("/", async (req, res) => {
  try {
    const data = req.body.data;
    const orderId = req.body.orderId;
    console.log(`New order ID: ${orderId}`);

    const state = [
      {
        key: uuid(),
        value: data,
      },
    ];
    await fetch(stateUrl, {
      method: "POST",
      body: JSON.stringify(state),
      headers: {
        "Content-Type": "application/json",
      },
    });

    res.status(200).json({ msg: `Created order with Order ID: ${orderId}` });
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: error });
  }
});
