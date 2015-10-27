package com.zdy.springsecurity;
import java.util.Collection;
import java.util.Set;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.zdy.entity.Resources;
import com.zdy.entity.Role;
import com.zdy.service.ResourceManager;
/**
 * 自己实现的过滤用户请求类，也可以直接使用 FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * 
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * <!-- 用户是否拥有所请求资源的权限 -->
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
	@Resource(name = "resourceManager")
	public ResourceManager resourceManager;
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		//所请求的资源拥有的权限(一个资源对多个权限)  
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();  
        while(iterator.hasNext()) {  
            ConfigAttribute configAttribute = iterator.next();  
            //访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            Resources resources = resourceManager.findResourcesListByName(needPermission.trim());
            Set<Role> sysResourcesRole = resources.getRoleSet();
            Iterator<Role> it=sysResourcesRole.iterator();
            while(it.hasNext())
            {
            	Role u=(Role) it.next();
	            for(GrantedAuthority ga : authentication.getAuthorities()) {  
	                if (("ROLE_" + u.getName()).equals(ga.getAuthority().trim())) {  
                        return;  
                    }
	            }
            }
        }
        //没有权限  
        throw new AccessDeniedException(" 没有权限访问！ ");
	}

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
}