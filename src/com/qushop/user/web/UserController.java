package com.qushop.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.service.SmsCheckOutService;
import com.qushop.common.util.Constants;
import com.qushop.common.util.UtilDate;
import com.qushop.user.entity.User;
import com.qushop.user.entity.UserAddress;
import com.qushop.user.entity.User_Ext_Enterprise;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.UserAddressService;
import com.qushop.user.service.UserService;

@Controller
@RequestMapping("/user/user")
public class UserController {

	@Resource
	UserService service;

	@Resource
	SmsCheckOutService checkOutService;

	@Resource
	UserAddressService userAddressService;

	@RequestMapping("login.action")
	@ResponseBody
	public Object login(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(DigestUtils.md5Hex(password + Constants.MAIN_KEY));
		boolean bool = service.login(user, request);
		if (bool) {
			return true;
		} else {
			return "uperror";
		}
	}

	@RequestMapping("logout.action")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.LOGIN_ENTER_UER);
		request.getSession().removeAttribute(Constants.LOGIN_USER);
		return "web/signup";
	}

	@RequestMapping("register.action")
	@ResponseBody
	public Object register(User user, String telephone, Integer checkcode, HttpServletRequest request) {

		try {
			if (user.getUserType() != 2) {
				Integer code = (Integer) request.getSession().getAttribute("registercode");
				if (code == null || !code.equals(checkcode)) {
					return "checkerror";
				}
			}
			user.setUserId(1 + UtilDate.getNowMonthDate() + (new Random().nextInt(89999) + 10000));
			user.setPassword(DigestUtils.md5Hex(user.getPassword() + Constants.MAIN_KEY));
			user.setValidflag((short) 1);
			service.register(user, request);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("exists.action")
	@ResponseBody
	public Object exists(String userName, HttpServletRequest request) {

		int count = service.exists(userName);
		if (count > 0) {
			return "exists";
		} else {
			return "not_exists";
		}
	}

	@RequestMapping("getLoginUser.action")
	@ResponseBody
	public Object getLoginUser(HttpServletRequest request) {

		Map rMap = new HashMap();
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (obj == null) {
			obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
			if (obj == null) {
				return "nologin";
			} else {
				rMap.put("loginuser", (User_Ext_Enterprise) obj);
			}
		} else {
			rMap.put("loginuser", (User_Ext_Personal) obj);
		}
		return rMap;
	}

	@RequestMapping("personLogin.action")
	@ResponseBody
	public Object personLogin(HttpServletRequest request) {

		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (obj == null) {
			return "nologin";
		} else {
			return "";
		}
	}

	@RequestMapping("updateLoginUserPassword.action")
	@ResponseBody
	public Object updateLoginUserPassword(String oldpassword, String spassword, HttpServletRequest request) {

		String userId = null;
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (obj == null) {
			obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
			if (obj == null) {
				return "needlogin";
			} else {
				userId = ((User_Ext_Enterprise) obj).getUser().getUserId();
			}
		} else {
			userId = ((User_Ext_Personal) obj).getUser().getUserId();
		}
		User user = (User) service.getUserByMethod(6, userId).get(0);
		user.setPassword(DigestUtils.md5Hex(oldpassword + Constants.MAIN_KEY));
		boolean bool = service.login(user, request);
		if (bool) {
			user.setPassword(DigestUtils.md5Hex(spassword + Constants.MAIN_KEY));
			boolean b = service.updateUser(user);
			if (b) {
				return "success";
			} else {
				return "failed";
			}
		} else {
			return "passworderror";
		}
	}

	@RequestMapping("forgetPassword.action")
	@ResponseBody
	public Object forgetPassword(String telephone, String phone, Integer vcode, String newpassword,
			HttpServletRequest request) {

		try {
			Integer code = (Integer) request.getSession().getAttribute("forgetCode");
			if (code == null || !code.equals(vcode)) {
				return "checkerror";
			}
			List userList = service.getUserByMethod(10, telephone);
			if (userList.size() <= 0) {
				return false;
			}
			User user = (User) userList.get(0);
			user.setPassword(DigestUtils.md5Hex(newpassword + Constants.MAIN_KEY));
			boolean bool = service.updateUser(user);
			if (bool) {
				request.getSession().removeAttribute("forgetCode");
				request.getSession().removeAttribute("sendForgetCodeTime");
			}
			return bool;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("getUserIdentity.action")
	@ResponseBody
	public Object getUserIdentity(HttpServletRequest request) {

		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (obj != null) {
			return "user";
		} else {

			Object obj1 = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
			if (obj1 != null) {
				return "entUser";
			} else {
				return "user";
			}
		}
	}

	/**
	 * =========================================================================
	 * =======
	 */
	@RequestMapping("userCenter.do")
	public String toUserCenter(HttpServletRequest request) {
		String userId = null;
		Object obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		if (obj == null) {
			obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
			if (obj == null) {
				return "needlogin";
			} else {
				userId = ((User_Ext_Enterprise) obj).getUser().getUserId();
			}
		} else {
			userId = ((User_Ext_Personal) obj).getUser().getUserId();
		}

		// List<UserAddress> userAddresss =
		// userAddressService.getUserAddressByUserId(userId);
		// request.setAttribute("userAddresList", userAddresss);
		// request.setAttribute("total", userAddresss.size());

		return "web/personal";
	}

	@RequestMapping("signup.shtml")
	public String toLogin(HttpServletRequest request, String _src) {
		if (_src != null && !_src.isEmpty()) {
			String serverPrefix = "/musicians";// Irving 根据url路径进行修改
			if (_src.contains(serverPrefix)) {
				_src = _src.replace(serverPrefix, "");
			}
			;
		}
		request.setAttribute("returnURL", _src);
		return "web/signup";
	}

	@RequestMapping("login.shtml")
	public String userLogin(HttpServletRequest request, String returnURL) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		// String returnURL = request.getParameter("returnURL");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(DigestUtils.md5Hex(password + Constants.MAIN_KEY));
		boolean bool = service.login(user, request);
		if (bool) {
			if (returnURL != null && !"".equals(returnURL)) {
				return "redirect:" + returnURL;
			}
			return "redirect:/";
		} else {
			request.setAttribute("returnURL", returnURL);
			request.setAttribute("msg", "用户名密码错误");
			return "web/signup";
		}
	}

	@RequestMapping("register.shtml")
	public Object userRegister(User user, String returnURL, HttpServletRequest request) {
		user.setUserId(1 + UtilDate.getNowMonthDate() + (new Random().nextInt(89999) + 10000));
		user.setPassword(DigestUtils.md5Hex(user.getPassword() + Constants.MAIN_KEY));
		user.setValidflag((short) 1);
		user.setUserType((short) 1);// irving 暂时自由一般用户。
		request.setAttribute("returnURL", returnURL);
		service.register(user, request);
		return "web/signup";
	}

	@RequestMapping("updatePassword.action")
	public Object updatePassword(User user, String returnURL, HttpServletRequest request) {
		return "web/password";
	}
}
