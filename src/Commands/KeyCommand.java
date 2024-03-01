package Commands;

public class KeyCommand extends Command {
    private boolean shiftEnable = false;
    private int keyCode;

    public KeyCommand(boolean shiftEnable, int keyCode) {
        this.shiftEnable = shiftEnable;
        this.keyCode = keyCode;
    }

    //SETTERS
    public boolean isShiftEnable() {
        return shiftEnable;
    }
    public void setShiftEnable(boolean shiftEnable) {
        this.shiftEnable = shiftEnable;
    }

    //GETTERS
    public int getKeyCode() {
        return keyCode;
    }
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public String print() {
        return "Shift: " + shiftEnable + " - " + keyCode;
    }
}
