package com.mcnblogs.utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 */
public class XmlJSON {

	/**
	 * xml字符串转json字符串
	 * @param xml字符串
	 * @return
	 */
	public static String xml2JSON(String xml) {
		try {
			JSONObject obj = XML.toJSONObject(xml);
			return obj.toString();
		} catch (JSONException e) {
			System.err.println("xml->json失败" + e.getLocalizedMessage());
			return "";
		}
	}

}
