package main;

import Commands.Command;
import Commands.KeyCommand;
import Commands.MouseCommand;
import RuntimeListeners.RuntimeStarter;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class main {
    public static List<Command> commandListTemp = new ArrayList<>();
    private static List<Command> commandList_LOADFACTORY = new ArrayList<>();
    private static List<Command> commandList_UNLOADFACTORY = new ArrayList<>();
    public static boolean configurationIsDone = false;

    public static void main(String[] args) throws InterruptedException, AWTException {
        System.out.println("Welcome to the automatic factory production tool. This tool is free to use and created by Brild.");


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
            "\nNow proceed by unloading the factory. IMPORTANT Before the click of the confirm, press x" +
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

        Robot robot = new Robot();
        while(true) {
            System.out.println("Executing while");
            //Main loop of execution. Inside this loop, all the commands are executed one after the other.
            //First, execute the load command
            for (Command c: commandList_LOADFACTORY) {
                //Verify if the command is a key command
                if(c instanceof KeyCommand) {
                    //If the command is a key, it's important to check for the shift before.
                    if(((KeyCommand) c).isShiftEnable()) {
                        //In this case, shift is enable.
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        robot.keyPress(((KeyCommand) c).getKeyCode());

                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }
                    else //Shift not enabled, so execute only the command
                        robot.keyPress(((KeyCommand) c).getKeyCode());

                    robot.keyRelease(((KeyCommand) c).getKeyCode());
                }
                //Verify if the command is a mouse command
                else if(c instanceof MouseCommand) {
                    robot.mouseMove(((MouseCommand) c).getX(), ((MouseCommand) c).getY());
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                }
                Thread.sleep(500);
            }
            Thread.sleep(3000);
        }
        /*try {
            Robot robot = new Robot();

            // Simulate a mouse click
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.mouseMove(random.nextInt(900), random.nextInt(900));
            // Simulate a key press
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        */

    }
}
