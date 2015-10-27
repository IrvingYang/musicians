package com.qushop.common.util;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class AuthorityInterceptor implements MethodInterceptor{


	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println(invocation.getMethod().getName());
		return invocation.proceed();
	}

}
