import express from "express";
import bodyParser from "body-parser";
import { homeController } from "./controllers/homeController.js";
import { createStateController } from "./controllers/createStateController.js";
import { getStateController } from "./controllers/getStateController.js";
import { deleteStateController } from "./controllers/deleteStateController.js";

const app = express();
const port = process.env.PORT || 3000;

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.use("/", homeController);
app.use("/order/get", getStateController);
app.use("/order/create", createStateController);
app.use("/order/delete", deleteStateController);

app.listen(port, () => console.log(`Server is listening on port ${port}`));
