var https = require('https');

var username = 'linnvxia';
var password = 'LinFei860621';

var qs = require('querystring');
var crypto = require('crypto');

var  MD5=function (data) {
   var _encrymd5 = require('crypto').createHash('md5');
   _encrymd5.update(data);
   return _encrymd5.digest('hex');
}

var cookies = null;
var session = null;

process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0"

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


function access() {
    console.log(" ================================================================ ");
    var options = {
        hostname: '10.68.37.213',
        port: 8443,
        path: '/BafServer/genaccess',
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;',
            "Cookie":['JSESSIONID='+session]
        }
    };
    var req = https.request(options, function (res) {
        console.log('STATUS: ' + res.statusCode);
        console.log('HEADERS: ' + JSON.stringify(res.headers));
        //res.setEncoding('utf8');
        res.on('data', function (chunk) {
            console.log('BODY: ' + chunk);
	        var json = JSON.parse("" + chunk);
	        var access = json['data'];
            login(access, username, password);
        });
    });
    req.write("");
    req.end();
}

function clientGivePassKey(access, password) {
    var md5Pass = MD5(password);
    var bufMd5 = new Buffer(md5Pass);
    var b = new Buffer(access);
    var hmac1 = crypto.createHmac('md5', b);
    hmac1.update(bufMd5);
    var resKey = hmac1.digest();

    console.log("digest1: ",resKey);
    
    console.log("resKey: ", resKey.toString());
    
    
    
//    var hmac2 = crypto.createHmac('md5', resKey);
//    hmac2.update(b);
//    var sender = hmac2.digest();
    hmac1 = crypto.createHmac('md5', resKey);
    hmac1.update(b);
    var sender = hmac1.digest();
    
    console.log("digest2: ",sender);
    var result = "";
    for(var i = 0; i < sender.length; i ++) {
        var hex = sender[i].toString(16);
        hex = hex.length == 1 ? "0" + hex : hex;
        result += hex;
    }
    console.log("result: ", result);
    return result;
}


function login(access, username, password) {
    var options = {
        hostname:'10.68.37.213',
        port:8443,
	    path:'/BafServer/login',
	    method:'POST',
	    headers: {
		    'Content-Type':'application/x-www-form-urlencoded;',
		    "Cookie":['JSESSIONID='+session]
	    }
    }
    var rep = https.request(options, function(res) {
	        console.log('STATUS: ' + res.statusCode);
    		console.log('HEADERS: ' + JSON.stringify(res.headers));
   		    //res.setEncoding('utf8');
    		res.on('data', function (chunk) {
        		console.log('BODY: ' + chunk);
    		});
    });
    var fromClient = clientGivePassKey(access, password);
    rep.write("content={'username':'"+username+"','passkey':'"+fromClient+"','verycode':'hujianumone'}");
    rep.end();
}

gencode();

setTimeout(function(){access();}, 3000);

