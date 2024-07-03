import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class AsciiArtCaptcha {

    private static final Map<Character, String[]> asciiArtMap = new HashMap<>();

    static {
        asciiArtMap.put('0', new String[]{
                "  ___  ",
                " / _ \\ ",
                "| | | |",
                "| | | |",
                "| |_| |",
                " \\___/ "
        });
        asciiArtMap.put('1', new String[]{
                " __ ",
                "/_ |",
                " | |",
                " | |",
                " | |",
                " |_|"
        });
        asciiArtMap.put('2', new String[]{
                " ___  ",
                "|__ \\ ",
                "   ) |",
                "  / / ",
                " / /_ ",
                "|____|"
        });
        asciiArtMap.put('3', new String[]{
                " ____  ",
                "|___ \\ ",
                "  __) |",
                " |__ < ",
                " ___) |",
                "|____/ "
        });
        asciiArtMap.put('4', new String[]{
                " _  _   ",
                "| || |  ",
                "| || |_ ",
                "|__   _|",
                "   | |  ",
                "   |_|  "
        });
        asciiArtMap.put('5', new String[]{
                " _____ ",
                "| ____|",
                "| |__  ",
                "|___ \\ ",
                " ___) |",
                "|____/ "
        });
        asciiArtMap.put('6', new String[]{
                "   __  ",
                "  / /  ",
                " / /_  ",
                "| '_ \\ ",
                "| (_) |",
                " \\___/ "
        });
        asciiArtMap.put('7', new String[]{
                " ______ ",
                "|____  |",
                "    / / ",
                "   / /  ",
                "  / /   ",
                " /_/    "
        });
        asciiArtMap.put('8', new String[]{
                "  ___  ",
                " ( _ ) ",
                " / _ \\ ",
                "| (_) |",
                " \\___/ ",
                "       "
        });
        asciiArtMap.put('9', new String[]{
                "  ___  ",
                " / _ \\ ",
                "| (_) |",
                " \\__, |",
                "   / / ",
                "  /_/  "
        });
    }

    public static String generateCaptcha() {
        SecureRandom random = new SecureRandom();
        int length = 5 + random.nextInt(3);
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < length; i++) {
            captcha.append(random.nextInt(10));
        }

        displayCaptcha(captcha.toString());
        return captcha.toString();
    }

    private static void displayCaptcha(String captcha) {
        StringBuilder[] lines = new StringBuilder[6];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new StringBuilder();
        }

        for (char digit : captcha.toCharArray()) {
            String[] asciiArt = asciiArtMap.get(digit);
            for (int i = 0; i < lines.length; i++) {
                lines[i].append(asciiArt[i]).append("  ");
            }
        }

        for (StringBuilder line : lines) {
            System.out.println(line);
        }
    }


}
