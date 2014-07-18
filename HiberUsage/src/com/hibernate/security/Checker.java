package com.hibernate.security;

import java.util.Arrays;

public class Checker {
	
	private static int RANDOM_STR_LEN = 32;
	
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
