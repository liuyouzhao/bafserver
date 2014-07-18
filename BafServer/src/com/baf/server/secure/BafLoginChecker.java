package com.baf.server.secure;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import com.baf.server.util.CryptUtil;

public class BafLoginChecker extends BafChecker {
	protected static String ACCESS_CHAR_COLLECT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
	
	protected static String ACCESS_NAME = "user_login_access";
	
	public static String getCurrentAccess(HttpSession session) {
		String access = (String) session.getAttribute(ACCESS_NAME);
		return access;
	}
	
	public static void invalidAccess(HttpSession session) {
		session.removeAttribute(ACCESS_NAME);
	}
	
	public static byte[] serverGiveAccess() throws Exception {
		String key = CryptUtil.initKey();
        byte[] c = CryptUtil.encrypt(ACCESS_CHAR_COLLECT.getBytes(), key);
        return c;
	}
	
	public static boolean checkPassword(byte[] clientPasswordKey, byte[] somethingFromClient, byte[] accessKeyAfterEncrypt) throws Exception {
		byte[] passKey = CryptUtil.encryptHMAC(clientPasswordKey, new String(accessKeyAfterEncrypt));
		byte[] result = CryptUtil.encryptHMAC(accessKeyAfterEncrypt, new String(passKey));

		if(Arrays.equals(somethingFromClient, result)) {
			return true;
		}
		return false;
	}
	
	public static String generateAccessKey() {
		String randStr = CryptUtil.getRandomString(RANDOM_STR_LEN);
		try {
			byte[] md5 = CryptUtil.encryptMD5(randStr.getBytes());
			return new String(md5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
