// imports
import express from 'express';
import signupController from "../controllers/signupController.js";

// constants
const router = express.Router();


router.get('/signup', signupController);


export default router;