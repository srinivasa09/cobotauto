package com.cobot.testcases;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {

	public static String decode(String base64EncodedStr)
	{
		byte[] decoded = Base64.decodeBase64(base64EncodedStr);
		String decodedStr="";
		try
		{
			decodedStr = new String(decoded, "UTF-8");
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return decodedStr;
	}
}
