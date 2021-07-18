// imports
import express from 'express';
import loginController from "../controllers/loginController.js";

// constants
const router = express.Router();


router.get('/login', loginController);


export default router;