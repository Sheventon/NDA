// imports
import {chatController} from "../controllers/chatController.js";
import express from "express";

// constants
const router = express.Router();

router.get('/messenger/chat/:id', chatController);


export default router;