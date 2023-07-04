import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        InterfazTorneo b = new InterfazTorneo();
        b.setTitle("Torneo de Futbol");
        b.setSize(900, 500);
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}