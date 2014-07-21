var https = require('https');
var qs = require('querystring');

var cookies = null;
var session = null;

var username = 'linnvxia';
var password = 'LinFei860621';
process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0"

var  MD5=function (data) {
   var _encrymd5 = require('crypto').createHash('md5');
   _encrymd5.update(data);
   return _encrymd5.digest('hex');
}

function gencode() {
    var options = {
        hostname: '10.68.37.213',
        port: 8443,
        path: '/BafServer/validatecode',
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;'
        }
    };
    var req = https.request(options, function (res) {
        console.log('STATUS: ' + res.statusCode);
        console.log('HEADERS: ' + JSON.stringify(res.headers));
        //res.setEncoding('utf8');
        res.on('data', function (chunk) {
            if(cookies == null) {
                cookies = res.headers['set-cookie'];
                var txt = cookies + "";
                var h = 'JSESSIONID=';
                var begin = txt.indexOf(h) + h.length;
                var end = txt.indexOf(';');     
                console.log(begin, end);
                session = txt.substring(begin, end);
                console.log("session: ", session);
            }           
        });
    });
    req.write("");
    req.end();
}

function register() {
    var content = "content={'username':'"+username+"','password':'"+MD5(password)+"','verycode':'hujianumone'}";
    
    var options = {
        hostname: '10.68.37.213',
        port: 8443,
        path: '/BafServer/register',
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;',
            "Cookie":['JSESSIONID='+session]
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
}

gencode();

setTimeout(function() {register();}, 3000);
