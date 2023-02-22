import * as express from "express";
import { stateUrl } from "../util/util.js";

const router = express.Router();

export const getStateController = router.get("/", async (_req, res) => {
  try {
    const response = await fetch(`${stateUrl}/order`);
    if (!response.ok) {
      throw "Could not get state.";
    }
    const orders = await response.json();
    res.json({ orders: orders });
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: error });
  }
});
