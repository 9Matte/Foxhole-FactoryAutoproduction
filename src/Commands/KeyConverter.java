package Commands;

import java.awt.event.KeyEvent;

public class KeyConverter {
    public static int convertToCode(String keyText) {
        switch (keyText) {
            case "Esc":
                return KeyEvent.VK_ESCAPE;
            default:
                return keyText.charAt(0);
        }
    }
}
