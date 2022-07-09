package sakura.kooi.minecraftforge.hidepassword;

import java.util.Arrays;

public class Hidepassword {
    public static String getBeginWithSecretCommand(String chatText) {
        String[] commands = new String[] {
                "/login ",
                "/l ",
                "/register ",
                "/reg ",
                "/r ",
                ".login ",
                ".l ",
                ".register ",
                ".reg ",
                ".r "
        };
        return Arrays.stream(commands).filter(chatText::startsWith).findFirst().orElse(null);
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
