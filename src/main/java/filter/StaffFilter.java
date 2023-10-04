package filter;

import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/order/*")
public class StaffFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            ((HttpServletResponse)response).sendRedirect("/login?message=You_need_Login");
            return;
        }
        if((!user.getRole().getRoleName().equalsIgnoreCase("Staff"))||(!user.getRole().getRoleName().equalsIgnoreCase("Admin"))){
            ((HttpServletResponse)response).sendRedirect("/login?message=You_need_Login");
            return;
        }
        chain.doFilter(request, response);
    }
}
