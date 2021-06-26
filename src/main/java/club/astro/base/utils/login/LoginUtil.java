package club.astro.base.utils.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class LoginUtil {
    public static String username = "Not Logged In.";

    public static boolean login(String username, String password) {
        LoginUtil.username = username;
        username = HashingUtil.getHash(username);
        password = HashingUtil.getHash(password);
        String hardwareID = HashingUtil.getHash(HwidUtil.getHWID());
        String secretKey = HashingUtil.getHash(hardwareID + password + username);

        return checkKey(secretKey);
    }

    private static boolean checkKey(String key) {
        try {
            URL url = new URL("https://pastebin.com/raw/yEtCKD7X");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            ArrayList<String> keys = new ArrayList<>();

            String s;

            while ((s = reader.readLine()) != null) {
                keys.add(s);
            }
            if (keys.contains(key)) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception ignored){
            return false;
        }
    }

    public static String getNewLogin(String username, String password) {
        username = HashingUtil.getHash(username);
        password = HashingUtil.getHash(password);
        String hardwareID = HashingUtil.getHash(HwidUtil.getHWID());
        String secretKey = HashingUtil.getHash(hardwareID + password + username);

        return secretKey;
    }
}
