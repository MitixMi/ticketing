package avion.com.filters;

import avion.com.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class BackofficeFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null || !user.getRole().equals("admin")) {
            response.sendRedirect("/listControllers/login");
        } else {
            chain.doFilter(req, res);
        }
    }
}
