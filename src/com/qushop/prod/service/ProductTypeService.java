package com.qushop.prod.service;

import java.util.List;

import com.qushop.prod.entity.ProductType;

public interface ProductTypeService {

	public List getProductType();
	
	public void getProductType2(List levelProductTypeList, ProductType currentnode);
	
	public boolean addProductType(ProductType productType);

	public boolean updateProductType(ProductType productType);

	public boolean deleteProductType(String productTypeId,String operId);
	
	public boolean existsChildrenProductOfProductTypeId(String productTypeId);
	
	/**
	 * 
	 * @param type 0 取详细    1取兄弟类型   2取子类型 3获取指定级别类型  4 查询已经取消的类型
	 *  5取根类型 6查询导航栏产品分类  7查询全部有效产品类型 8查询单个包含validflag=0
	 * @param params 插叙参数
	 * @return
	 */
	public List<ProductType> getProductTypeByMethod(int type,String...params);
	
}
