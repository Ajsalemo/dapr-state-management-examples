import express from "express";
import { homeController } from "./controllers/homeController.js";
import { createStateController } from "./controllers/createStateController.js";
import { getStateController } from "./controllers/getStateController.js";

const app = express()
const port = process.env.APP_PORT || 3000;

app.use("/", homeController)
app.use("/order/get", getStateController)
app.use("/order/create", createStateController)

app.listen(port, () => console.log(`Server is listening on port ${port}`))
