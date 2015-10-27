package com.qushop.common.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.AuthorizationForUserException;
import com.qushop.common.util.Constants;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.RoleAuthority;

/**
 * 
 * 
 * @author xie
 *
 */
public class MvcInterceptor implements HandlerInterceptor {

	@Resource
	CommonDao commonDao;

	private static final Logger log = Logger.getLogger(MvcInterceptor.class);

	private String[] excludedUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestURI3 = request.getRequestURI();
		String requestURI2 = requestURI3;
		String requestUri = requestURI2;
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				return true;
			}
		}

		if (requestUri.contains("manage")) {

			Object obj = request.getSession().getAttribute(Constants.OPER_USER);
			if (obj == null) {
				response.sendRedirect(request.getContextPath() + "/manage/oper/toLogin.action");
				return false;
			} else {
				Oper oper = (Oper) obj;
				Oper o1 = (Oper) commonDao
						.findByHql("from Oper where operName=? and password=?", oper.getOperName(), oper.getPassword())
						.get(0);
				if (o1.getLoginStatus() == 0) {
					request.getSession().removeAttribute(Constants.OPER_USER);
					response.sendRedirect(request.getContextPath() + "/manage/oper/toLogin.action");
					return false;
				}
				List<RoleAuthority> roleauthoritiesList = oper.getRole().getRoleauthoritiesList();
				for (RoleAuthority roleAuthority : roleauthoritiesList) {
					if (requestUri.trim().contains(roleAuthority.getUrllist().getUrlLink().trim())) {
						// System.out.println(uriStr.contains(roleAuthority.getUrllist().getUrlLink())+"
						// "+uriStr+"
						// "+roleAuthority.getUrllist().getUrlLink());
						return true;
					}
				}
			}
			// response.sendRedirect(request.getContextPath()+"/manage/oper/forbidden.do");
			return true;
		}
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (obj == null) {
			String originalURL=request.getRequestURI();
			response.sendRedirect(request.getContextPath()+"/user/user/signup.shtml?_src="+originalURL);
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	public String[] getExcludedUrls() {
		return excludedUrls;
	}

	public void setExcludedUrls(String[] excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

}
