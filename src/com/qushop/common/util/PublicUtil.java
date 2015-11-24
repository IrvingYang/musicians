package com.qushop.common.util;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.qushop.user.entity.Oper;
import com.qushop.user.entity.User_Ext_Personal;

public class PublicUtil {
	

	public static Oper getOper(HttpServletRequest request){

		Object obj = request.getSession().getAttribute(Constants.OPER_USER);
		return (Oper)obj;
	}
	
	public static boolean userLoginState(HttpServletRequest request){
		boolean bool = false;
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if(obj!=null){
			bool=true;
		}
		return bool;
	}
	public static boolean entUserLoginState(HttpServletRequest request){
		boolean bool = false;
		Object obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
		if(obj!=null){
			bool=true;
		}
		return bool;
	}
	
	public static User_Ext_Personal getUserOfSession(HttpServletRequest request){

		return (User_Ext_Personal) request.getSession().getAttribute(Constants.LOGIN_USER);
	}
	
	public static User_Ext_Personal getEntUserOfSession(HttpServletRequest request){

		return (User_Ext_Personal) request.getSession().getAttribute(Constants.LOGIN_USER);
	}
	
	public static String getUUID(){
		
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		uuid = uuid.replace("_", "");
		uuid = uuid.replace(".", "");
		return uuid;
	}
	
	
	
	
	
	public static String getURLWithConext(HttpServletRequest request){
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		String contextPath = request.getContextPath(); // includes leading
														// forward slash
		String resultPath = scheme + "://" + serverName + ":" + serverPort + contextPath;
		return resultPath;
	}
	public static String getRootFileDirectory(HttpServletRequest request){
		
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	public static int whichOneIsEmpty(List<Integer> obj){
		
		int i=0;
		while(i<Constants.MAX_AD_SERIALNUMBER){
			
			if( i<obj.size()){
				if(obj.get(i)==(i+1)){
					i++;
					continue;
				}else{
					return i+1;
				}
				
			}else{
			   return i+1;
			}	
			
		}
		return 0;
	}
	
}
