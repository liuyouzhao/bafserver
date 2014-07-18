package com.hibernate.usage;

import java.util.Random;

public class PasswordCheckTest {
	
	public static boolean IS_RANDOM = true;
	
	
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	
	public static String randomPassword(int length) {
		return getRandomString(length);
	}
	static char[] password;
	public static char[] recuiserPass(int len_from, int cur) throws Exception {
		if(password == null)
			password = new char[len_from];
		
		for(int i = cur; i < len_from; i ++) {
			for(int j = 0; j < 128; j ++) {
				password[i] = (char) j;
				
				for(int n = 0; n < password.length; n ++) {
					System.out.print(((byte)password[n]) + " ");
				}
				System.out.println();
				String strPass = new String(password);
				boolean ok = CryptTest.check(strPass, strPass);
				if(!ok) {
					System.out.println("ERROR: " + strPass);
					return null;
				}
				
				if(cur < len_from - 1)
					recuiserPass(len_from, cur + 1);
			}
		}
		
		
		return password;
	}
	
	public static void main(String[] args) throws Exception {
		long start_time = 0;
		long current_time = 0;
		int len_from = 6;
		long counter = 0;
		while(true) {
			if(IS_RANDOM) {
				String pass = randomPassword(len_from);
				boolean ok = CryptTest.check(pass, pass);
				if(!ok) {
					System.out.println("ERROR: " + pass);
					break;
				}
				counter ++;
			}
			else {
				
				recuiserPass(len_from, 0);
				System.out.println("OVER!");
				break;
			}
			
			current_time = System.currentTimeMillis();
			
			if(start_time == 0 || current_time - start_time >= 10000) {
				if(start_time > 0)
					System.out.println("checking passed number: " + counter);
				start_time = current_time;
			}
		}
	}
}
