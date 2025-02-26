package avion.com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataStore {
    private static final Map<String, List<String>> userData = new HashMap<>();

    static {
        // Hardcoded user data
        List<String> user1Data = new ArrayList<>();
        user1Data.add("User1 carol");
        user1Data.add("User1 15");
        user1Data.add("User1 Alone");

        List<String> user2Data = new ArrayList<>();
        user2Data.add("User2 marie");
        user2Data.add("User2 30");
        user2Data.add("User2 Married");

        userData.put("user1", user1Data);
        userData.put("user2", user2Data);
    }

    public static List<String> getUserData(String username) {
        return userData.get(username);
    }
}

