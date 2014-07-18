package com.hibernate.usage;

import java.math.BigInteger;
import java.util.Arrays;

import com.hibernate.security.CryptUtil;

public class CryptTest {
	public static String password = "WhatCanIDoForYou?YesYouCan@WhatDoyouWang?IWant985293$";
	public static String accessKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
	
	public static void main(String[] args) {

		try {
			byte[] clientPasswordKey = clientRegister();
			byte[] accessKey = serverGiveAccess();
			
			for(int i = 0; i < accessKey.length; i ++) {
				System.out.print(accessKey[i] + ",");
			}
			System.out.println();
			
			byte[] passFromClient = clientDo(accessKey, "WhatCanIDoForYou?YesYouCan@WhatDoyouWang?IWant985293$");
			
			for(int i = 0; i < passFromClient.length; i ++) {
				System.out.print(passFromClient[i] + ",");
			}
			System.out.println();
			
			
			boolean ok = serverCheckPassword(clientPasswordKey, passFromClient, accessKey);
			System.out.println(ok);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean check(String pswdRegister, String pswdLogin) throws Exception {
		byte[] clientPasswordKey = clientRegister(pswdRegister);
		byte[] accessKey = serverGiveAccess();
	
		byte[] passFromClient = clientDo(accessKey, pswdLogin);

		boolean ok = serverCheckPassword(clientPasswordKey, passFromClient, accessKey);
		return ok;
	}
	
	
	public static byte[] serverGiveAccess() throws Exception {
		String key = CryptUtil.initKey();
        byte[] c = CryptUtil.encrypt(accessKey.getBytes(), key);
        return c;
	}
	
	public static boolean serverCheckPassword(byte[] clientPasswordKey, byte[] somethingFromClient, byte[] accessKeyAfterEncrypt) throws Exception {
		byte[] passKey = CryptUtil.encryptHMAC(clientPasswordKey, new String(accessKeyAfterEncrypt));
		byte[] result = CryptUtil.encryptHMAC(accessKeyAfterEncrypt, new String(passKey));

		if(Arrays.equals(somethingFromClient, result)) {
			return true;
		}
		return false;
	}
	
	public static byte[] clientDo(byte[] accessKeyAfterEncrypt, String password) throws Exception {
		byte[] passMd5 = CryptUtil.encryptMD5(password.getBytes());
		byte[] passKey = CryptUtil.encryptHMAC(passMd5, new String(accessKeyAfterEncrypt));
		byte[] result = CryptUtil.encryptHMAC(accessKeyAfterEncrypt, new String(passKey));
		return result;
	}
	
	public static byte[] clientRegister() throws Exception {
		byte[] passMd5 = CryptUtil.encryptMD5(password.getBytes());
		return passMd5;
	}
	
	public static byte[] clientRegister(String password) throws Exception {
		byte[] passMd5 = CryptUtil.encryptMD5(password.getBytes());
		return passMd5;
	}
}
