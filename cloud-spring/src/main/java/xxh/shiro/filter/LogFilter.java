package xxh.shiro.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: elyuan
 * @Date: 2020/11/27 5:26 下午
 */
public class LogFilter  implements Filter  {


    private static Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filterConfig:{}",filterConfig);
        logger.info("初始化filter");
    }
    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req =(HttpServletRequest)request;
        HttpServletResponse resp =(HttpServletResponse)response;
        logger.info("执行filter................");
        chain.doFilter(req, resp);
    }



    @Override

    public void destroy() {
        logger.info("filter被销毁");
    }










}
