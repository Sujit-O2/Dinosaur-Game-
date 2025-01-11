import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("DINOSAUR GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);
        frame .setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(new game());
        frame.pack();
        frame.requestFocus();
        frame.setVisible(true);

    }
}
