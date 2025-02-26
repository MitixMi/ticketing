package avion.com.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import avion.com.annotation.GET;
import avion.com.model.ModelView;

@WebServlet(name = "AnnotatedController", urlPatterns = {"/annotatedController"})
public class AnnotatedController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("GET request handled by AnnotatedController");
    }

    @GET("/custom")
    protected ModelView customGetMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelView mv = new ModelView("/WEB-INF/views/customView.jsp");
        mv.addObject("message", "Custom GET request handled by customGetMethod");
        return mv;
    }

    @GET("/customa")
    protected String customGetMethods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "Custom GET request handled by customGetMethods";
    }
}
