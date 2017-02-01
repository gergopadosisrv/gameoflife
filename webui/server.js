var express = require('express');
var serveStatic = require('serve-static');
var proxy = require('http-proxy-middleware');
const path = require('path');

var app = express();

var BACKEND = 'http://localhost:8080';


app.use('/api', proxy({target: BACKEND, changeOrigin: true}));
app.use('/static', express.static('./static'));

app.get('/*', function (req, res) {
  res.sendFile(__dirname + '/index.html')
});

app.listen(5005);
