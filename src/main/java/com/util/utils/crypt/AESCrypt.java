package com.util.utils.crypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.util.utils.conversion.ConversionSystemUtils;
import com.util.utils.crypt.exception.AESDecryptFailException;

/**
 * <p>AES加解密。</p>
 * @author xlp
 *
 */
public class AESCrypt {
	
	/**
	 * <p>AES加密。</p>
	 * @param data 被加密的数据。
	 * @param secretKey 解密的key。
	 * @return 解密后的字符串。
	 * @throws Exception
	 */
	public static String encrypt(String data, String secretKey) throws Exception{
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(secretKey.getBytes("UTF-8")));
		SecretKey key = kgen.generateKey();
		byte[] enCodeFormat = key.getEncoded();

		SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, Algorithm.AES);
		Cipher cipher = Cipher.getInstance(Algorithm.AES);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] b = cipher.doFinal(data.getBytes("UTF-8"));
		return ConversionSystemUtils.parseByte2HexStr(b);
	}
	
	/**
	 * <p>AES解密。</p>
	 * @param data 被解密的数据。
	 * @param secretKey 解密的key。
	 * @return
	 */
	public static String decrypt(String data, String secretKey) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(secretKey.getBytes()));
			SecretKey key = kgen.generateKey();
			byte[] enCodeFormat = key.getEncoded();

			SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, Algorithm.AES);
			Cipher cipher = Cipher.getInstance(Algorithm.AES);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			return new String(cipher.doFinal(ConversionSystemUtils.parseHexStr2Byte(data)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AESDecryptFailException(secretKey, data);
		}
	}


}
