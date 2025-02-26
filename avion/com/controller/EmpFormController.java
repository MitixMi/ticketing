package avion.com.controller;

import avion.com.annotation.POST;
import avion.com.controller.RequestObject;
import avion.com.model.EmpForm;
import avion.com.controller.Validator;
import avion.com.model.ModelView;

import java.util.List;
import java.util.Map;

import avion.com.annotation.GET;

public class EmpFormController {

    @GET("/form")
    public ModelView showLoginForm() {
        // This should load the form page
        ModelView mv = new ModelView("/WEB-INF/views/form.jsp");
        return mv;
    }

    @POST("/submitEmpForm")
    public ModelView submitEmpForm(@RequestObject EmpForm form) {
        // Validator now returns a Map<String, String> of field-specific errors
        Map<String, String> fieldErrors = Validator.validate(form);

        ModelView modelView = new ModelView();
        if (fieldErrors.isEmpty()) {
            modelView.addAttribute("success", "Employee form submitted successfully!");
            modelView.setUrl("/WEB-INF/views/empSuccess.jsp");
        } else {
            // Pass field-specific errors to the ModelView
            modelView.addAttribute("fieldErrors", fieldErrors);
            modelView.setUrl("/WEB-INF/views/form.jsp");
        }
        return modelView;
    }

}
