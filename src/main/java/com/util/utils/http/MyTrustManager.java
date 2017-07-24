package com.util.utils.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Trust Manager 信任管理器
 * @author xlp
 *
 */
public class MyTrustManager implements X509TrustManager {

	//Certificate 证书
	
	/**
	 * check Client Trusted 检查客户端信任
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		System.out.println("112");
	}

	/**
	 * check Server Trusted 检查服务端信任
	 */
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//		System.out.println(chain.length);
//		System.out.println(chain);
//		System.out.println(authType);
//		for(X509Certificate certificate : chain){
//			System.out.println(certificate);
//			certificate.checkValidity();
//			System.out.println(certificate.getEncoded());
//			System.out.println(certificate.getBasicConstraints());
//			System.out.println(certificate.getSigAlgName());
//			System.out.println(certificate.getSigAlgOID());
//			System.out.println(certificate.getType());
//			System.out.println(certificate.getVersion());
//			System.out.println(certificate.hasUnsupportedCriticalExtension());
//			System.out.println(certificate.getPublicKey());
//			System.out.println(certificate.getIssuerDN());
//			System.out.println(certificate.getSigAlgParams());
//			System.out.println("---------------------------------------");
//		}
	}

	/**
	 * get Accepted Issuers 得到公认的发行人
	 * 返回受信任的X509安全证书数组
	 */
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		System.out.println("1124444");
		return null;
	}

}
