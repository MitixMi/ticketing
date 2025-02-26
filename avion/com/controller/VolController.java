package avion.com.controller;

import avion.com.annotation.GET;
import avion.com.annotation.POST;
import avion.com.annotation.Param;
import avion.com.dao.*;
import avion.com.model.ModelView;
import avion.com.database.DBConnection;
import avion.com.models.*;
import avion.com.example.MySession;
import avion.com.models.User;
import jakarta.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "VolController", urlPatterns = {"/VolController"})
public class VolController {

    private UserDAO userDAO;
    private VolDAO volDAO;
    private AvionDAO avionDAO;
    private VilleDAO villeDAO;
    private PromotionDAO promotionDAO;

    public VolController() {
        try {
            Connection connection = DBConnection.getConnection();
            volDAO = new VolDAO(connection);
            userDAO = new UserDAO(connection);  // ✅ Ajout de l'initialisation de userDAO
            avionDAO = new AvionDAO(connection);
            villeDAO = new VilleDAO(connection);
            promotionDAO = new PromotionDAO(connection);
            System.out.println("✅ VolController initialisé avec succès !");
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'initialisation de VolController : " + e.getMessage());
            throw new RuntimeException("Erreur connexion BDD", e);
        }
    }


    // 🔹 Affichage de la liste des vols
    @GET("/vols")
    public ModelView getAllVols() {
        ModelView mv = new ModelView("/WEB-INF/views/volList.jsp");
        try {
            // Récupérer la liste des vols
            List<Vol> vols = volDAO.getAllVols();

            // Récupérer la liste des avions et des villes
            List<Avion> avions = avionDAO.getAllAvions();  // Assurez-vous que vous avez un avionDAO
            List<Ville> villes = villeDAO.getAllVilles();  // Assurez-vous que vous avez un villeDAO

            // Ajouter les données au ModelView
            mv.addAttribute("vols", vols);
            mv.addAttribute("avions", avions);  // Ajouter la liste des avions
            mv.addAttribute("villes", villes);  // Ajouter la liste des villes

        } catch (Exception e) {
            mv.addAttribute("error", "Erreur lors de la récupération des vols.");
        }
        return mv;
    }

    @GET("/vols_page")
    public ModelView showAddVolForm() {
        ModelView mv = new ModelView("/WEB-INF/views/add_vol.jsp");
        try {
            // Récupérer la liste des avions et des villes
            List<Avion> avions = avionDAO.getAllAvions();  // Assurez-vous que vous avez un avionDAO
            List<Ville> villes = villeDAO.getAllVilles();  // Assurez-vous que vous avez un villeDAO

            // Ajouter les données au ModelView
            mv.addAttribute("avions", avions);  // Ajouter la liste des avions
            mv.addAttribute("villes", villes);  // Ajouter la liste des villes

        } catch (Exception e) {
            mv.addAttribute("error", "Erreur lors de la récupération des données.");
        }
        return mv;
    }


    // 🔹 Ajout d'un vol (Retourne directement la vue mise à jour)
    @POST("/addVol")
    public ModelView addVol(
            @Param(name = "avionId") int avionId,
            @Param(name = "villeDepartId") int villeDepartId,
            @Param(name = "villeArriveeId") int villeArriveeId,
            @Param(name = "dateDepart") String dateDepart,
            @Param(name = "dateArrivee") String dateArrivee,
            @Param(name = "prix") String prixStr) throws SQLException {

        try {
            // Vérifiez si le prix n'est pas vide avant de le convertir en double
            Double prix = null;
            if (prixStr != null && !prixStr.isEmpty()) {
                prix = Double.parseDouble(prixStr);
            }

            if (prix == null) {
                throw new IllegalArgumentException("Le prix ne peut pas être nul");
            }

            // Convertir les chaînes de caractères en Timestamp
            Timestamp dateDepartTimestamp = convertStringToTimestamp(dateDepart);
            Timestamp dateArriveeTimestamp = convertStringToTimestamp(dateArrivee);

            Vol vol = new Vol(0, avionId, villeDepartId, villeArriveeId, dateDepartTimestamp, dateArriveeTimestamp, prix);
            volDAO.ajouterVol(vol);

            return getAllVols();  // 🔥 Retourne directement la vue mise à jour avec la liste des vols

        } catch (Exception e) {
            ModelView mv = new ModelView("/WEB-INF/views/volList.jsp");
            List<Vol> vols = volDAO.getAllVols();
            List<Avion> avions = avionDAO.getAllAvions();
            List<Ville> villes = villeDAO.getAllVilles();
            mv.addAttribute("vols", vols);
            mv.addAttribute("avions", avions);
            mv.addAttribute("villes", villes);
            mv.addAttribute("error", "Erreur lors de l'ajout du vol : " + e.getMessage());
            return mv;
        }
    }

    // Méthode utilitaire pour convertir les chaînes de caractères en Timestamp
    private Timestamp convertStringToTimestamp(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return Timestamp.valueOf(localDateTime);
    }

    // 🔹 Mise à jour d'un vol
    @POST("/updateVol")
    public ModelView updateVol(
            @Param(name = "id") int id,
            @Param(name = "avionId") int avionId,
            @Param(name = "villeDepartId") int villeDepartId,
            @Param(name = "villeArriveeId") int villeArriveeId,
            @Param(name = "dateDepart") String dateDepart,
            @Param(name = "dateArrivee") String dateArrivee,
            @Param(name = "prix") double prix) {

        try {
            Vol vol = new Vol(id, avionId, villeDepartId, villeArriveeId, java.sql.Timestamp.valueOf(dateDepart), java.sql.Timestamp.valueOf(dateArrivee), prix);
            volDAO.modifierVol(vol);

            return getAllVols();  // 🔥 Retourne directement la vue mise à jour

        } catch (Exception e) {
            ModelView mv = new ModelView("/WEB-INF/views/editVol.jsp");
            mv.addAttribute("error", "Erreur lors de la mise à jour du vol.");
            return mv;
        }
    }

    // 🔹 Suppression d'un vol
    @POST("/deleteVol")
    public ModelView deleteVol(@Param(name = "id") int id) {
        try {
            volDAO.supprimerVol(id);

            return getAllVols();  // 🔥 Retourne directement la vue mise à jour

        } catch (Exception e) {
            ModelView mv = new ModelView("/WEB-INF/views/volList.jsp");
            mv.addAttribute("error", "Erreur lors de la suppression du vol.");
            return mv;
        }
    }

    // 🔹 Display the search page (No parameters required)
    @GET("/search_page")
    public ModelView showSearchPage() throws SQLException {
        // Assurez-vous que vous avez un villeDAO
        List<Ville> villes = villeDAO.getAllVilles();  // Assurez-vous que vous avez un villeDAO

        ModelView mv = new ModelView("/WEB-INF/views/searchVols.jsp");
        mv.addAttribute("villes", villes);

        return mv;
    }


    // 🔹 Handle the search query (Requires parameters)
    @POST("/searchVol")
    public ModelView searchVols(
            @Param(name = "villeDepartId") String villeDepartIdStr,
            @Param(name = "villeArriveeId") String villeArriveeIdStr,
            @Param(name = "dateDepart") String dateDepart,
            @Param(name = "maxPrix") String maxPrixStr) {

        System.out.println("📌 Searching flights with: villeDepartId=" + villeDepartIdStr +
                ", villeArriveeId=" + villeArriveeIdStr + ", dateDepart=" + dateDepart + ", maxPrix=" + maxPrixStr);

        // Déclarez les variables pour stocker les identifiants des villes et le prix maximum
        Integer villeDepartId = null;
        Integer villeArriveeId = null;
        Double maxPrix = null;

        // Vérifiez si les paramètres ne sont pas vides avant de les convertir
        if (villeDepartIdStr != null && !villeDepartIdStr.isEmpty() && !villeDepartIdStr.equals("all")) {
            villeDepartId = Integer.parseInt(villeDepartIdStr);
        }

        if (villeArriveeIdStr != null && !villeArriveeIdStr.isEmpty() && !villeArriveeIdStr.equals("all")) {
            villeArriveeId = Integer.parseInt(villeArriveeIdStr);
        }

        if (maxPrixStr != null && !maxPrixStr.isEmpty()) {
            maxPrix = Double.parseDouble(maxPrixStr);
        }

        ModelView mv = new ModelView("/WEB-INF/views/searchVols.jsp");
        try {
            List<Ville> villes = villeDAO.getAllVilles();
            List<Vol> vols = volDAO.searchVols(villeDepartId, villeArriveeId, dateDepart, maxPrix);
            mv.addAttribute("villes", villes);
            mv.addAttribute("vols", vols);

        } catch (Exception e) {
            mv.addAttribute("error", "Erreur lors de la recherche des vols.");
        }
        return mv;
    }

    @GET("/reserver_page")
    public ModelView afficherReservation(MySession session) {
        // Vérifier si l'utilisateur est connecté et a le rôle "client"
        if (session.get("userId") == null || !"client".equals(session.get("role"))) {
            ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
            mv.addAttribute("error", "Vous devez être connecté en tant que client.");
            return mv;
        }

        try {
            ModelView mv = new ModelView("/WEB-INF/views/reservation.jsp");

            // Récupérer les vols, villes et promotions
            List<Vol> vols = volDAO.getAllVols();
            List<Ville> villes = villeDAO.getAllVilles();
            List<Promotion> promotions = promotionDAO.getAllPromotions();

            // Associer les noms de villes aux vols
            for (Vol vol : vols) {
                for (Ville ville : villes) {
                    if (vol.getVilleDepartId() == ville.getId()) {
                        vol.setVilleDepartNom(ville.getNom());
                    }
                    if (vol.getVilleArriveeId() == ville.getId()) {
                        vol.setVilleArriveeNom(ville.getNom());
                    }
                }
            }

            // Ajouter les données à la vue
            mv.addAttribute("vols", vols);
            mv.addAttribute("promotions", promotions);
            mv.addAttribute("villes", villes);

            return mv;
        } catch (SQLException e) {
            ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
            mv.addAttribute("error", "Erreur lors du chargement des données.");
            return mv;
        }
    }




}

