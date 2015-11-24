package com.qushop.common.ckfinder;

import javax.servlet.http.HttpServletRequest;

import com.ckfinder.connector.configuration.ConfigurationPathBuilder;
import com.qushop.common.util.Constants;
import com.qushop.common.util.PublicUtil;
import com.qushop.user.entity.Oper;

public class MyConfigurationPathBuilder extends ConfigurationPathBuilder {

	@Override
	public String getBaseUrl(final HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(Constants.OPER_USER);
		Oper oper = null;
		if (obj != null) {
			oper = (Oper) obj;
		}
		
		return PublicUtil.getURLWithConext(request) + "/media_upload/" + oper.getProviderId() + "/";
	}
	
	@Override
	public String getBaseDir(HttpServletRequest arg0) {
		Oper oper=(Oper)arg0.getSession().getAttribute(Constants.OPER_USER);
		return PublicUtil.getRootFileDirectory(arg0)+"/media_upload/" + oper.getProviderId() + "/";
	}
}
