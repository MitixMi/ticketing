package avion.com.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import avion.com.annotation.GET;
import avion.com.annotation.POST;
import avion.com.annotation.Param;
import avion.com.controller.RequestObject;
import avion.com.model.ModelView;
import avion.com.controller.User;
import avion.com.example.MySession;
import avion.com.example.UserDataStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    // Hardcoded credentials
    private static final String HARDCODED_USERNAME_1 = "user1";
    private static final String HARDCODED_PASSWORD_1 = "password1";
    private static final String HARDCODED_USERNAME_2 = "user2";
    private static final String HARDCODED_PASSWORD_2 = "password2";

    @GET("/submitUser")
    public ModelView submitUser(@RequestObject User user) {
        ModelView mv = new ModelView("/WEB-INF/views/userResult.jsp");
        mv.addData("user", user);
        return mv;
    }

    @GET("/login")
    public ModelView showLoginForm() {
        ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
        return mv;
    }

    @POST("/logins")
    public ModelView login(@Param(name = "username") String username,
                           @Param(name = "password") String password,
                           MySession session) {
        // Simulate user authentication
        if ((HARDCODED_USERNAME_1.equals(username) && HARDCODED_PASSWORD_1.equals(password)) ||
            (HARDCODED_USERNAME_2.equals(username) && HARDCODED_PASSWORD_2.equals(password))) {
            session.add("username", username);
            ModelView mv = new ModelView("/WEB-INF/views/userData.jsp");
            mv.addAttribute("username", username);  // Add username to be displayed in the view
            mv.addAttribute("dataList", UserDataStore.getUserData(username));
            return mv;
        } else {
            ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
            mv.addAttribute("error", "Invalid credentials");
            return mv;
        }
    }

    @GET("/logout")
    public ModelView logout(MySession session) {
        session.delete("username");
        ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
        return mv;
    }

    private List<String> getUserData(String username) {
        // Simulate fetching user-specific data
        List<String> dataList = new ArrayList<>();
        dataList.add("Data 1 for " + username);
        dataList.add("Data 2 for " + username);
        dataList.add("Data 3 for " + username);
        return dataList;
    }

    
}

