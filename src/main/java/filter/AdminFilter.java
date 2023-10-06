package filter;

import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        User user = (User) session.getAttribute("user");
        String url = ((HttpServletRequest) request).getRequestURI();
        if(url.contains("assets")){
            chain.doFilter(request, response);
        }else{
            if(user == null){
                ((HttpServletResponse)response).sendRedirect("/login?message=You_need_Login");
                return;
            }
            if(!user.getRole().getRoleName().equalsIgnoreCase("Admin")){
                ((HttpServletResponse)response).sendRedirect("/login?message=You_need_Login");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
