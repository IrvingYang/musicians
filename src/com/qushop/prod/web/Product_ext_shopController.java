package com.qushop.prod.web;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.entity.CartUtil;
import com.qushop.common.util.CachedManager;
import com.qushop.common.util.Constants;
import com.qushop.common.util.PagePojo;
import com.qushop.dict.entity.Brand_vendor;
import com.qushop.dict.service.Brand_vendorService;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.entity.ProductType;
import com.qushop.prod.entity.Product_ext_recommProd;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductReviewService;
import com.qushop.prod.service.ProductTypeService;
import com.qushop.prod.service.Product_ext_recommProdService;
import com.qushop.prod.service.Product_ext_shopService;
import com.qushop.user.entity.UserAddress;
import com.qushop.user.entity.User_Ext_Personal;
import com.qushop.user.service.UserAddressService;

@Controller
@RequestMapping("/eshop/shopProduct")
public class Product_ext_shopController {

	@Resource
	Product_ext_shopService service;

	@Resource
	ProductImgService productImgService;

	@Resource
	CommonDao<ProductImg> commondao;

	@Resource
	ProductTypeService productTypeService;

	@Resource
	Brand_vendorService brand_vendorService;

	@Resource
	ProductReviewService productReviewService;

	@Resource
	UserAddressService userAddressService;
	
	@Resource
	Product_ext_recommProdService product_ext_recommProdService;

	@RequestMapping("getShopProductByProductTypeId.action")
	@ResponseBody
	public Object getNewProduct(int pageno, int pagesize, String level, String productTypeId, String ordercondition,
			String listtype, HttpServletRequest request, HttpServletResponse response) {
		Map map = service.getShopProductByProductTypeId(level, productTypeId, ordercondition, pageno, pagesize,
				listtype);
		return map;
	}

	@RequestMapping("getShopProductDetail.action")
	@ResponseBody
	public Object getShopProductDetail(String productId, HttpServletRequest request) {

		Product_ext_shop product_ext_shop = null;
		product_ext_shop = service.getShopProductByMethod(5, productId).get(0);
		if (product_ext_shop.getProduct().getValidflag() == 1) {
			if (product_ext_shop.getProduct().getProvider().getSuperprovider() == 1) {
				String num = CachedManager.getInstance().get(productId);
				if (num == null || "".equals(num)) {
					num = "0";
				}
				Integer numplus = Integer.parseInt(num) + 1;
				CachedManager.getInstance().set(productId, numplus + "");
			}
		}
		return product_ext_shop;
	}

	@RequestMapping("getAssociatedShopProduct.action")
	@ResponseBody
	public Object getAssociatedProduct(String productId, HttpServletRequest request, HttpServletResponse response) {

		List<Product_ext_shop> product_ext_shopsList = service.getShopProductByMethod(1, productId);
		for (Product_ext_shop product_ext_shop : product_ext_shopsList) {
			product_ext_shop.getProduct().setProductimglist(
					productImgService.getProductImgByMethod(1, product_ext_shop.getProductId(), "2"));
		}

		return product_ext_shopsList;
	}

	@RequestMapping("searchProductShop.action")
	public Object searchProductShop(String searchstr, String ordercondition, String pageNostr, String pageSizestr,
			HttpServletRequest request, String pricestr, String orderstr, String gradestr,String typestr)
					throws UnsupportedEncodingException {
		Integer pageNo = 0;
		Integer pageSize = 12;
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPageno(pageNo);
		pagePojo.setPagesize(pageSize);
		pagePojo.setSearchKey(searchstr);

		if (pageNostr != null && !"".equals(pageNostr)) {
			pageNo = Integer.valueOf(pageNostr);
			pagePojo.setPageno(pageNo - 1);
		}
		if (pageSizestr != null && !"".equals(pageSizestr)) {
			pageSize = Integer.valueOf(pageSizestr);
			pagePojo.setPagesize(pageSize);
		}

		Map map = service.searchShopProductByProductShop(searchstr, ordercondition, pagePojo.getPageno(),
				pagePojo.getPagesize());

		List<ProductType> productTypesList = productTypeService.getProductTypeByMethod(9,
				Constants.TOP_PROD_TYPE_PARENT);
		List<Brand_vendor> vendorsList = brand_vendorService.getVendorByMethod(1);
		request.setAttribute("groupActivity", "y");
		request.setAttribute("flag", "shop");
		request.setAttribute("page", map.get("page"));
		request.setAttribute("search", searchstr);
		request.setAttribute("pricestr", pricestr);
		request.setAttribute("orderstr", orderstr);
		request.setAttribute("gradestr", gradestr);
		request.setAttribute("typestr", typestr);


		request.setAttribute("shopList", map.get("shopproduct"));
		request.setAttribute("productTypeList", productTypesList);
		request.setAttribute("vendorsList", vendorsList);
		return "web/productShopList";

	}

	@RequestMapping("productTypeList.action")
	@ResponseBody
	public List<ProductType> searchProductShop(HttpServletRequest request) {
		List<ProductType> productTypesList = productTypeService.getProductTypeByMethod(9,
				Constants.TOP_PROD_TYPE_PARENT);
		return productTypesList;
	}

	@RequestMapping("shopProduct.shtml")
	public String getShopProductList(HttpServletRequest request, String pageNostr, String pageSizestr, String orderstr,
			String pricestr, String gradestr, String searchstr, String brandstr,String typestr,String typename) {

		Integer pageNo = 0;
		Integer pageSize = 12;
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPageno(pageNo);
		pagePojo.setPagesize(pageSize);

		if (searchstr == null) {
			searchstr = "";
		}
		pagePojo.setSearchKey(searchstr);
		if (pageNostr != null && !pageNostr.equals("")) {
			pageNo = Integer.parseInt(pageNostr);
			pagePojo.setPageno(pageNo - 1);
		}
		if (pageSizestr != null && !pageSizestr.equals("")) {
			pageSize = Integer.parseInt(pageSizestr);
			pagePojo.setPagesize(pageSize);
		}

		List<Product_ext_shop> ext_shopsList = service.getProductShopPageByCondition(pagePojo, pricestr, orderstr,
				searchstr, gradestr, brandstr,typestr);
		List<ProductType> productTypesList = productTypeService.getProductType();
		List<Brand_vendor> vendorsList = brand_vendorService.getVendorByMethod(1);
		request.setAttribute("groupActivity", "y");
		request.setAttribute("flag", "shop");
		request.setAttribute("page", pagePojo);
		request.setAttribute("pricestr", pricestr);
		request.setAttribute("orderstr", orderstr);
		request.setAttribute("gradestr", gradestr);
		request.setAttribute("search", searchstr);
		request.setAttribute("brandstr", brandstr);
		request.setAttribute("typestr", typestr);
		request.setAttribute("typename", typename);

		request.setAttribute("shopList", ext_shopsList);
		request.setAttribute("productTypeList", productTypesList);
		request.setAttribute("vendorsList", vendorsList);
		return "web/productShopList";
	}

	@RequestMapping("productShopDetail.html")
	public String getProductShopDetail(String productId, HttpServletRequest request) {
		if (productId == null || "".equals(productId)) {
			return "redirect:/";
		} else {
			List<Product_ext_recommProd> recommProdsList = product_ext_recommProdService.getRecommProductByMethod(0,productId);

			if (recommProdsList != null && recommProdsList.size() > 0) {
				request.setAttribute("recommProdsList", recommProdsList);
			}
			
			List<Product_ext_shop> ext_shopsList = service.getShopProductByMethod(5, productId);
			
			if (ext_shopsList != null && ext_shopsList.size() > 0) {
				Product_ext_shop ext_shop = ext_shopsList.get(0);
				request.setAttribute("shop", ext_shop);
				// productReviewService.getProductReviewPaging(ext_shop.getProductId() ,0,1);
			} else {
				return "redirect:/";
			}
		}
		return "web/productShopDetail";
	}

	/*
	 * use ProductController operationCart
	 */
	@RequestMapping("opCart.html")
	@Deprecated()
	public String opCart(String action, String productId, Integer count, HttpServletRequest request) {
		// String userId = "";
		// Object obj =
		// request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
		// if(obj==null){
		// obj = request.getSession().getAttribute(Constants.LOGIN_USER);
		// if(obj==null){
		// return "needlogin";
		// }else{
		// User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) obj;
		// userId = user_Ext_Personal.getUserId();
		// }
		// }else{
		// return "entUser";
		// }
		//
		//
		// List<UserAddress> userAddressesList =
		// userAddressService.getUserAddressByUserId(userId);

		CartUtil cartUtil = (CartUtil) request.getSession().getAttribute("cart");
		if ("update".equals(action) || "add".equals(action)) {
			List<Product_ext_shop> ext_shopsList = service.getShopProductByMethod(5, productId);
			if (ext_shopsList != null && ext_shopsList.size() > 0) {
				Product_ext_shop ext_shop = ext_shopsList.get(0);
				if ("add".equals(action)) {
					if (cartUtil == null) {
						cartUtil = new CartUtil();
					}
					cartUtil.addCart(productId, ext_shop, count);
				} else {
					cartUtil.updateCart(productId, ext_shop, count);
				}
			}
		} else {
			if (cartUtil != null) {
				cartUtil.deleteCart(productId);
			}
		}
		request.getSession().setAttribute("cart", cartUtil);
		// request.setAttribute("userAddressesList",userAddressesList);
		return "redirect:/eshop/shopProduct/cartList.do";
	}

	@RequestMapping("cartList.do")
	public String cartList(HttpServletRequest request) {
		String userId = "";
		Object obj = request.getSession().getAttribute(Constants.LOGIN_ENTER_UER);
		if (obj == null) {
			obj = request.getSession().getAttribute(Constants.LOGIN_USER);
			if (obj == null) {
				return "needlogin";
			} else {
				User_Ext_Personal user_Ext_Personal = (User_Ext_Personal) obj;
				userId = user_Ext_Personal.getUserId();
			}
		} else {
			return "entUser";
		}

		List<UserAddress> userAddressesList = userAddressService.getUserAddressByUserId(userId);

		CartUtil cartUtil = (CartUtil) request.getSession().getAttribute("cart");

		Double totalPrice = 0.0;
		Integer totalProductCount = 0;
		if (cartUtil != null) {
			for (Iterator<?> iterator = cartUtil.getMap().keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next() + "";
				Double subtotal = cartUtil.getMap().get(key).getSubtotal();
				Integer count = cartUtil.getMap().get(key).getCount();
				totalPrice += subtotal;
				totalProductCount += count;
			}
		}
		request.setAttribute("totalProductCount", totalProductCount);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("flag", "shop");
		request.setAttribute("userAddressesList", userAddressesList);

		return "web/cart";
	}
}
