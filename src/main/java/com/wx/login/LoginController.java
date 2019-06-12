package com.wx.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wx.login.utils.AuthUtil;

@RestController
public class LoginController {
	
	@RequestMapping("/wxLogin")
	public void wxLogin(HttpServletResponse response) throws IOException {
		String callBack="http://25x2579v70.wicp.vip/wxGetMessage";
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+AuthUtil.APPID
				+ "&redirect_uri="+URLEncoder.encode(callBack)
				+ "&response_type=code"
				+ "&scope="+AuthUtil.SCOPETYPE1
				+ "&state=STATE#wechat_redirect";
		
		response.sendRedirect(url);
	}
	
	@RequestMapping("/wxGetMessage")
	public JSONObject wxGetMessage(HttpServletRequest request) throws ClientProtocolException, IOException {
		JSONObject result=null;
		String code=request.getParameter("code");
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid="+AuthUtil.APPID
				+ "&secret="+AuthUtil.APPSECRET
				+ "&code="+code
				+ "&grant_type=authorization_code";
		result= AuthUtil.doGetJson(url);
		System.out.println("openid:"+result.get("openid"));
		System.out.println("access_token:"+result.get("access_token"));
		System.out.println("expires_in:"+result.get("access_token"));
		System.out.println("refresh_token:"+result.get("access_token"));
		System.out.println("scope:"+result.get("access_token"));
		
		String userInfoUrl="https://api.weixin.qq.com/sns/userinfo?"
				+ "access_token="+result.get("access_token")
				+ "&openid="+result.get("openid")
				+ "&lang=zh_CN";
		result= AuthUtil.doGetJson(userInfoUrl);
		System.out.println("openid:"+result.get("openid"));
		System.out.println("nickname:"+result.get("nickname"));
		System.out.println("sex:"+result.get("sex"));
		System.out.println("province:"+result.get("province"));
		System.out.println("city:"+result.get("city"));
		System.out.println("country:"+result.get("country"));
		System.out.println("headimgurl:"+result.get("headimgurl"));
		System.out.println("privilege:"+result.get("privilege"));
		System.out.println("unionid:"+result.get("unionid"));
		return result;
	}
	
}
