import * as express from "express";

const router = express.Router()

export const homeController = router.get("/", (_req, res) => {
    res.json({ msg: "dapr-state-management-examples-node-http" })
})