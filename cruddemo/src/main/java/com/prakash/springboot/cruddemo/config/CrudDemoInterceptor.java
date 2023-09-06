package com.prakash.springboot.cruddemo.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.prakash.springboot.cruddemo.utils.JwtTokenUtil;
import com.prakash.springboot.cruddemo.utils.Permission;

@Component
public class CrudDemoInterceptor implements HandlerInterceptor{
	
	 @Autowired
	    private JwtTokenUtil jwtTokenUtil;
	 
	 
	 private static Map <String, List<Permission>> permissionsMap = new HashMap<>();
	 
	 
	 static {
		 
		 List<Permission> endUserPermissions = new ArrayList <>();
		 endUserPermissions.add(new Permission("/api/employees/lists", "GET"));
		 endUserPermissions.add(new Permission("/api/employees/list/{employeeId}", "GET"));
		 
		 
		 
		 List<Permission> adminUserPermissions = new ArrayList <>();
		 adminUserPermissions.add(new Permission("/api/employees/create", "POST"));
		 adminUserPermissions.add(new Permission("/api/employees/edit", "PUT"));
		 adminUserPermissions.add(new Permission("/api/employees/delete/{employeeId}", "DELETE"));
		 
		 permissionsMap.put("END_USER",endUserPermissions);
		 permissionsMap.put("ADMIN_USER",adminUserPermissions);
	 }
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		 String accessToken = request.getHeader("Authorization");

	        System.out.println("End Point is: " + request.getRequestURI());
	        System.out.println("Method is: " + request.getMethod());

	        System.out.println("Role is: " + jwtTokenUtil.getRoleFromToken(accessToken));

	        String emailId = jwtTokenUtil.getUsernameFromToken(accessToken);

	        if (emailId == null || emailId.trim().length() == 0) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return false;
	        }

	        User userDetails = new User(emailId, "", new ArrayList<>());
	        if (jwtTokenUtil.validateToken(accessToken, userDetails)) {
	        	
	        	String url = request.getRequestURI().toString();
	        	String method = request.getMethod().toString(); 
	        	String userRole = jwtTokenUtil.getRoleFromToken(accessToken);
	        	
	        	
	        	List <Permission> permissions = permissionsMap.get(userRole);
	        	
	        	for ( Permission permission:permissions) {
	        		if ( permission.getUrl().equalsIgnoreCase(url)&& permission.getMethod().equalsIgnoreCase(method))
	        		{
	        			return true;	
	        		}
	        		return false;
	        	}
	        	
	            ;
	        }
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        return true;
	}
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse  response, 
		Object handler, ModelAndView modelAndView)throws Exception{
		System.out.println("Inside post handle");
        
    }

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse  response, 
		Object handler, Exception e)throws Exception{
		System.out.println("Inside after completion");
	
	}
	
}
