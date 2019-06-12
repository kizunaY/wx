package com.wx.login.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class AuthUtil {
	
	public static final String APPID="wx5f43bfeef82e3fcd";
	public static final String APPSECRET="3f29ff642277342e708ce8bbce21bf7f";
	public static final String SCOPETYPE1="snsapi_userinfo";
	public static final String SCOPETYPE2="snsapi_base";
	public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
		JSONObject jsonObject=null;
		DefaultHttpClient client=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url);
		HttpResponse response=client.execute(httpGet);
		HttpEntity entity=response.getEntity();
		if(entity!=null) {
			String result=EntityUtils.toString(entity,"utf-8");
			jsonObject=JSONObject.parseObject(result);
		}
		httpGet.releaseConnection();
		return jsonObject;
	}
}
