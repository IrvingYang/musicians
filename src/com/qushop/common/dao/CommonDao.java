package com.qushop.common.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 谢龙飞
 *
 * @param <T>
 */
@Component
@SuppressWarnings("unchecked")
public class CommonDao<T> // extends HibernateDaoSupport{
{

	@Autowired
	SessionFactory sessionFactory;

	/**
	 * getSession
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 
	 * @param obj
	 */
	public Serializable insert(final Object obj) {
		Serializable s = getSession().save(obj);
		this.getSession().flush();
		return s;
	}

	/**
	 * 修改
	 * 
	 * @param obj
	 */
	public void update(final Object obj) {

		this.getSession().update(obj);
		this.getSession().flush();
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param obj
	 */
	public void delete(final Object obj) {
		this.getSession().delete(obj);
		this.getSession().flush();
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param cls
	 * @param id
	 */
	public void delete(final Class<T> cls, final Integer id) {
		T t = (T) this.getSession().get(cls, id);
		this.getSession().delete(t);
		this.getSession().flush();
	}

	/**
	 * 
	 * 查询单个
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public T get(final Class<T> cls, final Integer id) {
		return (T) this.getSession().get(cls, id);
	}

	/**
	 * 查询列表所有
	 * 
	 * @param cls
	 * @return
	 */
	public List<T> findAll(final Class<T> cls) {

		List<T> list = this.getSession().createCriteria(cls).setCacheable(true).list();
		return list;
	}

	/**
	 * 
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> findByHql(final String hql, final Object... params) {
		Query query = this.getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.setCacheable(true).list();
	}

	/**
	 * 
	 * @param cls
	 * @param params
	 * @return
	 */
	public List<T> findEqMap(final Class<T> cls, final Map<String, String> params) {
		Criteria cri = this.getSession().createCriteria(cls);
		if (params != null) {
			for (String key : params.keySet()) {
				cri.add(Restrictions.eq(key, params.get(key)));
			}
		}
		return cri.setCacheable(true).list();
	}

	/**
	 * Map映射
	 * 
	 * @param cls
	 * @param params
	 * @return
	 */
	public List<T> findLikeMap(final Class<T> cls, final Map<String, String> params) {
		Criteria cri = this.getSession().createCriteria(cls);
		for (Iterator<?> iter = params.keySet().iterator(); iter.hasNext();) {
			String key = iter.next() + "";
			String value = params.get(key) + "";
			cri.add(Restrictions.like(key, value, MatchMode.ANYWHERE));
		}
		return cri.setCacheable(true).list();
	}

	/**
	 * 通过hql查询分页
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<T> findPagingByHql(final String hql, Integer pageNo, Integer pageSize, final Object... params) {

		Query query = this.getSession().createQuery(hql);
		if (pageNo == null || pageNo <= 0) {
			pageNo = 0;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		query.setFirstResult(pageNo * pageSize);
		query.setMaxResults(pageSize);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.setCacheable(true).list();
	}

	/**
	 * 通过hql查询分页
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<T> findPagingBySql(final String sql, int pageNo, int pageSize, Class<T> cls, final Object... params) {

		SQLQuery query = this.getSession().createSQLQuery(sql);
		if (pageNo <= 0) {
			pageNo = 0;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}
		query.setFirstResult(pageNo * pageSize);
		query.setMaxResults(pageSize);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (cls != null) {
			return query.addEntity(cls).setCacheable(true).list();
		} else {
			return query.list();
		}

	}

	/**
	 * 通过类查询分页
	 * 
	 * @param cls
	 * @param pageNo
	 * @param pageSize
	 * @param equalMap
	 * @param likeMap
	 * @return
	 */
	public List<T> findPagingByClass(final Class<T> cls, Integer pageNo, Integer pageSize,
			final Map<String, String> equalMap, final Map<String, String> likeMap) {

		Criteria cri = this.getSession().createCriteria(cls);
		cri.add(Restrictions.allEq(equalMap));
		for (Iterator<?> iter = likeMap.keySet().iterator(); iter.hasNext();) {
			String key = iter.next() + "";
			String value = likeMap.get(key) + "";
			cri.add(Restrictions.like(key, value, MatchMode.ANYWHERE));
		}
		if (pageNo == null || pageNo <= 0) {
			pageNo = 0;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		cri.setFirstResult(pageNo * pageSize);
		cri.setMaxResults(pageSize);
		return cri.setCacheable(true).list();

	}

	/**
	 * sql执行修改
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Boolean executeBySql(final String sql, Object... params) {

		try {
			SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					sqlQuery.setParameter(i, params[i]);
				}
			}
			int count = sqlQuery.executeUpdate();
			return count > 0 ? new Boolean(true) : new Boolean(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public List<T> findBySql(String sql, Class<T> cls, Object... params) {

		SQLQuery query = this.getSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (cls != null) {
			return query.addEntity(cls).setCacheable(true).list();
		}
		return query.list();
	}
}
