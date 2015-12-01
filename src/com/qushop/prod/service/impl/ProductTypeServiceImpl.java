package com.qushop.prod.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Constants;
import com.qushop.prod.entity.ProductType;
import com.qushop.prod.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {


	@Resource
	CommonDao commonDao;
	
	@Override
	@SuppressWarnings("all")
	public List getProductType() {
		
		List<ProductType> rootProductTypesList = commonDao.findByHql("from ProductType where parentId=? and validflag = 1", Constants.TOP_PROD_TYPE_PARENT);
		
		for (ProductType rootProductType : rootProductTypesList) {
			
			List<ProductType> secondProductTypesList = commonDao.findByHql("from ProductType where parentId=? and validflag = 1", rootProductType.getProductTypeId());
			rootProductType.setChildTypeList(secondProductTypesList);
			List ses = new ArrayList();
			for (ProductType secondProductType : secondProductTypesList) {
				List<ProductType> thirdlyProductTypesList = commonDao.findByHql("from ProductType where parentId=? and validflag = 1", secondProductType.getProductTypeId());
				secondProductType.setChildTypeList(thirdlyProductTypesList);
			}
		}
		return rootProductTypesList;
	}

	
	@Override
	@SuppressWarnings("all")
	public void getProductType2(List levelProductTypeList, ProductType currentnode) {
		String parentid;
		if(currentnode == null){
			parentid=Constants.TOP_PROD_TYPE_PARENT;
		}else{
			parentid = currentnode.getProductTypeId();
		}
		
		List<ProductType> templist = commonDao.findByHql("from ProductType where parentId = ? and validflag=1", parentid);

		for(ProductType tempProductType : templist){
			
			List<ProductType> list = commonDao.findByHql("from ProductType where parentId = ? and validflag=1", tempProductType.getProductTypeId());
			if(list!=null && list.size()>0){
				tempProductType.setChildTypeList(list);
				getProductType2(levelProductTypeList,tempProductType);
			}
			levelProductTypeList.add(tempProductType);
		}
	}

	@Override
	public List<ProductType> getProductTypeByMethod(int type,String...params){
		
		List<ProductType> productTypesList = new ArrayList();
		String sql = null;
		switch (type) {
		case 0:
			productTypesList = commonDao.findByHql("from ProductType where productTypeId=? and validflag=1", params[0]);
			for (ProductType productType : productTypesList) {
				List<ProductType> parentProductType = commonDao.findByHql("from ProductType where productTypeId=? and validflag=1",productType.getParentId());
				if(parentProductType!=null && parentProductType.size()>0){
					productType.setParentProductType(parentProductType.get(0));
				}
			}
			break;
			
		case 1:
			sql = "select * from tb_productType WHERE parentId = (select parentId from tb_productType where productTypeId=? and validflag=1) and validflag=1 order by RAND() limit 5";
			productTypesList = commonDao.findBySql(sql, ProductType.class, params[0]);
			break;
			
		case 2:
			productTypesList = commonDao.findByHql("from ProductType where parentId=? and validflag=1", params[0]);
			break;
			
		case 3:
			sql = "SELECT tb_productType.productTypeId, tb_productType.LogImagePath, tb_productType.typeName, tb_productType.parentId,"
						+"tb_productType.description, tb_productType.properties,tb_productType.operid,tb_productType.lastUpdateTime,"
						+ "tb_productType.validflag FROM tb_productType WHERE "
						+"LENGTH(tb_productType.productTypeId) = ? and validflag=1";
			productTypesList = commonDao.findBySql(sql, ProductType.class,params[0]);
			for (ProductType productType : productTypesList) {
				List<ProductType> parentProductType = commonDao.findByHql("from ProductType where productTypeId=? and validflag=1",productType.getParentId());
				if(parentProductType!=null && parentProductType.size()>0){
					productType.setParentProductType(parentProductType.get(0));
				}
			}
			break;
		case 4:
			productTypesList = commonDao.findByHql("from ProductType where typeName=? and validflag=0", params[0]);
			break;
		case 5:
			productTypesList = commonDao.findByHql("from ProductType where parentId=? and validflag=1", Constants.TOP_PROD_TYPE_PARENT);
			break;
		case 6:
		 	int level = params[0].length()/2;
	        String currenttype = params[0];
	        while ( level > 0){       	
	        	productTypesList.add((ProductType) commonDao.findByHql("from ProductType where productTypeId=? and validflag=1 order by parentId asc", currenttype).get(0));
	        	currenttype = currenttype.substring(0,currenttype.length()-2);    	
	        	level--;
	        }
	        break;
		case 7:
			productTypesList = commonDao.findByHql("from ProductType where validflag=1 order by parentId asc ");
			for (ProductType productType : productTypesList) {
				if(!productType.getParentId().equals(Constants.TOP_PROD_TYPE_PARENT)){
					 List<ProductType> parentProductTypesList = commonDao.findByHql("from ProductType where productTypeId=? and validflag=1 order by parentId asc",productType.getParentId());
					 if(parentProductTypesList!=null && parentProductTypesList.size()>0){
						 ProductType parentProductType = parentProductTypesList.get(0);
						 productType.setParentProductType(parentProductType);
					 }
				}
			}
			break;
			
		case 8:
			productTypesList = commonDao.findByHql("from ProductType where productTypeId=?", params[0]);
			break;
		case 9:
			productTypesList = commonDao.findByHql("from ProductType where parentId=?", params[0]);
		default:
			break;
		}
		
		return productTypesList;
	}
	

	@Override
	public boolean addProductType(ProductType productType) {
		
		List<String> list = commonDao.findBySql("select max(productTypeId) from tb_productType where parentId = ? ", null,productType.getParentId());
		String maxProductTypeId = list.get(0);
			String productTypeId = "";
			if(maxProductTypeId==null){
				productTypeId= "01";
				String parentId = productType.getParentId();
				if(parentId.trim().equals(Constants.TOP_PROD_TYPE_PARENT)){
					productType.setProductTypeId(productTypeId);
				}else{
					productType.setProductTypeId(parentId+productTypeId);
				}
			}else{
				maxProductTypeId = maxProductTypeId.substring(maxProductTypeId.length()-2);
				int tempInt = Integer.parseInt(maxProductTypeId);
				if(tempInt>=99){
					return false; 
				}
				String tempTypeId = (tempInt+1)+"";
				for (int i = tempTypeId.length(); i < maxProductTypeId.length(); i++) {
					productTypeId+="0";
				}
				productTypeId+=tempTypeId;

				if(productType.getParentId().trim().equals(Constants.TOP_PROD_TYPE_PARENT)){
					productType.setProductTypeId(productTypeId);
				}else{
					productType.setProductTypeId(productType.getParentId()+productTypeId);
				}
			}
			commonDao.insert(productType);
			return true;
	}

	@Override
	public boolean updateProductType(ProductType productType) {
			commonDao.update(productType);
			return true;
	}
	
	@Override
	public boolean deleteProductType(String productTypeId,String operId){
		
		String sql = "update tb_productType set validflag=0,lastUpdateTime=?,operid=? where productTypeId in ("+productTypeId+")";
		boolean bool = commonDao.executeBySql(sql,new Date(),operId);
		return bool;
	}

	@Override
	public boolean existsChildrenProductOfProductTypeId(String productTypeId) {
		
		Integer count =  Integer.parseInt(commonDao.findBySql("select count(*) from tb_productType where parentId in ("+productTypeId+") and validflag=1",null).get(0)+"");
		
		return count>0?true:false;
	}


	@Override
	public String getParentProductTypeId(String productTypeId) {
		String parentTypeId = (String)commonDao.findBySql("select parentId from tb_productType where productTypeId in ("+productTypeId+") and validflag=1",null).get(0);
		return parentTypeId;
	}
	
}
