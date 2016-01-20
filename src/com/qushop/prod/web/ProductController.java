package com.qushop.prod.web;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Constants;
import com.qushop.common.util.ShoppingCart;
import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.musicains.service.LeaseConfigService;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.Product_ext_shopService;

@Controller
@RequestMapping("/eshop/product")
public class ProductController {

	@Resource
	ProductService productService;

	@Resource
	Product_ext_shopService shopService;

	@Resource
	ProductImgService productImgService;

	@Resource
	CommonDao commondao;

	@Resource
	LeaseConfigService leaseConfigServiceImpl;

	@RequestMapping("getProductDetailedById.action")
	@ResponseBody
	public Object getProductDetailedById(String productId, HttpServletRequest request, HttpServletResponse response) {

		List<Product> productsList = productService.getProductListByMethod(2, null, productId);
		if (productsList != null && productsList.size() > 0) {
			return productsList.get(0);
		}
		return null;
	}

	@RequestMapping(value = "operationCart.action", method = RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("all")
	public Object operationCart(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		String productId = request.getParameter("productId");
		String addcounts = request.getParameter("addcount");
		String cartType = request.getParameter("cartType");
		String leaseCycle = request.getParameter("leaseCycle");

		Date nowdate = new Date();
		int addcount = 0;
		if (addcounts != null && !"".equals(addcounts) && !"null".equals(addcounts)) {
			addcount = Integer.parseInt(addcounts);
		}
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(Constants.SHOPPING_CART);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
		}
		if ("getCart".equals(action)) {
			Map map = shoppingCart.getMap();
			double totalPrice = 0;
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
				String key = iter.next() + "";
				Map<String, Object> productMap = (Map<String, Object>) map.get(key);
				Integer type = Integer.parseInt(productMap.get("cartType") + "");
				Product product = (Product) productMap.get("product");
				if (type == 0) {
					Integer promoteflag = Integer.parseInt(productMap.get("promoteflag") + "");
					if (promoteflag == 1) {
						Double promotePrice = Double.parseDouble(productMap.get("promotePrice") + "");
						Integer productCount = Integer.parseInt(productMap.get("productCount") + "");
						totalPrice += (promotePrice * productCount);
					} else {
						Double originalPrice = Double.parseDouble(productMap.get("originalPrice") + "");
						Integer productCount = Integer.parseInt(productMap.get("productCount") + "");
						totalPrice += (originalPrice * productCount);
					}
				} else if (type == 1) {
					Double shopPrice = Double.parseDouble(productMap.get("shopPrice") + "");
					Integer productCount = Integer.parseInt(productMap.get("productCount") + "");
					totalPrice += (shopPrice * productCount);
				}

			}
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			String totalPriceStr = numberFormat.format(totalPrice);
			Map rmap = new HashMap();
			rmap.put("totalPrice", totalPriceStr);
			rmap.put("product", map);
			return rmap;
		}
		if ("add".equals(action)) {

			List<Product_ext_shop> product_ext_shops = shopService.getShopProductByMethod(5, productId);
			Product_ext_shop ext_shop = null;
			if (product_ext_shops != null && product_ext_shops.size() > 0) {
				ext_shop = product_ext_shops.get(0);
			}
			Product product =ext_shop.getProduct();
			//Product product = (Product) productService.getProductListByMethod(2, null, productId).get(0);
			//product.setProductimglist(productImgService.getProductImgByMethod(1, product.getProductId(), "4"));
			if (cartType.equals("2")) {
				LeaseConfig leaseConfig = leaseConfigServiceImpl.getLeaseConfig(ext_shop, addcount,
						Integer.valueOf(leaseCycle));
				shoppingCart.addCart(product, addcount, Integer.parseInt(cartType), ext_shop, leaseCycle, leaseConfig);
			} else {
				shoppingCart.addCart(product, addcount, Integer.parseInt(cartType), ext_shop, leaseCycle, null);
			}
		} else if ("remove".equals(action)) {
			shoppingCart.removeCart(productId, cartType);
		} else if ("update".equals(action)) {
			Product product = (Product) productService.getProductListByMethod(4, null, productId).get(0);
			product.setProductimglist(productImgService.getProductImgByMethod(1, product.getProductId(), "4"));
			shoppingCart.updateCart(product, addcount, cartType);
		}
		request.getSession().setAttribute(Constants.SHOPPING_CART, shoppingCart);
		return "operasuccess";
	}

}
