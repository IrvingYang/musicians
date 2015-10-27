package com.qushop.prod.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.PagePojo;
import com.qushop.prod.entity.Product_ext_groupBuy;
import com.qushop.prod.service.Product_ext_groupBuyService;

@Controller
@RequestMapping("/eshop/groupBuyProduct")
public class Product_ext_groupBuyController {

	@Resource
	Product_ext_groupBuyService service;

	@RequestMapping("getgroupBuyProductByType.action")
	@ResponseBody
	public Object getgroupBuyProductByType(int type, String typeId, int maxcount,
			HttpServletRequest request,	HttpServletResponse response) {
		return service.getgroupBuyProductByMethod(type, typeId, maxcount+"");
	}
	
	@RequestMapping("groupBuyList.shtml")
	public String getGroupBuyList(HttpServletRequest request,String pageNostr,
			String pageSizestr){
		Integer pageNo = 0;
		Integer pageSize = 12;
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPageno(pageNo);
		pagePojo.setPagesize(pageSize);
		
		if(pageNostr!=null && !pageNostr.equals("")){
			pageNo = Integer.parseInt(pageNostr);
			pagePojo.setPageno(pageNo-1);
		}
		if(pageSizestr!=null && !pageSizestr.equals("")){
			pageSize = Integer.parseInt(pageSizestr);
			pagePojo.setPagesize(pageSize);
		}
		List<Product_ext_groupBuy> ext_groupBuysList = service.getPageGroupBuyList(pagePojo);
		request.setAttribute("groupbuyList", ext_groupBuysList);
		return "web/groupBuy";
	}
	
}
