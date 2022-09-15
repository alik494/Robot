import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import javax.swing.JFrame;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class Main implements NativeKeyListener {
    public String H = "H";
    public String HPr;
    String G = "G";
    static boolean prov;
    private ArrayList<Word> words = new ArrayList<>();


    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }
        }

        if (G == NativeKeyEvent.getKeyText(e.getKeyCode())) {
            prov = true;
            test(prov);

        }

    }


    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        if (H == NativeKeyEvent.getKeyText(e.getKeyCode())) {
            try {
                prov = false;
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {
        test(prov);
        try {
            GlobalScreen.registerNativeHook();

        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new Main());
    }

    class Word {
        private int id;

        public Word(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    static void test(boolean prov) {


        try {
            Robot robot = new Robot();


            // Simulate a mouse click
            while (prov) {
                try {
                    Thread.sleep(60000);
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                    robot.keyPress(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_W);
                    try {
                        GlobalScreen.registerNativeHook();
                    } catch (NativeHookException e) {
                        e.printStackTrace();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GlobalScreen.addNativeKeyListener(new Main());
            }


            // Simulate a key press
            // robot.keyPress(KeyEvent.VK_A);


        } catch (AWTException e) {
            e.printStackTrace();
        }


    }


}
