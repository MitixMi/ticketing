package avion.com.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import avion.com.annotation.GET;
import avion.com.annotation.Restapi;
import avion.com.model.ModelView;

@WebServlet(name = "SampleController", urlPatterns = {"/sampleController"})
public class SampleController extends HttpServlet{

    // A REST API method returning a simple string response
    @Restapi
    @GET("/stringResponse") // URL to access this method
    public String getStringResponse(HttpServletRequest request, HttpServletResponse response) {
        return "Hello, REST API!";
    }
    
    // A REST API method returning an integer response
    @Restapi
    @GET("/intResponse") // URL to access this method
    public int getIntResponse(HttpServletRequest request, HttpServletResponse response) {
        return 42;
    }
    
    // A REST API method returning a ModelView object
    @Restapi
    @GET("/modelViewResponse") // URL to access this method
    public ModelView getModelViewResponse(HttpServletRequest request, HttpServletResponse response) {
        ModelView modelView = new ModelView("/WEB-INF/views/customView.jsp");
        modelView.addData("message", "Hello from ModelView");
        modelView.addData("status", "success");
        return modelView;
    }
}

