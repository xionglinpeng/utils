package com.util.utils.crypt;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
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
 * 注意：如果使用的是公钥加密，则必须使用私钥解密，反之，如果使用的是私钥加密，则必须使用公钥解密。
 * @author xlp
 *
 */
public class RSACrypt {
	
	/**
	 * <p>获取私钥的Map key值：{@value}。</p>
	 */
	public static final String PRIVATE_KEY = "PRIVATE_KEY";
	
	/**
	 * <p>获取公钥的Map key值：{@value}。</p>
	 */
	public static final String PUBLIC_KEY = "PUBLIC_KEY";
	
	/**
	 * <P>RSA算法字节长度：{@value}。</P>
	 */
	private static final int BYTE_LENGTH = 1024;
	
	/**
	 * <p>获取私钥以及公钥。</p>
	 * 公钥获取方式：<code>RSACrypt.getSecretKey().get(RSACrypt.PUBLIC_KEY);</code>
	 * 私钥获取方式：<code>RSACrypt.getSecretKey().get(RSACrypt.PRIVATE_KEY);</code>
	 * 每一次获取的公钥和私钥都会不同。
	 * @return 存储私钥以及公钥的Map集合,获取失败返回null。
	 * @throws Exception 
	 */
	public static Map<String,Key> getSecretKey() throws Exception{
		Map<String, Key> map = new HashMap<>(2);
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Algorithm.RSA);
		keyPairGen.initialize(BYTE_LENGTH);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);
		return map;
	}
	
	
	
	/**
     * <p>用私钥对信息生成数字签名。</p>
     * @param data 已加密数据。
     * @param privateKey 私钥。
     * @return
     * @throws Exception
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(Algorithm.SIGNATURE);
        signature.initSign(privateKey);
        signature.update(Base64.decodeBase64(data));
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * <p>校验数字签名。</p>
     * @param data 已加密数据。
     * @param publicKey 公钥。
     * @param sign 数字签名。
     * @return
     * @throws Exception
     */
    public static boolean verify(String data, PublicKey publicKey, String sign)
            throws Exception {
        Signature signature = Signature.getInstance(Algorithm.SIGNATURE);
        signature.initVerify(publicKey);
        signature.update(Base64.decodeBase64(data));
        return signature.verify(Base64.decodeBase64(sign));
    }
	
    /**
     * <p>将公钥字符串还原为公钥对象。</p>
     * @param publicKey 公钥字符串。
     * @return 公钥。
     * @throws Exception
     */
    public static PublicKey restorePublicKey(String publicKey) throws Exception{
    	X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
    	KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA);
    	return keyFactory.generatePublic(encodedKeySpec);
    }
    
    /**
     * <p>将密钥字符串还原为密钥对象。</p>
     * @param privateKey 秘钥字符串。
     * @return 秘钥。
     * @throws Exception
     */
    public static PrivateKey restorePrivate(String privateKey) throws Exception{
    	PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
    	KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA);
    	return keyFactory.generatePrivate(encodedKeySpec);
    }
	
	/**
	 * <p>使用密钥加密。</p>
	 * @param key 密钥,如果秘钥是privateKey，则是私钥加密，如果是publicKey，则是公钥加密。
	 * @param data 被加密的数据。
	 * @return 加密后的字符串，如果加密失败，返回null。
	 * @throws Exception 
	 */
	public static String encrypt(Key key,String data) throws Exception{
		Cipher cipher = Cipher.getInstance(Algorithm.RSA);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = cipher.doFinal(data.getBytes());
		return Base64.encodeBase64String(b);
	}
	
	/**
	 * <p>使用秘钥解密。</p>
	 * @param key 密钥,如果秘钥是privateKey，则是私钥解密，如果是publicKey，则是公钥解密。
	 * @param data 解密的数据。
	 * @return 解密后的数据，如果解密失败，返回null。
	 * @throws Exception 
	 */
	public static String decrypt(Key key,String data) throws Exception{
		Cipher cipher = Cipher.getInstance(Algorithm.RSA);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(data)));
	}
	
	public static PublicKey getPublicKey(Map<String,Key> map){
		return (PublicKey) map.get(PUBLIC_KEY);
	}
	
	public static String getPublicKeyStr(Map<String,Key> map){
		return Base64.encodeBase64String(getPublicKey(map).getEncoded());
	}
	
	public static PrivateKey getPrivateKey(Map<String,Key> map){
		return (PrivateKey) map.get(PRIVATE_KEY);
	}
	
	public static String getPrivateKeyStr(Map<String,Key> map){
		return Base64.encodeBase64String(getPrivateKey(map).getEncoded());
	}
}
