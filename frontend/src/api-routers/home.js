// imports
import homeController from "../controllers/homeController.js";
import express from 'express';

// constants
const router = express.Router();


router.get('/', homeController);


export default router;