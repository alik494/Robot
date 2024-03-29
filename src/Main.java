import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Main implements NativeKeyListener {
    int START = NativeKeyEvent.VC_J;
    String STOP = "S";
    long timeStart;
    Thread t;


    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        if (e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                //  e1.printStackTrace();
            }
        }

        if (START == (e.getKeyCode())) {

            t = new Thread(() -> {
                timeStart = System.currentTimeMillis();
                System.out.println("stat_time" + timeStart);

                while (true) {
                    try {
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_O);
                        robot.keyRelease(KeyEvent.VK_O);
                        Thread.sleep(475);
                    } catch (Exception e12) {
                        break;
                    }
                }


            });
            t.start();
        }

        if (STOP == NativeKeyEvent.getKeyText(e.getKeyCode())) {
            long timeFin = System.currentTimeMillis();
            System.out.println("TIME_in_mils" + (timeFin - timeStart));
            t.interrupt();
        }
    }


    public void nativeKeyReleased(NativeKeyEvent e) {
        //      System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        //    System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {

        try {
            GlobalScreen.registerNativeHook();

        } catch (NativeHookException ex) {
            //     System.err.println("There was a problem registering the native hook.");
            //   System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new Main());
    }


}