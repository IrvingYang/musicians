package com.qushop.prod.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.PagePojo;
import com.qushop.common.util.PublicUtil;
import com.qushop.order.entity.Order_detail;
import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_detailService;
import com.qushop.order.service.Order_listService;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.entity.Product_ext_groupBuy;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.Product_ext_groupBuyService;
@Service
public class Product_ext_groupBuyServiceImpl implements
		Product_ext_groupBuyService {

	@Resource
	CommonDao commonDao;
	
	@Resource
	ProductService productService;
	
	@Resource
	ProductImgService productImgService;
	
	@Resource
	Order_listService order_listService;
	
	@Resource
	Order_detailService order_detailService;
	
	@Override
	@SuppressWarnings("all")
	public List<Product_ext_groupBuy>  getgroupBuyProductByMethod(int type, String ...params) {

		List<Product_ext_groupBuy> groupBuysList = null;
		List<ProductImg> imgsList = null;
		switch (type) {
		case 1:
			/* 如果开放立刻开团模式  
			 * select * from qushop.tb_product_ext_groupbuy where openresult is null and groupbuytype = 0 and validflag = 1 union select * from qushop.tb_product_ext_groupbuy where groupbuytype = 1 and sysdate()< groupBuyEndTime and  validflag= 1 
			*/
			if (params[0].equals("00")) {
				groupBuysList = commonDao.findPagingBySql("select * from tb_product_ext_groupBuy where groupBuyStartTime<=NOW() and groupBuyEndTime>NOW() and validflag = 1", 0,Integer.parseInt(params[1]),Product_ext_groupBuy.class);
			} else {

			}
			break;
		case 2:	
			/* 如果开放立刻开团模式  
			 * select * from qushop.tb_product_ext_groupbuy where groupbuyid = ? and validflag = 1 
			*/
			groupBuysList = commonDao.findPagingBySql("select * from tb_product_ext_groupBuy where groupbuyid = ?  and groupBuyStartTime<=NOW() and groupBuyEndTime>NOW() and validflag = 1",  0, Integer.parseInt(params[1]),Product_ext_groupBuy.class, params[0]);
			String prodtype = groupBuysList.get(0).getProductId().substring(4, 6);
			groupBuysList = commonDao.findPagingByHql("from Product_ext_groupBuy where substr(productid, 5, 2) = ? and groupbuyid <> ? and validflag = 1",  0, Integer.parseInt(params[1]), prodtype, params[0]);
            break;
		case 3:	
			groupBuysList = commonDao.findByHql("from Product_ext_groupBuy where groupbuyid = ?  and validflag = 1", params[0]);
			break;
		case 4:
			
			if(Integer.parseInt(params[0])==0){
				groupBuysList = commonDao.findByHql("from Product_ext_groupBuy where validflag = 1"); 
			}
			if(Integer.parseInt(params[0])==1){
				groupBuysList = commonDao.findBySql("select groups.* from tb_product_ext_groupBuy groups,tb_product product where groups.productId=product.productId and product.providerId=? and groups.validflag = 1 ",Product_ext_groupBuy.class,params[1]); 
			}
			break;
		case 5:
			groupBuysList = commonDao.findByHql("from Product_ext_groupBuy where  groupbuyid = ?",params[0]);
			break;
		case 6:
			groupBuysList = commonDao.findBySql("select * from tb_product_ext_groupBuy where  productId in ("+params[0]+") and validflag=1",null);
			return groupBuysList;
		default:
			break;
		}
		for (Product_ext_groupBuy product_ext_groupBuy : groupBuysList) {
			
			Product product = productService.getProductListByMethod(2, null, product_ext_groupBuy.getProductId()).get(0);
			switch(type) {
			case 1:
				imgsList = productImgService.getProductImgByMethod(6, product.getProductId());
				break;
			case 2:
				imgsList = productImgService.getProductImgByMethod(1, product.getProductId(), "4");
				break;
			case 3:
				imgsList = productImgService.getProductImgByMethod(4, product.getProductId());
				break;
			default:
				break;
			}
			product.setProductimglist(imgsList);
			product_ext_groupBuy.setProduct(product);
		}
		return groupBuysList;

	}


//	@Override
//	public List<Product_ext_groupBuy> getProduct_ext_groupByMethod(int type,
//			String... params) {
//		
//		List<Product_ext_groupBuy> groupBuysList = new ArrayList();
//		
//		switch (type) {
//		
//		case 1:
//			
//			if(Integer.parseInt(params[0])==0){
//				groupBuysList = commonDao.findByHql("from Product_ext_groupBuy where validflag = 1"); 
//			}
//			if(Integer.parseInt(params[0])==1){
//				groupBuysList = commonDao.findBySql("select groups.* from tb_product_ext_groupBuy groups,tb_product product where groups.productId=product.productId and groups.validflag = ? and product.providerId=?",Product_ext_groupBuy.class, (short)1,params[1]); 
//			}
//			break;
//			
//		case 2:
//			groupBuysList = commonDao.findByHql("from Product_ext_groupBuy where groupbuyid = ? and validflag = 1",params[0]);
//			break;
//			
//		case 3:
//			groupBuysList = commonDao.findByHql("from Product_ext_groupBuy where  groupbuyid = ?",params[0]);
//			break;
//
//		default:
//			break;
//		}
//		for (Product_ext_groupBuy product_ext_groupBuy : groupBuysList) {
//			Product product = productService.getProductListByMethod(2, null, product_ext_groupBuy.getProductId()).get(0);		
//			product_ext_groupBuy.setProduct(product);
//		}
//		return groupBuysList;
//	}

	@Override
	public boolean updateProduct_ext_group(Product_ext_groupBuy groupBuy) {
		
		try {
			commonDao.update(groupBuy);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addProduct_ext_group(Product_ext_groupBuy groupBuy) {
		
		try {
			commonDao.insert(groupBuy);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProduct_ext_group(String groupbuyId,HttpServletRequest request) {
		
		String sql = "update tb_product_ext_groupBuy set validflag=0,operid=?,lastUpdateTime=? where groupbuyId = "+groupbuyId+" and validflag=1"; 
		List<Product_ext_groupBuy> groupBuyList = commonDao.findByHql("from Product_ext_groupBuy where groupbuyId=? and validflag=1", groupbuyId);
		try {
			
			if(groupBuyList!=null && groupBuyList.size()>0){
				
				List<Order_detail> detailsList = order_detailService.getOrderdetailByMethod(0, null, groupbuyId,"2");
				
				if(detailsList!=null && detailsList.size()>0){
					for (Order_detail order_detail : detailsList) {
						List<Order_list> OrderList = order_listService.getOrder_listByMethod(PublicUtil.getOper(request),8,null,order_detail.getOrderId());
						if(OrderList!=null && OrderList.size()>0){
							Order_list orderList = OrderList.get(0);
								orderList.setStatus("02");
								orderList.setOperid(PublicUtil.getOper(request).getOperId());
								orderList.setLastUpdateTime(new Date());
								commonDao.update(orderList);
							}
						}
					}
				}
			return commonDao.executeBySql(sql,PublicUtil.getOper(request).getOperId(),new Date());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public boolean openGroupBuy(String groupbuyId,Integer statusId,HttpServletRequest request) {
		/* 如果开放立刻开团模式，不需要开团此类团购 */
		boolean bool = false;
		List<Product_ext_groupBuy> groupBuyList = commonDao.findByHql("from Product_ext_groupBuy where groupbuyId=? and validflag=1", groupbuyId);
		try {
			
			if(groupBuyList!=null && groupBuyList.size()>0){
				
				List<Order_detail> detailsList = order_detailService.getOrderdetailByMethod(0, null, groupbuyId,"2");
				
				if(detailsList!=null && detailsList.size()>0){
					for (Order_detail order_detail : detailsList) {
						List<Order_list> OrderList = order_listService.getOrder_listByMethod(PublicUtil.getOper(request),8,null,order_detail.getOrderId());
						if(OrderList!=null && OrderList.size()>0){
							Order_list orderList = OrderList.get(0);
							if(orderList.getStatus().equals("04"))
							{
								//status为1表示开团成功
								if(statusId==1){
									orderList.setStatus("06");
								}
								//status为0表示开团失败
								else if(statusId==0){
									orderList.setStatus("12");
								}
								orderList.setOperid(PublicUtil.getOper(request).getOperId());
								orderList.setLastUpdateTime(new Date());
								commonDao.update(orderList);
							}
							if(orderList.getStatus().equals("01"))
							{
								orderList.setStatus("02");
								orderList.setOperid(PublicUtil.getOper(request).getOperId());
								orderList.setLastUpdateTime(new Date());
								commonDao.update(orderList);
							}
						}
						
					}
				}
			
				Product_ext_groupBuy group = groupBuyList.get(0);
				group.setOperid(PublicUtil.getOper(request).getOperId());
				group.setLastUpdateTime(new Date());
				group.setOpenresult(statusId.shortValue());
				commonDao.update(group);
			}
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return bool;
	}

	@Override
	public boolean updateStockNumber(String groupbuyId, int number) {
		return commonDao.executeBySql("update tb_product_ext_groupbuy set stockNumber=stockNumber+? where groupbuyId=?", number,groupbuyId);
	}

	@Override
	public List<Product_ext_groupBuy> getPageGroupBuyList(PagePojo pagePojo) {
		return null;
	}

}







