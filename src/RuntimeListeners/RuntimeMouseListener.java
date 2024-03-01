package RuntimeListeners;
import main.main;

import Commands.MouseCommand;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

public class RuntimeMouseListener implements NativeMouseListener {
    public void nativeMouseClicked(NativeMouseEvent e) {
        //When mouse is clicked, we consider as left click. Just register it.
        main.commandListTemp.add(new MouseCommand(e.getX(), e.getY(), RuntimeKeyboardListener.criticalClick));
        //It's important to reset criticalClick every time
        //System.out.println("click");
        RuntimeKeyboardListener.criticalClick = false;
    }
}