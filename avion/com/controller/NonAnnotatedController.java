package avion.com.controller;

import java.io.IOException;

// Controller with @WebServlet annotation
import jakarta.servlet.*;
import jakarta.servlet.http.*;

// Controller without @WebServlet annotation
public class NonAnnotatedController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implementation here
    }
}

