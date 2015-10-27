package com.qushop.common.ckfinder;


import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ckfinder.connector.configuration.Configuration;
import com.qushop.common.util.Constants;
import com.qushop.user.entity.Oper;

public class CkfinderConfiguration extends Configuration {
    String path = "";
    HttpServletRequest request = null;
    
    public CkfinderConfiguration(ServletConfig servletConfig) {
            super(servletConfig);
            path = servletConfig.getServletContext().getContextPath();
    }


    @Override
    public void init() throws Exception {
            super.init();
            /*改用相对路径 便于部署在ngnix */
            //this.baseURL = path + "/media_upload/";
            this.baseURL = "media_upload/";            
    }
    
    @Override
	protected Configuration createConfigurationInstance() {
		return new CkfinderConfiguration(this.servletConf);
	}
    
    @Override
    public boolean checkAuthentication(final HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	if(session.getAttribute(Constants.OPER_USER) == null){
    		return false;
    	}
    	return true;
    }
    

}
