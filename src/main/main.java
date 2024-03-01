package main;

import Commands.Command;
import Commands.KeyCommand;
import Commands.MouseCommand;
import RuntimeListeners.RuntimeStarter;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class main {
    public static List<Command> commandListTemp = new ArrayList<>();
    private static List<Command> commandList_LOADFACTORY = new ArrayList<>();
    private static List<Command> commandList_UNLOADFACTORY = new ArrayList<>();
    public static boolean configurationIsDone = false;

    public static void main(String[] args) throws InterruptedException, AWTException, IOException {
        System.out.println("Welcome to the automatic factory production tool. This tool is free to use and created by Brild.");
        Robot robot = new Robot();








        System.out.println("\n\n\nProceed to load the factory. " +
            "\n--------------> When you are done, use Alt key.");
        RuntimeStarter runtimeStarter = new RuntimeStarter();
        runtimeStarter.start();

        while(!configurationIsDone)
        {
            Thread.sleep(1000);
        }
        //Save the command list in the LOADFACTORY list
        commandList_LOADFACTORY = new ArrayList<>(commandListTemp);
        commandListTemp.clear();
        configurationIsDone = false;







        System.out.println("\n\n\nFirst configuration completed." +
            "\nNow proceed by unloading the factory. IMPORTANT Before the click of the confirm, press X" +
            "\n--------------> When you are done, use Alt key.");
        while(!configurationIsDone)
        {
            Thread.sleep(1000);
        }
        commandList_UNLOADFACTORY = new ArrayList<>(commandListTemp);;
        commandListTemp.clear();
        configurationIsDone = false;








        System.out.println("\n\n\nAll configurations has been completed.");
        //BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));


        while(true) {
            //Main loop of execution. Inside this loop, all the commands are executed one after the other.
            //First, execute the load command
            System.out.println("Executing load commands");
            executeCommands(commandList_LOADFACTORY, robot);
            Thread.sleep(3000);
            System.out.println("Executing unload commands");
            while (!executeCommands(commandList_UNLOADFACTORY, robot)) {
                Thread.sleep(3000);
            }
        }
    }

    /***
     * Method used to execute a list of commands
     * @param commandList List of commands to be executed
     * @param robot Robot used to execute the commands
     * @throws InterruptedException
     * @return True if unloading happened. False otherwise
     */
    public static boolean executeCommands(List<Command> commandList, Robot robot) throws InterruptedException {
        boolean finishedUnloading = false; //change to true only if unloading happened successfully (green happened)
        for (Command c: commandList) {
            //Verify if the command is a key command
            if(c instanceof KeyCommand) {
                System.out.println(c.print());
                //If the command is a key, it's important to check for the shift before.
                if(((KeyCommand) c).isShiftEnable()) {
                    //In this case, shift is enable.
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(((KeyCommand) c).getKeyTest_char());
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
                else //Shift is not pressed, so execute the command
                    robot.keyPress(((KeyCommand) c).getKeyTest_char());

                robot.keyRelease(20);
            }
            //Verify if the command is a mouse command
            else if(c instanceof MouseCommand) {
                //If the movement is critical, check if the box of the production is green.
                if(((MouseCommand) c).isCriticalClick()) {
                    Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage image = robot.createScreenCapture(capture);
                    System.out.println("Critical!");
                    //If the color is green, move the mouse and press. otherwise, do nothing
                    if (verifyColorGreen(image, ((MouseCommand) c).getX(), ((MouseCommand) c).getY())) {
                        robot.mouseMove(((MouseCommand) c).getX(), ((MouseCommand) c).getY());
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        finishedUnloading = true;
                    }
                    else
                        robot.mouseMove(((MouseCommand) c).getX(), ((MouseCommand) c).getY());
                }
                //If movement is not critical, execute the standard procedure
                else {
                    robot.mouseMove(((MouseCommand) c).getX(), ((MouseCommand) c).getY());
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                }
            }
            //Wait before executing the next command
            Thread.sleep(500);
        }
        return finishedUnloading;
    }

    /***
     * Method is used to verify if the pixels are green next to the cursor portion
     * @param image Image to be parsed
     * @param cursor_x Cursor x position
     * @param cursor_y Cursor y position
     * @return True if green matched. Otherwise red
     */
    public static boolean verifyColorGreen(BufferedImage image, int cursor_x, int cursor_y) {
        final int XRANGE = 4;
        final int YRANGE = 4;
        final int GREEN_MIN = 40;

        for (int x = -XRANGE/2; x < XRANGE/2; x++) {
            for (int y = -YRANGE/2; y < YRANGE/2; y++) {
                //Verify that the color is green. To be green, the red < green and green should be over 80
                int clr = image.getRGB(cursor_x + x, cursor_y + y);
                int red =   (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue =   clr & 0x000000ff;
                if(red < green && green > GREEN_MIN) {
                    System.out.println("Color is green, production finished");
                    return true;
                }
            }
        }
        return false;
    }
}
