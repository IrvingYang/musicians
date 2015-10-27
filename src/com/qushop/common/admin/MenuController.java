package com.qushop.common.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/menu")
public class MenuController {
	
	@RequestMapping("product.do")
	public String product(){
		
		return "admin/menu/menu_product";
	}
	
	@RequestMapping("groupbuy.do")
	public String groupbuy(){

		return "admin/menu/menu_groupbuy";
	}
	
	@RequestMapping("bigdeal.do")
	public String bigdeals(){
		
		return "admin/menu/menu_bigdeal";
	}
	
	@RequestMapping("provider.do")
	public String provider(){
		
		return "admin/menu/menu_provider";
	}
	
	@RequestMapping("user.do")
	public String user(){
		
		return "admin/menu/menu_user";
	}
	
	@RequestMapping("advertising.do")
	public String advertising(){
		
		return "admin/menu/menu_advertising";
	}
	@RequestMapping("sysconfig.do")
	public String sysconfig(){
		return "admin/menu/menu_sysconfig";
	}
	
	@RequestMapping("order.do")
	public String order(){
		return "admin/menu/menu_order";
	}

	@RequestMapping("dict.do")
	public String dict(){
		return "admin/menu/menu_dict";
	}
	
	@RequestMapping("activity.do")
	public String activity()
	{
		return "admin/menu/menu_activity";
	}
	
	@RequestMapping("blogs.do")
	public String blogs()
	{
		return "admin/menu/menu_blogs";
	}
	
	@RequestMapping("travel.do")
	public String travel()
	{
		return "admin/menu/menu_travel";
	}
	
	@RequestMapping("programa.do")
	public String programa()
	{
		return "admin/menu/menu_programa";
	}

}
