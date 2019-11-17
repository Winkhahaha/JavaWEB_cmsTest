package edu.xalead.web.servlet.backend;

import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "CheckLoginFilter", urlPatterns = {"/backend/*"})
public class CheckLoginFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestUri = request.getRequestURI();
        if (request.getSession().getAttribute(User.LOGIN_USER) != null ||
                !requestUri.matches("\\/backend\\/\\w+(\\.jsp|Servlet)") ||
                requestUri.equals("/backend/login.jsp"))
            chain.doFilter(req, resp);
        else
            request.getRequestDispatcher("/backend/login.jsp").forward(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
