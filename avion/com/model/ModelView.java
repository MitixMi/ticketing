package avion.com.model;

import java.io.IOException;
import java.util.HashMap;
import jakarta.servlet.http.HttpServletResponse;

public class ModelView {
    private String url;
    private HashMap<String, Object> data;

    // Constructeurs
    public ModelView() {
        this.data = new HashMap<>();
    }

    public ModelView(String url) {
        this.url = url;
        this.data = new HashMap<>();
    }

    // Vérifie si c'est une redirection
    public boolean isRedirect() {
        return url != null && url.startsWith("redirect:");
    }

    // Retourne l'URL propre (sans "redirect:")
    public String getUrl() {
        if (isRedirect()) {
            return url.substring(9);  // Supprime "redirect:"
        }
        return url;
    }

    // Effectue une redirection HTTP si nécessaire
    public void handleRedirect(HttpServletResponse response) throws IOException {
        if (isRedirect()) {
            response.sendRedirect(getUrl());  // Envoie une vraie redirection HTTP 302
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addAttribute(String key, Object value) {
        data.put(key, value);
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void addObject(String key, Object value) {
        this.data.put(key, value);
    }

    public void addData(String key, Object value) {
        this.data.put(key, value);
    }
}

