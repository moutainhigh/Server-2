package com.fantingame.game.gateway.util;

import java.security.MessageDigest;


public class EncryptUtil {

	public static String getMD5(String str) {
		return encode(str, "MD5");
	}

	public static String getDES(String str) {
		return encode(str, "DES");
	}

	public static String getSHA1(String str) {
		return encode(str, "SHA-1");
	}

	//	public static String getLittleMD5(String str) {
	//		String estr = encode(str, "MD5");
	//		return estr.substring(0, 20);
	//	}
	//
	//	public static String getLittleSHA1(String str) {
	//		String estr = encode(str, "SHA-1");
	//		return estr.substring(0, 20);
	//	}

	private static String encode(String str, String type) {
		try {
			MessageDigest alga = MessageDigest.getInstance(type);
			alga.update(str.getBytes());
			byte digesta[] = alga.digest();
			String hex = byte2hex(digesta);
			// String hex2 = byte2hex2(digesta);
			// if (!hex.equals(hex2)) {
			// System.out.println("str:" + str);
			// }
			return hex;
		}
		catch (Exception e) {
		}
		return "";
	}

	public static String uuid(String strs[]) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			for (int i = 0; i < strs.length; i++) {
				if (strs[i] != null) {
					md.update(strs[i].getBytes());
				}
			}

			byte bs[] = md.digest();
			return byte2hex(bs);
		}
		catch (Exception e) {
		}
		return null;
	}

	private static String byte2hex(byte b[]) {
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < b.length; n++) {
			String stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1) {
				//				hs = hs + "0" + stmp;
				sb.append("0");
			}
			sb.append(stmp);
			//			else {
			////				hs = hs + stmp;
			//			}
		}

		return sb.toString().toUpperCase();
	}

	// private static byte[] hex2byte(byte b[]) {
	// if (b.length % 2 != 0) {
	// throw new
	// IllegalArgumentException("\u957F\u5EA6\u4E0D\u662F\u5076\u6570");
	// }
	// byte b2[] = new byte[b.length / 2];
	// for (int n = 0; n < b.length; n += 2) {
	// String item = new String(b, n, 2);
	// b2[n / 2] = (byte) Integer.parseInt(item, 16);
	// }
	//
	// return b2;
	// }
	
	private final static String key = "fuckUBitch";
	
	/**
	 * 加密
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] encode(byte[] data) {
		try {
			int len = data.length;
			for (int i = 0; i < len; i++) {
				int index = i % key.length();
				byte b = (byte) key.charAt(index);
				data[i] = (byte) (-data[i] + b);
			}

			return data;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 解密
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] decode(byte[] data) {
		try {
			int len = data.length;
			for (int i = 0; i < len; i++) {
				int index = i % key.length();
				byte b = (byte) key.charAt(index);
				data[i] = (byte) (-data[i] + b);
			}
			
			return data;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}
}
