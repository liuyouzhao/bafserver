package com.hibernate.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <ul>
 * <li>BASE64�ļ��ܽ�����˫��ģ������󷴽⡣</li>
 * <li>MD5��SHA�Լ�HMAC�ǵ�����ܣ��κ����ݼ��ܺ�ֻ�����Ψһ��һ�����ܴ���ͨ������У�������ڴ���������Ƿ��޸ġ�</li>
 * <li>HMAC�㷨��һ����Կ����ǿ�����ݴ�������еİ�ȫ�ԣ�ǿ�����㷨��Ĳ��ɿ����ء�</li>
 * <li>DES DES-Data Encryption Standard,�����ݼ����㷨��
 * DES�㷨����ڲ���������:Key��Data��Mode��
 * <ul>
 * <li>Key:8���ֽڹ�64λ,��DES�㷨�Ĺ�����Կ;</li>
 * <li>Data:8���ֽ�64λ,��Ҫ�����ܻ򱻽��ܵ�����;</li>
 * <li>Mode:DES�Ĺ�����ʽ,������:���ܻ���ܡ�</li>
 * </ul>
 * </li>
 * <ul>
 * 
 * @author Ice_Liu
 * 
 */
public class CryptUtil {
    private static final String KEY_MD5 = "MD5";
    private static final String KEY_SHA = "SHA";
    /**
     * MAC�㷨��ѡ���¶����㷨
     * 
     * <pre>
     *  
     * HmacMD5  
     * HmacSHA1  
     * HmacSHA256  
     * HmacSHA384  
     * HmacSHA512 
     * </pre>
*/
    public static final String KEY_MAC = "HmacSHA1";

    /**
     * BASE64����
     * 
     * @param key
     * @return
     * @throws Exception
*/
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64 ����
     * 
     * @param key
     * @return
     * @throws Exception
*/
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5����
     * 
     * @param data
     * @return
     * @throws Exception
*/
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }

    /**
     * SHA����
     * 
     * @param data
     * @return
     * @throws Exception
*/
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    /**
     * ��ʼ��HMAC��Կ
     * 
     * @return
     * @throws Exception
*/
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC ����
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    /**
     * DES �㷨 <br>
     * ���滻Ϊ��������һ���㷨��ͬʱkeyֵ��size��Ӧ�ı䡣
     * 
     * <pre>
     * DES                  key size must be equal to 56 
     * DESede(TripleDES)    key size must be equal to 112 or 168 
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
     * RC2                  key size must be between 40 and 1024 bits 
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits 
     * </pre>
*/
    public static final String ALGORITHM = "DES";

    /**
     * DES �㷨ת����Կ<br>
     * 
     * @param key
     * @return
     * @throws Exception
*/
    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = null;
        if (ALGORITHM.equals("DES") || ALGORITHM.equals("DESede")) {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            secretKey = keyFactory.generateSecret(dks);
        } else {
            // ��ʹ�������ԳƼ����㷨ʱ����AES��Blowfish���㷨ʱ�������������滻�������д���
            secretKey = new SecretKeySpec(key, ALGORITHM);
        }
        return secretKey;
    }

    /**
     * DES �㷨����
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * DES �㷨����
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
*/
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * DES �㷨������Կ
     * 
     * @return
     * @throws Exception
*/
    public static String initKey() throws Exception {
        return initKey(null);
    }

    /**
     * DES �㷨������Կ
     * 
     * @param seed
     * @return
     * @throws Exception
*/
    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);
        SecretKey secretKey = kg.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    public static void main(String[] args) {
        try {
            String s = "�����ŵĸ���";
            String b = CryptUtil.encryptBASE64(s.getBytes("UTF-8"));
            System.out.println("BASE64���ܺ�:" + b);
            byte[] c = CryptUtil.decryptBASE64(b);
            System.out.println("BASE64���ܺ�:" + new String(c, "UTF-8"));

            c = encryptMD5(s.getBytes());
            System.out.println("MD5   ���ܺ�:" + new BigInteger(c).toString(16));

            c = encryptSHA(s.getBytes());
            System.out.println("SHA   ���ܺ�:" + new BigInteger(c).toString(16));

            String key = initMacKey();
            System.out.println("HMAC�ܳ�:" + key);
            c = encryptHMAC(s.getBytes(), key);
            System.out.println("HMAC  ���ܺ�:" + new BigInteger(c).toString(16));

            key = initKey();
            System.out.println(ALGORITHM + "��Կ:\t" + key);
            c = encrypt(s.getBytes("UTF-8"), key);
            System.out.println(ALGORITHM + "   ���ܺ�:" + new BigInteger(c).toString(16));
            c = decrypt(c, key);
            System.out.println(ALGORITHM + "   ���ܺ�:" + new String(c, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
	public static String getRandomString(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}