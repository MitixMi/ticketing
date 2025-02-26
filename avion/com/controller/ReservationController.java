package avion.com.controller;

import avion.com.annotation.GET;
import avion.com.annotation.POST;
import avion.com.annotation.Param;
import avion.com.dao.*;
import avion.com.model.ModelView;
import avion.com.database.DBConnection;
import avion.com.example.MySession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import avion.com.models.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "ReservationController", urlPatterns = {"/ReservationController"})
public class ReservationController {

    private ReservationDAO reservationDAO;
    private VolDAO volDAO;
    private PromotionDAO promotionDAO;
    private VilleDAO villeDAO;
    private AvionDAO avionDAO;

    public ReservationController() {
        try {
            Connection connection = DBConnection.getConnection();
            volDAO = new VolDAO(connection);
            promotionDAO = new PromotionDAO(connection);
            villeDAO = new VilleDAO(connection);
            avionDAO = new AvionDAO(connection);
            reservationDAO = new ReservationDAO(connection, promotionDAO);
            System.out.println("✅ ReservationController initialisé !");
        } catch (Exception e) {
            throw new RuntimeException("Erreur connexion BDD", e);
        }
    }

    @GET("/reservation")
    public ModelView afficherFormulaireReservation() {
        ModelView mv = new ModelView("/WEB-INF/views/reservation.jsp");
        try {
            List<Vol> vols = volDAO.getAllVols();  // Récupérer tous les vols
            List<Ville> villes = villeDAO.getAllVilles(); // Récupérer toutes les villes
            List<Promotion> promotions = promotionDAO.getAllPromotions(); // Récupérer les promos

            // Ajouter les noms des villes aux vols
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

            mv.addAttribute("vols", vols);
            mv.addAttribute("promotions", promotions);
            mv.addAttribute("villes", villes);
        } catch (Exception e) {
            mv.addAttribute("error", "Erreur lors du chargement des vols et promotions.");
        }
        return mv;
    }



    // 🔹 Afficher les réservations d'un utilisateur
    @GET("/reservations")
    public ModelView afficherReservations(MySession session) {
        ModelView mv = new ModelView("/WEB-INF/views/reservation.jsp");
        try {
            int utilisateurId = (int) session.get("userId");

            // Récupérer les réservations pour l'utilisateur
            List<Reservation> reservations = reservationDAO.getReservationsByUser(utilisateurId);

            // Récupérer tous les vols, villes et promotions
            List<Vol> vols = volDAO.getAllVols();
            List<Ville> villes = villeDAO.getAllVilles();
            List<Promotion> promotions = promotionDAO.getAllPromotions();

            // Ajouter les noms des villes aux vols
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

            // Ajouter les données au ModelView
            mv.addAttribute("reservations", reservations);
            mv.addAttribute("vols", vols);
            mv.addAttribute("promotions", promotions);
            mv.addAttribute("villes", villes);
        } catch (Exception e) {
            mv.addAttribute("error", "Erreur lors de la récupération des réservations et des données associées.");
        }
        return mv;
    }


    // 🔹 Réserver un vol
    @POST("/reserverVol")
    public ModelView reserverVol(@Param(name = "volId") int volId, @Param(name = "typeSiege") String typeSiege, MySession session) throws SQLException {
        try {
            int utilisateurId = (int) session.get("userId");
            if (!reservationDAO.peutReserver(volId)) {
                // Récupérer la liste des vols, avions et villes pour `volList.jsp`
                ModelView mv = new ModelView("/WEB-INF/views/volList.jsp");
                List<Vol> vols = volDAO.getAllVols();
                List<Avion> avions = avionDAO.getAllAvions();
                List<Ville> villes = villeDAO.getAllVilles();
                mv.addAttribute("vols", vols);
                mv.addAttribute("avions", avions);
                mv.addAttribute("villes", villes);
                mv.addAttribute("error", "Réservation impossible : le vol part dans moins de 3 heures.");
                return mv;
            }
            reservationDAO.reserverVol(volId, utilisateurId, typeSiege);
            return afficherReservations(session);
        } catch (Exception e) {
            // Ajouter des journaux pour une meilleure compréhension de l'erreur
            e.printStackTrace();  // Affiche la trace de la pile dans la console pour le diagnostic

            // Récupérer la liste des vols, avions et villes pour `volList.jsp`
            ModelView mv = new ModelView("/WEB-INF/views/volList.jsp");
            List<Vol> vols = volDAO.getAllVols();
            List<Avion> avions = avionDAO.getAllAvions();
            List<Ville> villes = villeDAO.getAllVilles();
            mv.addAttribute("vols", vols);
            mv.addAttribute("avions", avions);
            mv.addAttribute("villes", villes);
            mv.addAttribute("error", "Erreur lors de la réservation : " + e.getMessage());  // Ajouter le message d'exception à la vue
            return mv;
        }
    }



    @POST("/annulerReservation")
    public ModelView annulerReservation(@Param(name = "id") int reservationId, MySession session) throws SQLException {
        try {
            if (!reservationDAO.peutAnnuler(reservationId)) {
                ModelView mv = new ModelView("/WEB-INF/views/mes_reservations.jsp");

                // Récupérer les réservations de l'utilisateur
                int userId = (int) session.get("userId"); // Assurez-vous que l'utilisateur est connecté
                List<Reservation> reservations = reservationDAO.getReservationsByUserId(userId);

                // Ajouter les données à la vue
                mv.addAttribute("reservations", reservations);
                mv.addAttribute("error", "Annulation impossible : le vol part dans moins de 6 heures.");
                return mv;
            }
            reservationDAO.annulerReservation(reservationId);
            return afficherReservationsUtilisateur(session);
        } catch (Exception e) {
            ModelView mv = new ModelView("/WEB-INF/views/mes_reservations.jsp");

            // Récupérer les réservations de l'utilisateur
            int userId = (int) session.get("userId"); // Assurez-vous que l'utilisateur est connecté
            List<Reservation> reservations = reservationDAO.getReservationsByUserId(userId);

            // Ajouter les données à la vue
            mv.addAttribute("reservations", reservations);
            mv.addAttribute("error", "Erreur lors de l'annulation.");
            return mv;
        }
    }


    @GET("/mes-reservations")
    public ModelView afficherReservationsUtilisateur(MySession session) {
        // Vérifier si l'utilisateur est connecté
        if (session.get("userId") == null || !"client".equals(session.get("role"))) {
            ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
            mv.addAttribute("error", "Vous devez être connecté pour voir vos réservations.");
            return mv;
        }

        int userId = (int) session.get("userId"); // Récupération de l'ID utilisateur depuis la session

        try {
            ModelView mv = new ModelView("/WEB-INF/views/mes_reservations.jsp");

            // Récupérer les réservations de l'utilisateur
            List<Reservation> reservations = reservationDAO.getReservationsByUserId(userId);

            // Ajouter les données à la vue
            mv.addAttribute("reservations", reservations);

            return mv;
        } catch (SQLException e) {
            ModelView mv = new ModelView("/WEB-INF/views/login.jsp");
            mv.addAttribute("error", "Erreur lors du chargement des réservations.");
            return mv;
        }
    }


}
