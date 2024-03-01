package Commands;

public class KeyCommand extends Command {
    private boolean shiftEnable = false;
    private String keyText;

    public KeyCommand(boolean shiftEnable, String keyText) {
        this.shiftEnable = shiftEnable;
        this.keyText = keyText;
    }

    //SETTERS
    public boolean isShiftEnable() {
        return shiftEnable;
    }
    public void setShiftEnable(boolean shiftEnable) {
        this.shiftEnable = shiftEnable;
    }

    //GETTERS
    public String getKeyText() {
        return keyText;
    }
    public char getKeyTest_char() {
        return keyText.charAt(0);
    }
    public void setKeyCode(String keyText) {
        this.keyText = keyText;
    }

    public String print() {
        return "Shift: " + shiftEnable + " - " + keyText + " - " + keyText.charAt(0);
    }
}
