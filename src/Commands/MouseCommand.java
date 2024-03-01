package Commands;

public class MouseCommand extends Command{
    private int x;
    private int y;
    private boolean criticalClick;

    public MouseCommand(int x, int y, boolean criticalClick) {
        this.x = x;
        this.y = y;
        this.criticalClick = criticalClick;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCriticalClick() {
        return criticalClick;
    }

    public void setCriticalClick(boolean criticalClick) {
        this.criticalClick = criticalClick;
    }

    public String print() {
        return "x: " + x + "  y: " + y + " " + criticalClick;
    }
}
