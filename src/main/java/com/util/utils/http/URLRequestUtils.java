package com.util.utils.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.io.IOUtils;

import com.util.utils.charsets.Charsets;



public class URLRequestUtils {
	
	
	public static String httpGet(String url) throws IOException{
		URL u = new URL(url);
		return httpGet(u);
	}
	
	public static String httpGet(URL url) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		setGetURLConnection(connection);
		return readResponseData(connection);
	}
	
	public static String httpPost(URL url,String requestParam) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		setPostURLConnection(connection);
		return writeAndReadPostData(connection, requestParam);
	}
	
	
	public static String httpsGet(String url) throws IOException{
		URL u = new URL(url);
		return httpsGet(u);
	}
	
	public static String httpsGet(String url,Map<Object,Object> paeamMap) throws IOException{
		URL u = JoiningTogetherGetRequestParamMap(url, paeamMap);
		return httpsGet(u);
	}
	
	public static String httpsGet(URL url) throws IOException{
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		setGetURLConnection(connection);
//		connection.setSSLSocketFactory(createSSLSocketFactory());
		return readResponseData(connection);
	}
	
	public static String httpsGet(URL url,Map<Object,Object> paeamMap) throws IOException{
		URL u = JoiningTogetherGetRequestParamMap(url.toString(), paeamMap);
		return httpsGet(u);
	}
	
	public static String httpsPost(URL url,String requestParam) throws IOException{
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		setPostURLConnection(connection);
		connection.setSSLSocketFactory(createSSLSocketFactory());
		return writeAndReadPostData(connection, requestParam);
	}
	
	/**
	 * Joining Together Get Request Param Map Type.
	 * 拼接get请求参数，类型Map。
	 * @throws MalformedURLException 
	 */
	private static URL JoiningTogetherGetRequestParamMap(String url,Map<Object,Object> paeamMap) throws MalformedURLException{
		if(paeamMap!=null&&!paeamMap.isEmpty()){
			StringBuffer u = new StringBuffer(url);
			u.append("?");
			for(Entry<Object,Object> entry : paeamMap.entrySet()){
				u.append(entry.getKey()).append("=").append(entry.getValue()).append("&&");
			}
			int queryLength = u.length();
			u.delete(queryLength-2, queryLength);
			return new URL(u.toString());
		}
		return new URL(url);
	}
	
	private static void setURLConnection(HttpURLConnection connection){
		connection.setRequestProperty("content-type", "application/json;charset=UTF-8");
		connection.setConnectTimeout(3000);
		connection.setReadTimeout(3000);
	}
	
	/**
	 * 设置GET请求头
	 * @param connection
	 */
	private static void setGetURLConnection(HttpURLConnection connection){
		try {
			setURLConnection(connection);
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 设置POST请求头
	 * @param connection
	 */
	private static void setPostURLConnection(HttpURLConnection connection){
		try {
			setURLConnection(connection);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写post请求数据
	 * @param connection
	 * @param requestParam
	 * @throws IOException 
	 */
	private static void writeRequestPostData(URLConnection connection,String requestParam) throws IOException{
		OutputStream outputStream = null;
		try {
			outputStream = connection.getOutputStream();
			IOUtils.write(requestParam, outputStream, Charsets.UTF_8);
		}finally{
			if(outputStream!=null)
				outputStream.close();
		}
	}
	/**
	 * 读取响应数据
	 * @param connection
	 * @return
	 * @throws IOException 
	 */
	private static String readResponseData(URLConnection connection) throws IOException{
		InputStream inputStream = null;
		try {
			inputStream = connection.getInputStream();
			return IOUtils.toString(inputStream, Charsets.UTF_8);
		}finally{
			if(inputStream!=null)
				inputStream.close();
		}
	}
	
	/**
	 * POST请求写，并且读取响应数据。
	 * @param connection
	 * @param requestParam
	 * @return
	 * @throws IOException 
	 */
	private static String writeAndReadPostData(URLConnection connection,String requestParam) throws IOException{
		writeRequestPostData(connection, requestParam);
		return readResponseData(connection);
	}
	
	
	
	public static SSLSocketFactory createSSLSocketFactory(){
		
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS", "SunJSSE");
			
			KeyManager[] km = null;
			TrustManager[] tm = {new MyTrustManager()};
			SecureRandom random = new SecureRandom();
			
			sslContext.init(km, tm, random);
			
			return sslContext.getSocketFactory();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
