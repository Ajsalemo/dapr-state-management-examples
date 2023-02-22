import * as express from "express";
import { stateUrl } from "../util/util.js";

const router = express.Router();

export const getStateController = router.get("/:id", async (req, res) => {
  const orderId = req.params.id;

  try {
    const response = await fetch(`${stateUrl}/${orderId}`);
    const orders = await response.json();
    
    res.json({ orders: orders });
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: error });
  }
});
