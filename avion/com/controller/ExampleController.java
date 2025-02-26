package avion.com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import avion.com.annotation.GET;
import avion.com.annotation.POST;

// Controller class example
public class ExampleController {

    @GET("/getEmployee")
    public String getEmployee(HttpServletRequest request, HttpServletResponse response) {
        return "GET Employee";
    }

    @POST("/createEmployee")
    public String createEmployee(HttpServletRequest request, HttpServletResponse response) {
        return "POST Employee Created";
    }

    // No annotation means GET by default
    public String defaultGet(HttpServletRequest request, HttpServletResponse response) {
        return "Default GET method";
    }
}
