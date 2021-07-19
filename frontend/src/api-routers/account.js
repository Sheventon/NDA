// imports
import express from 'express';
import accountController from "../controllers/accountController.js";

// constants
const router = express.Router();


router.get('/account', accountController);


export default router;