const router = require('express-promise-router')();

const {
  index,
  getUser,
  newUser,
  replaceUser,
  updateUser,
  deleteUser
} = require('../controllers/user');

router.get('/', index);
router.post('/', newUser);
router.get('/:userId', getUser);
router.put('/:userId', replaceUser);
router.patch('/:userId', updateUser);
router.delete('/:userId', deleteUser);

module.exports = router;
