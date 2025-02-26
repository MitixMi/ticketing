package avion.com.controller;

import avion.com.models.Promotion;
import avion.com.models.User;
import avion.com.dao.UserDAO;
import avion.com.annotation.GET;
import avion.com.annotation.POST;
import avion.com.annotation.Param;
import avion.com.example.MySession;
import avion.com.model.ModelView;
import avion.com.models.Ville;
import avion.com.models.Vol;
import jakarta.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.SQLException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private UserDAO userDAO;

    public LoginController() {
        try {
            Connection connection = avion.com.database.DBConnection.getConnection();
            this.userDAO = new UserDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    @GET("/login_page")
    public ModelView showLoginForm() {
        return new ModelView("/WEB-INF/views/login.jsp");
    }

    @POST("/log")
    public ModelView handleLogin(@Param(name = "email") String email,
                                 @Param(name = "password") String password,
                                 MySession session) {
        try {
            User user = userDAO.login(email, password);
            if (user != null) {
                session.add("userId", user.getId());
                session.add("username", user.getNom());
                session.add("role", user.getRole());

                // ✅ Log success and return the appropriate page based on user role
                System.out.println("✅ Connexion réussie pour " + user.getNom() + " avec le rôle " + user.getRole() + ".");

                if ("client".equals(user.getRole())) {
                    return new ModelView("/WEB-INF/views/client_menu.jsp");
                } else if ("admin".equals(user.getRole())) {
                    return new ModelView("/WEB-INF/views/admin_menu.jsp");
                } else {
                    ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
                    mv.addAttribute("error", "Rôle non reconnu");
                    return mv;
                }
            } else {
                ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
                mv.addAttribute("error", "Identifiants incorrects");
                return mv;
            }
        } catch (SQLException e) {
            ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
            mv.addAttribute("error", "Erreur de base de données");
            return mv;
        }
    }


    @GET("/login_out")
    public ModelView logout(HttpServletRequest request) {
        if (request != null) {
            HttpSession session = request.getSession(false); // Don't create a new session if none exists
            if (session != null) {
                session.invalidate(); // Destroy session
                System.out.println("✅ Session destroyed successfully.");
            } else {
                System.out.println("⚠️ No active session found.");
            }
        } else {
            System.out.println("❌ HttpServletRequest is null. Can't access session.");
        }

        return new ModelView("/WEB-INF/views/login.jsp");
    }




}

