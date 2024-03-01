package RuntimeListeners;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class RuntimeStarter {
    public void start(){
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new RuntimeKeyboardListener());
        GlobalScreen.addNativeMouseListener(new RuntimeMouseListener());

    }
}
