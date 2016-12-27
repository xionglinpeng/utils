package com.util.utils.crypt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * <p>RSA算法。</p>
 * 在客户端可以使用jsencrypt.js进行匹配。
 * GitHup地址：https://github.com/travist/jsencrypt。
 * @author xlp
 *
 */
public class RSACrypt {
	
	public static final String PRIVATE_KEY = "PRIVATE_KEY";
	public static final String PUBLIC_KEY = "PUBLIC_KEY";
	
	
	/**
	 * <p>获取私钥以及公钥。</p>
	 * 公钥获取方式：<code>RSACrypt.getSecretKey().get(RSACrypt.PUBLIC_KEY);</code>
	 * 私钥获取方式：<code>RSACrypt.getSecretKey().get(RSACrypt.PRIVATE_KEY);</code>
	 * 每一次获取的公钥和私钥都会不同。
	 * @return 存储私钥以及公钥的Map集合,获取失败返回null。
	 */
	public static Map<String,Object> getSecretKey(){
		Map<String, Object> map;
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Algorithm.RSA);
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			map = new HashMap<>(2);
			map.put(PUBLIC_KEY, publicKey);
			map.put(PRIVATE_KEY, privateKey);
			return map;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * <p>使用公钥加密。</p>
	 * @param publicKey 公钥。
	 * @param data 被加密的数据。
	 * @return 加密后的字符串，如果加密失败，返回null。
	 */
	@SuppressWarnings("static-access")
	public static String encryptPublicKey(PublicKey publicKey,String data){
		try {
			X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey key = keyFactory.generatePublic(encodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(cipher.ENCRYPT_MODE, key);
			byte[] b = cipher.doFinal(data.getBytes());
			return Base64.encodeBase64String(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * <p>使用私钥解密。</p>
	 * @param privateKey 私钥。
	 * @param data 解密的数据。
	 * @return 解密后的数据，如果解密失败，返回null。
	 */
	public static String decryptPrivateKey(PrivateKey privateKey,String data){
		try {
			Cipher cipher = Cipher.getInstance(Algorithm.RSA);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return new String(cipher.doFinal(Base64.decodeBase64(data)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Map<String,Object> map = getSecretKey();
		PublicKey publicKey = (PublicKey) map.get(PUBLIC_KEY);
		PrivateKey privateKey = (PrivateKey) map.get(PRIVATE_KEY);
//		System.out.println(Base64.encodeBase64String(publicKey.getEncoded()));
//		System.out.println(Base64.encodeBase64String(privateKey.getEncoded()));
		System.out.println(encryptPublicKey(publicKey, "你好"));
		System.out.println(decryptPrivateKey(privateKey, encryptPublicKey(publicKey, "你好")));
	}
	
}
