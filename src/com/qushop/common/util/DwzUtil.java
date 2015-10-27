package com.qushop.common.util;


public class DwzUtil {

	private static class DwzMessage{
		public String statusCode;
		public String message;
		public String navTabId;
		public String rel;
		public String callbackType="";
		public String forwardUrl="";
		public String confimMsg="";
	}

	public static DwzMessage opSuccess(String message,String navId){
		DwzMessage dwzMessage = new DwzMessage();
		dwzMessage.statusCode="200";
		dwzMessage.message=message;
		dwzMessage.navTabId=navId;
		return dwzMessage;
	}
	
	public static DwzMessage opFailed(String message,String navId){
		DwzMessage dwzMessage = new DwzMessage();
		dwzMessage.statusCode="300";
		dwzMessage.message=message;
		dwzMessage.navTabId=navId;
		return dwzMessage;
	}
}
