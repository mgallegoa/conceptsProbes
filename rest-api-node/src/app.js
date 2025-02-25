const bodyParser = require("body-parser");
const morgan = require("morgan");
const express = require("express");
const mongoose = require("mongoose");

const MONGO_CONNECTION_URI = process.env.MONGO_CONNECTION_URI;

const app = express();

const userRoutes = require("./routes/users");

//This db is hosted on claver-cloud
//"mongodb://ud7ptlvmm9xlaymbyn44:F3GvOYqA5pmvJ4yKxpwR@bqbgzm5svpnv0fg-mongodb.services.clever-cloud.com:27017/bqbgzm5svpnv0fg",
mongoose
  .connect(MONGO_CONNECTION_URI)
  .then((db) => console.log("db is connected"))
  .catch((err) => console.log(err));

// configurations
app.set("port", process.env.PORT || 3000);

// Middleware
app.use(morgan("dev"));
app.use(bodyParser.json());

// Routes
app.use("/users", userRoutes);

// Start server
app.listen(app.get("port"), () => {
  console.log("Server on port", app.get("port"));
});
