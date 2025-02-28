const User = require("../models/user");

module.exports = {
  /*
   * index: function () {
    // callbacks method
    User.find({}, function(err, users) {

    });
    // Promise method
    User.find({})
       .then(function(users) {
         console.log(users);
       })
       .catch(function(errors) {
         console.log(errors);
       })
  },
  //Asyncronous method
  index: async function () {
    const users = await User.find({});
    res.json(users);
  },
  */
  index: async (req, res, next) => {
    const users = await User.find({});
    res.status(200).json(users);
  },

  newUser: async (req, res, next) => {
    const newUser = new User(req.body);
    const user = await newUser.save();
    res.status(200).json(user);
  },

  getUser: async (req, res, next) => {
    const { userId } = req.params;
    const user = await User.findById(userId);
    res.status(200).json(user);
  },

  //Method Put
  replaceUser: async (req, res) => {
    const { userId } = req.params;
    const newUser = req.body;
    const replacedUser = await User.findByIdAndUpdate(userId, newUser, {
      new: true,
    });
    res.status(200).json({ success: true, replacedUser });
  },

  // Method Patch
  updateUser: async (req, res) => {
    const { userId } = req.params;
    const newUser = req.body;
    const updatedUser = await User.findByIdAndUpdate(userId, newUser, {
      new: true,
    });
    res.status(200).json({ success: true, updatedUser });
  },

  deleteUser: async (req, res, next) => {
    const { userId } = req.params;
    const deletedUser = await User.findByIdAndDelete(userId);
    if (deletedUser) {
      res.status(200).json({ success: true });
    } else {
      res.status(200).json({ success: false });
    }
  },
};
