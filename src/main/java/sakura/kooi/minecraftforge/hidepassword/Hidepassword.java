package sakura.kooi.minecraftforge.hidepassword;

public class Hidepassword {
    public static String getBeginWithSecretCommand(String chatText) {
        if (chatText.startsWith("/login ")) {
            return "/login ";
        }
        if (chatText.startsWith("/l ")) {
            return "/l ";
        }
        if (chatText.startsWith("/register ")) {
            return "/register ";
        }
        if (chatText.startsWith("/reg ")) {
            return "/reg ";
        }
        if (chatText.startsWith("/r ")) {
            return "/r ";
        }
        if (chatText.startsWith(".login ")) {
            return ".login ";
        }
        if (chatText.startsWith(".l ")) {
            return ".l ";
        }
        if (chatText.startsWith(".register ")) {
            return ".register ";
        }
        if (chatText.startsWith(".reg ")) {
            return ".reg ";
        }
        if (chatText.startsWith(".r ")) {
            return ".r ";
        }
        return null;
    }

    public static String replaceChatText(String chatText) {
        if (chatText == null)
            return null;
        String begin = getBeginWithSecretCommand(chatText);
        if (begin == null)
            return chatText;
        String secret = chatText.substring(begin.length());
        return begin + fillString('*', secret);
    }

    private static String fillString(char c, String string) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                builder.append(' ');
            } else builder.append(c);
        }
        return builder.toString();
    }
}
