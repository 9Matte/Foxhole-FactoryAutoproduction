package RuntimeListeners;
import main.main;

import Commands.KeyCommand;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class RuntimeKeyboardListener implements NativeKeyListener {
    Boolean shiftPressed = false; //handle shift pressed or not
    public static Boolean criticalClick = false; //A critical click is when it deserves to check if item is finished

    public void nativeKeyPressed(NativeKeyEvent e) {
        //if shift is pressed, just register it and don't add any command.
        String keyStroke = NativeKeyEvent.getKeyText(e.getKeyCode());

        //System.out.println(keyStroke + " -> " + e.getKeyCode());
        switch (e.getKeyCode()) {
            case 42 -> // SHIFT
                //If Shift is pressed, only consider as a flag
                shiftPressed = true;
            case 45 -> // X
                criticalClick = true;
            case 14 -> // Backspace
                System.exit(0);
            case 56 -> // Alt
                main.configurationIsDone = true;
            default ->
                //register every single other key
                main.commandListTemp.add(new KeyCommand(shiftPressed, keyStroke));
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        //Only check if shift has been released and remove it.
        if(e.getKeyCode() == 42) shiftPressed = false;
    }
}
