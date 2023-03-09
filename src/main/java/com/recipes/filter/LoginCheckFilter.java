package com.recipes.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.recipes.common.BaseContext;
import com.recipes.common.CustomException;
import com.recipes.common.R;
import com.recipes.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * 检查用户是否已经完成登录
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");//ip地址
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token, authorization");

        //获取本次请求的URL
        String requestURI = request.getRequestURI();
        log.info("拦截到请求:{}",request.getRequestURI());

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/user/**",
                "/employee/**"
        };

        //判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }


        //判断登录状态，如果已经登录，则直接放行
        String token = request.getHeader("token");
        Long userId = 888L;
        String username ="";
        String role = "";
        Boolean flag = true;
        try{
            userId = JwtUtil.getUserId(token);
            username = JwtUtil.getUsername(token);
            role = JwtUtil.getRole(token);
        }catch (Exception e){
            flag = false;
        }

        String[] employeeUrls = new String[]{
                "/backStage/**",
                "/common/**",
                "/menu/**",
                "/category/**"
        };
        String[] userUrls = new String[]{
                "/common/**",
                "/address/**",
                "/shoppingCart/**",
                "/category/**"
        };




        if(flag == true){
            log.info("用户已登录，username:{} id:{}",username,userId);
            log.info("当前线程id为{}",Thread.currentThread().getId());
            BaseContext.setUserId(userId);
            BaseContext.setUsername(username);
            if(role.equals("employee") && check(employeeUrls, requestURI))
            filterChain.doFilter(request,response);
            else if(role.equals("user") && check(userUrls, requestURI))
            filterChain.doFilter(request,response);
            else
            response.getWriter().write(com.alibaba.fastjson.JSON.toJSONString(R.error("非法请求"), SerializerFeature.BrowserCompatible));
            return;
        }

        log.info("用户未登录");
        //如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据

        response.getWriter().write(com.alibaba.fastjson.JSON.toJSONString(R.error("用户未登录"), SerializerFeature.BrowserCompatible));

    }

    /**
     * 路径匹配，检查本次请求是否放行
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match)return true;
        }
        return false;
    }


}
