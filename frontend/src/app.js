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

// template config
app.set('views', path.join(__dirname, 'public'));
app.set('view engine', 'ejs');

// static
app.use(express.static(path.join(__dirname, "/script")));
app.use(express.static(path.join(__dirname, "/style")));
app.use(express.static(path.join(__dirname, "/assets")));

// routers
router.use(homeRouter);
router.use(chatRouter);


app.listen(process.env.port || port);
console.log(`Running at Port ${port}`);
