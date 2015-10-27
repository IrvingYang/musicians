package com.qushop.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.qushop.prod.entity.ProductType;

public class DatabaseSource {

	private static DruidDataSource dataSource;

	private static final Properties properties = new Properties();

	static {

		InputStream inputStream = new DatabaseSource().getClass()
				.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		dataSource = new DruidDataSource();
		dataSource.setUrl(properties.getProperty("jdbc.url").toString());
		dataSource.setUsername(properties.getProperty("jdbc.username")
				.toString());
		dataSource.setPassword(properties.getProperty("jdbc.password")
				.toString());
		dataSource.setDbType("mysql");
		dataSource.setDriverClassName(properties
				.getProperty("jdbc.driverClassName"));
		dataSource.setDefaultAutoCommit(false);
	}

	public static DruidPooledConnection getDataSourceConnection() {

		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void closeSqlOperation(ResultSet resultSet,
			PreparedStatement preparedStatement,
			DruidPooledConnection druidPooledConnection) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (druidPooledConnection != null) {
			try {
				druidPooledConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws SQLException {

		List alist = new ArrayList();

	}

	private static List getProductByParentId(String parentId)
			throws SQLException {

		DruidPooledConnection druidPooledConnection = DatabaseSource
				.getDataSourceConnection();
		PreparedStatement preparedStatement = druidPooledConnection
				.prepareStatement("select * from tb_producttype where parentId="
						+ parentId);

		ResultSet resultSet = preparedStatement.executeQuery();
		List<ProductType> productTypesList = new ArrayList<ProductType>();
		while (resultSet.next()) {
			ProductType productType = new ProductType();
			productType.setProductTypeId(resultSet.getString("productTypeId")
					+ "");
			productType.setTypeName(resultSet.getString("typeName"));
			productType.setParentId(resultSet.getString("parentId"));
			productTypesList.add(productType);
		}

		// if(productTypesList!=null && productTypesList.size()>0){
		// Map map = new HashMap();
		// for (ProductType productType : productTypesList) {
		// // System.out.println(productType.getTypeName());
		// List list =
		// getProductByParentId(alist,productType.getProductTypeId());
		// map.put("node", productType.getTypeName());
		// map.put("nodeList", productTypesList);
		// alist.add(map);
		// }
		// alist.add(map);
		// }
		// DatabaseSource.closeSqlOperation(resultSet, preparedStatement,
		// druidPooledConnection);
		// System.out.println(alist);
		return productTypesList;
	}

//	public static List getCatNode(String ParentId, ProductType productType)
//			throws SQLException {
//		List<ProductType> list = getProductByParentId(ParentId);
//		if (list != null && list.size() != 0) {
//			for (int i = 0; i < list.size(); i++) {
//				// productType.s
//				getCatNode(list.get(i).getProductTypeId(), list.get(i));
//			}
//		}
//	}
	// public List getCatNode(ParentId， CurrentNode){
	// List list = 查找ParentID的子节点;
	// if(list !=null && list.size()!=0){
	// for(int i=0;i<list.size();i++){
	// CurrentNode.添加子节点
	// getCatNode(list.get(i).prodtypeid, list.get(i));
	// }
	// }
	// }
}
