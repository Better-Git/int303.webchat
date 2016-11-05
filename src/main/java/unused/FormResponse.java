package unused;

public class FormResponse {
    private static boolean rm;
    private static boolean usr;
    private static boolean pswd;
    
    private static boolean isAlphanumerics(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean RoomValid(String room) {
        if (!room.isEmpty() && isAlphanumerics(room)) {
            if ((room.length() > 2) && (room.length() < 7)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean UserValid(String user) {
        if (!user.isEmpty() && isAlphanumerics(user)) {
            if ((user.length() > 0)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean PassValid(String pass) {
        pswd = false;
        if (!pass.isEmpty() && isAlphanumerics(pass)) {
            if ((pass.length() > 2)) {
                pswd = true;
            }
        }
        return pswd;
    }
}