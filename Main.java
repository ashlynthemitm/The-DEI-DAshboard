import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DashGUI test = new DashGUI();
        test.dashboard().setVisible(true);
        test.dashboard().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}