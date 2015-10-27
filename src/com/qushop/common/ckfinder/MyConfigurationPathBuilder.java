package com.qushop.common.ckfinder;

import javax.servlet.http.HttpServletRequest;

import com.ckfinder.connector.configuration.ConfigurationPathBuilder;
import com.qushop.common.util.Constants;
import com.qushop.user.entity.Oper;

public class MyConfigurationPathBuilder extends ConfigurationPathBuilder {


	@Override
    public String getBaseUrl(final HttpServletRequest request) { 
		try {
    		Object obj = request.getSession().getAttribute(Constants.OPER_USER);
        	Oper oper = null;
        	if(obj!=null){
        		oper = (Oper) obj;
        	}
        	return "media_upload/"+oper.getProviderId()+"/";
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "media_upload/";
    }
}
