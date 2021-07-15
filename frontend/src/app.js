import express from 'express';
import path from 'path';
import homeRouter from './api-routers/home.js';
import chatRouter from './api-routers/chat-service.js';
import { __dirname } from './common/constants.js';

const port = 3000;
const app = express();
const router = express.Router();

let userId = 'test';

app.use('', router);

// static
app.use("/script", express.static(path.join(__dirname, "script")))

// routers
router.use(homeRouter);
router.use(chatRouter);


app.listen(process.env.port || port);
console.log(`Running at Port ${port}`);