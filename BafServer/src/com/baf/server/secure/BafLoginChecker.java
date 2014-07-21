package com.baf.server.secure;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import com.baf.server.util.ArgumentUtil;
import com.baf.server.util.CryptUtil;

public class BafLoginChecker extends BafChecker {
	protected static String ACCESS_CHAR_COLLECT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
	
	protected static String ACCESS_NAME = "user_login_access";
	
	public static String getCurrentAccess(HttpSession session) {
		String access = (String) session.getAttribute(ACCESS_NAME);
		return access;
	}
	
	public static String generateAccessAndSave(HttpSession session) {
		try {
			String access = serverGiveAccess();
			session.setAttribute(ACCESS_NAME, access);
			return access;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void invalidAccess(HttpSession session) {
		session.removeAttribute(ACCESS_NAME);
	}
	
	public static String serverGiveAccess() throws Exception {
		String key = CryptUtil.initKey();
        byte[] c = CryptUtil.encrypt(ACCESS_CHAR_COLLECT.getBytes(), key);
        return ArgumentUtil.bytesToHexString(c);
	}
	
	public static boolean checkPassword(byte[] clientPasswordKey, byte[] somethingFromClient, byte[] accessKeyAfterEncrypt) throws Exception {
		///=============================
		System.out.println();
		System.out.print("bufMd5: ");
		for(byte p : clientPasswordKey) {
			System.out.print(String.format("%02x", p) + " ");
		}
		System.out.println();
		// ====================================
		String accessStr = new String(accessKeyAfterEncrypt);
		System.out.print("access: " + accessStr);
		byte[] passKey = CryptUtil.encryptHMAC(clientPasswordKey, accessStr);
		
		///=============================
		System.out.println();
		System.out.print("digest1: ");
		for(byte p : passKey) {
			System.out.print(String.format("%02x", p) + " ");
		}
		System.out.println();
		// ====================================
		byte[] result = CryptUtil.encryptHMAC(accessKeyAfterEncrypt, passKey);
		
		///=============================
		System.out.println();
		System.out.print("digest2: ");
		for(byte p : result) {
			System.out.print(String.format("%02x", p) + " ");
		}
		System.out.println();
		// ====================================
		
		String resStr = ArgumentUtil.bytesToHexString(result);
		String clientStr = new String(somethingFromClient);
		System.out.println("server: " + resStr + "  client: " + clientStr);
		if(resStr.equals(clientStr)) {
			return true;
		}
		return false;
	}
	
	public static String generateActivityKey() {
		String randStr = CryptUtil.getRandomString(RANDOM_STR_LEN);
		try {
			byte[] md5 = CryptUtil.encryptMD5(randStr.getBytes());
			return  ArgumentUtil.bytesToHexString(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
