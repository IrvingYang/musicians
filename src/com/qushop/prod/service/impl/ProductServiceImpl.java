package com.qushop.prod.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Constants;
import com.qushop.common.util.Hanyu;
import com.qushop.common.util.ImgCompress;
import com.qushop.common.util.PublicUtil;
import com.qushop.dict.service.Brand_vendorService;
import com.qushop.dict.service.CityService;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.entity.ProductTrack;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductReviewService;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.ProductTrackService;
import com.qushop.prod.service.ProductTypeService;
import com.qushop.provider.service.ProviderService;
import com.qushop.user.entity.Oper;
import com.qushop.user.service.WishlistService;

/**
 * 
 * 业务层，根据业务需要可含不同的mapper
 * 
 * @author xie
 * 
 */
@Service
@SuppressWarnings("unchecked")
public class ProductServiceImpl implements ProductService {

	@Resource
	CommonDao commonDao;
	
	@Resource
	ProductImgService productImgService;
	
	@Resource
	ProviderService providerService;
	
	@Resource
	Brand_vendorService brandservice;
	
	@Resource
	CityService cityService;
	
	@Resource
	ProductTypeService productTypeService;
	
	@Resource
	ProductTrackService productTrackService;

	@Resource
	WishlistService wishlistService;
	
	@Resource
	ProductReviewService productReviewService;
	

	@Override
	public List<Product> getProductListByMethod(Integer type,Oper oper,String...params){


		List<Product> productsList = null;
		
		StringBuffer hql  = new StringBuffer("from Product where  ");
		
		switch (type) {
			case 0:
				break;
			case 1:
				if(oper.getPartnerflag()==1){
					hql.append(" providerId='"+oper.getProviderId() + "' and validflag = 1");
					productsList = commonDao.findByHql(hql.toString());
				}
				else if(oper.getPartnerflag()==0){
					hql.append(" validflag=1 order by providerId asc,ontime desc");
					productsList = commonDao.findByHql(hql.toString());
				}
				if (productsList != null && productsList.size() > 0) {
					for (Product product : productsList) {
						product.setProvider(providerService.getProviderByMethod(5, null, product.getProviderId()).get(0));
						product.setBrand_vendor(brandservice.getVendorByMethod(4, product.getBrandid()).get(0));
						product.setCity(cityService.getCityByMethod(4, product.getProductCityId()).get(0));
						product.setProduct_type(productTypeService.getProductTypeByMethod(8, product.getProductTypeId()).get(0));
					}
				}
				break;
				
			case 2:
				hql.append(" productId='"+params[0]+"'");
				productsList = commonDao.findByHql(hql.toString());
				if (productsList != null && productsList.size() > 0) {
					for (Product product : productsList) {
						product.setProvider(providerService.getProviderByMethod(5,null, product.getProviderId()).get(0));
						product.setBrand_vendor(brandservice.getVendorByMethod(4, product.getBrandid()).get(0));
						product.setCity(cityService.getCityByMethod(4, product.getProductCityId()).get(0));
						product.setProduct_type(productTypeService.getProductTypeByMethod(8, product.getProductTypeId()).get(0));
						List<ProductTrack> productTracksList = productTrackService.getProductTrackByMethod(1, null, product.getProductId());
						List<ProductImg> productImgsList = productImgService.getProductImgByMethod(4, product.getProductId());
						product.setProductimglist(productImgsList);
						if(productTracksList.size()>0){
							product.setProducttrack(productTracksList.get(0));
						}
					}
				}
				break;
			case 3:
				hql.append(" providerId='"+params[0]+"'  and validflag = 1");
				productsList = commonDao.findByHql(hql.toString());
				for (Product product : productsList) {
					List<ProductImg> productImgsList = productImgService.getProductImgByMethod(1, product.getProductId(),"6");
					product.setProductimglist(productImgsList);
				}
				break;
			case 4:
				hql.append(" productId='"+params[0]+"' ");
				productsList = commonDao.findByHql(hql.toString());
				break;
				
		}
		
		return productsList;
		
	}
	
	/**
	 * 0 查询允许商城的商品 1查询允许团购的商品 2查询允许大宗交易的商品
	 * @return
	 */
	@Override
	public List<Product> getAllowProducts(int type,String ...params) {
		
		
		String sql = "SELECT * "+
			"FROM "+
			"tb_product AS product "+
			"WHERE ";
		switch (type) {
		case 0:
			sql += "SUBSTRING(product.salesrange,1,1)='1' and "+ 
					"product.providerId=? and product.validflag=1 "
					+ " and productId not in (select shop.productId from tb_product_ext_shop shop where shop.validflag = 1)";
			break;
		case 1:
			sql += "SUBSTRING(product.salesrange,2,1)='1' and "+ 
					"product.providerId=? and product.validflag=1 "
					+ " and productId not in (select groupbuy.productId from tb_product_ext_groupbuy groupbuy where groupbuy.validflag = 1)";
			break;
		case 2:
			sql += "SUBSTRING(product.salesrange,3,1)='1' and "+ 
					"product.providerId=? and product.validflag=1 "
					+ " and productId not in (select bigdeal.productId from tb_product_ext_bigdeal bigdeal where bigdeal.validflag = 1)";
			break;

		default:
			break;
		}
		
		List<Product> productsList = commonDao.findBySql(sql, Product.class,params[0]);
		
		for (Product product : productsList) {
			product.setProvider(providerService.getProviderByMethod(1,null, product.getProviderId()).get(0));
			product.setBrand_vendor(brandservice.getVendorByMethod(4, product.getBrandid()).get(0));
			product.setCity(cityService.getCityByMethod(4, product.getProductCityId()).get(0));
			product.setProduct_type(productTypeService.getProductTypeByMethod(8, product.getProductTypeId()).get(0));
		}
		
		return productsList;
	}

	@Override
	public boolean addorupdate(int type,String[] images, Product product,
			HttpServletRequest request) {

		String shop = request.getParameter("shop");
		shop = (shop == null || shop.equals("")) ? "0" : shop;
		String group = request.getParameter("group");
		group = (group == null || group.equals("")) ? "0" : group;
		String bigdeal = request.getParameter("bigdeal");
		bigdeal = (bigdeal == null || bigdeal.equals("")) ? "0" : bigdeal;
		String trackInfo = request.getParameter("trackInfo");
		try {
			product.setSalesrange(shop + group + bigdeal);
			if(product.getProductGrade() == 0 ){
				product.setProductGrade(Constants.PROD_DEFAULT_GRADE);
			}
			product.setValidflag((short) 1);
			product.setLastUpdateTime(new Date());
			product.setOffTime(null);
			product.setOperid(PublicUtil.getOper(request).getOperId());

			//type 0新增 1修改
			if(type==0){

				product.setOnTime(new Date());
				product.setProductId(product.getProductTypeId()
						+ product.getProviderId()
						+ (new Random().nextInt(8999) + 1000));

				commonDao.insert(product);
			}else if(type==1){
				commonDao.update(product);
				productImgService.deleteProductImgPhysically(0,product.getProductId());
			}
			
			//查询溯源包含无效数据
			List<ProductTrack> productTracksList = productTrackService.getProductTrackByMethod(1, null, product.getProductId());

			ProductTrack productTrack = null;
			
			//如果无数据
			if(productTracksList.size()<=0)
			{
				if(trackInfo!=null && !trackInfo.trim().equals(""))
				{
					productTrack = new ProductTrack();
					productTrack.setLastUpdateTime(new Date());
					productTrack.setOperid(PublicUtil.getOper(request).getOperId());
					productTrack.setProductId(product.getProductId());
					productTrack.setValidflag((short)1);
					productTrack.setTrackInfo(trackInfo);
					productTrackService.addProductTrack(productTrack);
				}
			}
			//反之
			else
			{
				productTrack = productTracksList.get(0);
				productTrack.setLastUpdateTime(new Date());
				productTrack.setOperid(PublicUtil.getOper(request).getOperId());
				if(trackInfo!=null && !trackInfo.trim().equals(""))
				{
					productTrack.setValidflag((short)1);
					productTrack.setTrackInfo(trackInfo);
				}
				else
				{
					productTrack.setValidflag((short)0);
					productTrack.setTrackInfo("");
				}
				productTrackService.updateProductTrack(productTrack);
			}
			
			
			//得到根目录
			String rootPath = PublicUtil.getRootFileDirectory(request);
			//基本文件名
			String fileName = new Hanyu().getStringPinYin(product.getProductName() );
			/*************************************************************************************************************/
			String groupbigdealimg = request.getParameter("groupbigdealimg");
			
			if(groupbigdealimg!=null && !"".equals(groupbigdealimg)){
				
				productImgService.deleteProductImgPhysically(1,product.getProductId());
				
				//获取后缀
				String groupbigdealimgsuffix = groupbigdealimg.substring(groupbigdealimg.lastIndexOf("."));
				//获取图片所在文件跟目录
				String groupbigdealimgimageRootUrl = rootPath
						+ groupbigdealimg.substring(0, groupbigdealimg.lastIndexOf("/")) + "/";
				
				//得到无前缀的图片路径（相对）
				String noPrevGroupbigdealimg = groupbigdealimgimageRootUrl.replace(rootPath, "");
				
//				String _thumbs = noPrevGroupbigdealimg.replace(PublicUtil.getOper(request).getProviderId() + "/i", PublicUtil.getOper(request).getProviderId() + "/_thumbs/I");
				
//				//获取ckfinder中的缩略图（非程序生成）
//				File _thumbsThumbnail = new File(rootPath+_thumbs + "thumbnail/");
//				File files[] = _thumbsThumbnail.listFiles();
//				//全部删除
//				for (File file : files) {
//					file.delete();
//				}
				
				File groupbigdealImgthumbnail = new File(groupbigdealimgimageRootUrl + "thumbnail/");
				
				if(!groupbigdealImgthumbnail.exists()){
					groupbigdealImgthumbnail.mkdirs();
				}
				
				File compressImg6 = new File(groupbigdealimgimageRootUrl + "thumbnail/" + fileName + "_" + "6"
						+ groupbigdealimgsuffix);
				if (!compressImg6.exists()) {
					compressImg6.createNewFile();
				}
	
				ImgCompress groupbigdealimgCompress = new ImgCompress(rootPath + groupbigdealimg);
	
				groupbigdealimgCompress.resizeFix(350, 220, compressImg6);
				
				ProductImg productImg5 = new ProductImg();
				ProductImg productImg6 = new ProductImg();
				
				productImg5.setImgType((short) 5);
				productImg5.setPath(groupbigdealimg);
				productImg5.setProductId(product.getProductId());
				productImg5.setUploadTime(new Date());
				productImg5.setValidflag((short) 1);
				productImg5.setSerialNumber((short) 1);
	
				productImg6.setImgType((short) 6);
				productImg6.setPath(noPrevGroupbigdealimg + "thumbnail/" + fileName + "_" + "6"
						+ groupbigdealimgsuffix);
				productImg6.setProductId(product.getProductId());
				productImg6.setUploadTime(new Date());
				productImg6.setValidflag((short) 1);
				productImg6.setSerialNumber((short) 1);
	
				commonDao.insert(productImg5);
				commonDao.insert(productImg6);
			}
			
			/************************************************************************************************************/
			
			if (images != null && images.length > 0) {
				for (int i = 0;i<images.length;i++) {
					
					String img = images[i];
					//获取后缀
					String suffix = img.substring(img.lastIndexOf("."));
					//获取图片所在文件跟目录
					String imageRootUrl = rootPath
							+ img.substring(0, img.lastIndexOf("/")) + "/";
					
					//得到无前缀的图片路径（相对）
					String noPrevRootPath = imageRootUrl.replace(rootPath, "");
					
					String _thumbs = noPrevRootPath.replace(PublicUtil.getOper(request).getProviderId() + "/i", PublicUtil.getOper(request).getProviderId() + "/_thumbs/I");
					
					//获取ckfinder中的缩略图（非程序生成）
					File _thumbsThumbnail = new File(rootPath+_thumbs + "thumbnail/");
					File files[] = _thumbsThumbnail.listFiles();
					if(files!=null && files.length>0){
						//全部删除
						for (File file : files) {
							file.delete();
						}
					}
					
					File thumbnail = new File(imageRootUrl + "thumbnail/");
					
					if(!thumbnail.exists()){
						thumbnail.mkdirs();
					}
//					File t = new File(rootPath + "/" + PublicUtil.getOper(request) + "_thumbs\Images")
				
					File compressImg2 = new File(imageRootUrl + "thumbnail/" + fileName + "_" + (i+1) + "2"
							+ suffix);
					if (!compressImg2.exists()) {
						compressImg2.createNewFile();
					}

					File compressImg3 = new File(imageRootUrl + "thumbnail/" + fileName + "_" +  (i+1) + "3"
							+ suffix);
					if (!compressImg3.exists()) {
						compressImg3.createNewFile();
					}
					File compressImg4 = new File(imageRootUrl + "thumbnail/" + fileName + "_" +  (i+1) + "4"
							+ suffix);
					if (!compressImg4.exists()) {
						compressImg4.createNewFile();
					}
					//创建压缩对象
					ImgCompress compress = new ImgCompress(rootPath + img);//imageUrl
					//压缩相应图片到相应路径
					compress.resizeFix(450, 450, compressImg2);
					compress.resizeFix(250, 250, compressImg3);
					compress.resizeFix(120, 120, compressImg4);

					ProductImg productImg1 = new ProductImg();
					ProductImg productImg2 = new ProductImg();
					ProductImg productImg3 = new ProductImg();
					ProductImg productImg4 = new ProductImg();

					productImg1.setImgType((short) 1);
					productImg1.setPath(img);
					productImg1.setProductId(product.getProductId());
					productImg1.setUploadTime(new Date());
					productImg1.setValidflag((short) 1);
					productImg1.setSerialNumber((short) (i+1));

					productImg2.setImgType((short) 2);
					productImg2.setPath(noPrevRootPath + "thumbnail/" + fileName + "_" +  (i+1) + "2"
							+ suffix);
					productImg2.setProductId(product.getProductId());
					productImg2.setUploadTime(new Date());
					productImg2.setValidflag((short) 1);
					productImg2.setSerialNumber((short) (i+1));

					productImg3.setImgType((short) 3);
					productImg3.setPath(noPrevRootPath + "thumbnail/" + fileName + "_" +  (i+1) + "3"
							+ suffix);
					productImg3.setProductId(product.getProductId());
					productImg3.setUploadTime(new Date());
					productImg3.setValidflag((short) 1);
					productImg3.setSerialNumber((short) (i+1));

					productImg4.setImgType((short) 4);
					productImg4.setPath(noPrevRootPath + "thumbnail/" + fileName + "_" +  (i+1) + "4"
							+ suffix);
					productImg4.setProductId(product.getProductId());
					productImg4.setUploadTime(new Date());
					productImg4.setValidflag((short) 1);
					productImg4.setSerialNumber((short) (i+1));

					commonDao.insert(productImg1);
					commonDao.insert(productImg2);
					commonDao.insert(productImg3);
					commonDao.insert(productImg4);

				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("新增或者修改产品出错");
		}
	}

	@Override
	public boolean deleteProduct(String productId,Oper oper) {

		String productsql = "update tb_product set validflag=0,lastUpdateTime=?,operid=? where productId in(" + productId + ") and validflag=1";
		boolean bool =false;
		try {
			bool = productTrackService.deleteProductTrack(productId, oper);
			bool = productReviewService.deleteProductReviewByProduct(productId, oper);
			bool = wishlistService.deleteWishListByProduct(productId);
			bool = productImgService.deleteProductImgPhysically(3, productId);
			bool = commonDao.executeBySql(productsql,new Date(),oper.getOperId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean existsProductInProductType(String productTypeId) {
		
		Integer count = Integer.parseInt(commonDao.findBySql("select count(*) from tb_product where productTypeId in ("+productTypeId+") and validflag = 1",null).get(0)+"");
		
		return count>0?true:false;
	}
	
	@Override
	public boolean updateStockNumber(String productId, int number) {
		
		return commonDao.executeBySql("update tb_product set stockNumber=stockNumber+? where productId=? and validflag=1", number,productId);
		
	}

	@Override
	public Integer getProductCountByBrandVendor(String brandIds) {
		List brandVendorCount = commonDao.findBySql("select count(*) from tb_product where brandid in ("+brandIds+") and validflag=1", null);
		return Integer.valueOf(brandVendorCount.get(0)+"");
	}

	@Override
	public Integer getProductCountByCityId(String cityId) {
		
		List cityCount =  commonDao.findBySql("select count(*) from tb_product where productCityId in ("+cityId+") and validflag=1", null);
		return Integer.valueOf(cityCount.get(0)+"");
	}

}
