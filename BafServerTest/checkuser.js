var https = require('https');

var qs = require('querystring');

var  MD5=function (data) {
   var _encrymd5 = require('crypto').createHash('md5');
   _encrymd5.update(data);
   return _encrymd5.digest('hex');
}

var post_data = "{'username': 'hujia'}";


var content = "content=" + post_data;
console.log(content);
process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0"
var options = {
    hostname: '10.68.37.213',
    port: 8443,
    path: '/BafServer/checkuser',
    method: 'POST',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded;'
    }
};

var req = https.request(options, function (res) {
    console.log('STATUS: ' + res.statusCode);
    console.log('HEADERS: ' + JSON.stringify(res.headers));
    res.setEncoding('utf8');
    res.on('data', function (chunk) {
        console.log('BODY: ' + chunk);
    });
});

req.on('error', function (e) {
    console.log('problem with request: ' + e.message);
});

// write data to request body
req.write(content);

req.end();
