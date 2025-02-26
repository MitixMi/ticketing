package avion.com.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import avion.com.annotation.GET;
import avion.com.annotation.Param;
import avion.com.model.ModelView;

@WebServlet(name = "NameController", urlPatterns = {"/NameController"})
public class NameController extends HttpServlet {

    @GET("/submit")
    public ModelView submitName(@Param(name = "name") String name) {
        ModelView modelView = new ModelView("/WEB-INF/views/displayName.jsp");
        modelView.addData("name", name);
        return modelView;
    }
}
