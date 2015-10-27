package com.qushop.common.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.log4j.Logger;

import com.qushop.common.web.InitApp;


/**
 * 
 * 
 * @author xie
 *
 */
public class CachedManager {
	
	public static MemcachedClientBuilder memcachedBuilder = null;
	public static MemcachedClient memcachedClient = null;
	private static final CachedManager cachedManager= new CachedManager();
	private static final Logger log = Logger.getLogger(CachedManager.class);
	
	private CachedManager(){
		//得到IP地址
		String memcachedIp = InitApp.getPro().getProperty("memcachedIp");
		Map<InetSocketAddress,InetSocketAddress> addressIp = new HashMap<InetSocketAddress,InetSocketAddress>();
		addressIp.put(AddrUtil.getOneAddress(memcachedIp), AddrUtil.getOneAddress(memcachedIp));
		//开始创建客户端默认权重
		memcachedBuilder = new XMemcachedClientBuilder(addressIp);
		try {
			//建立客户端
			memcachedClient = memcachedBuilder.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static CachedManager getInstance(){
		return cachedManager;
	}
	
	public static void set(String key,String value){
		log.info("set memcached....");
		try {
			memcachedClient.set(key, 0, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void replace(String key,String value){
		try {
			memcachedClient.replace(key, 0, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(String key){
		try {
			memcachedClient.delete(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String get(String key){
		log.info("get memcached....");
		try {
			return memcachedClient.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void flushAll(){
		log.info("flushAll memcached....");
		try {
			memcachedClient.flushAll();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static Map<String, String> getAllValueOfMap(){
		
		Map<String,String> value = new HashMap<String,String>();
		String memcachedIp = InitApp.getPro().getProperty("memcachedIp");
		KeyIterator keyIterator = null;
		try {
			keyIterator = memcachedClient.getKeyIterator(AddrUtil.getOneAddress(memcachedIp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(keyIterator.hasNext()){
			try {
				String key = keyIterator.next()+"";
				String val = memcachedClient.get(key)+"";
				value.put(key, val);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
		
	}
	public static void main(String[] args) {
		CachedManager cachedManager = CachedManager.getInstance();
		Map<String,String> map = cachedManager.getAllValueOfMap();
		for (String key: map.keySet()) {
		}
	}
}
