package com.util.utils.conversion;

/**
 * <p>不同进制之间的转换</p>
 * @author xlp
 *
 */
public class ConversionSystemUtils {
	/**
	 * <p>将二进制转换成16进制。</p>
	 * @param buf 二进制字节数组。
	 * @return 16进制字符串。
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * <p>将16进制转换为二进制。</p>
	 * @param hexStr 16进制字符串。
	 * @return 二进制字节数组。
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
