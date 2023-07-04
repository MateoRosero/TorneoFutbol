import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class InterfazTorneo extends JFrame {
    private javax.swing.JPanel JPanel;
    private JComboBox comboNumEquipos;
    private JButton crearTorneoButton;
    private JTextArea textArea;
    private JButton nombrarButton;
    private JComboBox comboBoxEquipo;
    private JTextField textFieldNombreEquipo;
    private JComboBox comboBoxPartido;
    private JButton jugarButton;
    private JTextField textFieldEquipo1;
    private JTextField textFieldEquipo2;
    private JButton terminarButton;
    private JLabel equipo1;
    private JLabel equipo2;
    private JButton quemarDatosButton;
    private JTextArea textArea1;
    TorneoFutbol torneo;
    Partido p;



    public InterfazTorneo() {
        setContentPane(JPanel);

        nombrarButton.setEnabled(false);

        crearTorneoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearTorneo();
            }
        });
        nombrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombrarEquipo();
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarPartido();
            }
        });
        terminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugarPartido();
            }
        });
        textFieldEquipo1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || textFieldEquipo1.getText().length() >= 3) {
                    e.consume();
                }
            }
        });
        textFieldEquipo2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || textFieldEquipo2.getText().length() >= 3) {
                    e.consume();
                }
            }
        });
        quemarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quemarDatos();
            }
        });
    }

    public void crearTorneo() {
        int numEquipos = Integer.parseInt(comboNumEquipos.getSelectedItem().toString());
        torneo = new TorneoFutbol(numEquipos);
        actualizarEquiposBox(numEquipos);
        p = null;
        iniciarBotones(true);
        mostrarTorneo();
    }

    public void nombrarEquipo() {
        if (!textFieldNombreEquipo.getText().isEmpty()) {
            textArea1.setText(torneo.asignarNombreEquipo(comboBoxEquipo.getSelectedItem().toString(), textFieldNombreEquipo.getText()));
            mostrarTorneo();
        } else {
            textArea1.setText("Ingresa un nombre para el equipo");
        }
    }

    public void empezarPartido() {
        p = torneo.obtenerPartidoPorId(Integer.parseInt(comboBoxPartido.getSelectedItem().toString()));
        if (p.getEquipo1().getNombre() == null || p.getEquipo2().getNombre() == null) {
            terminarButton.setEnabled(false);
            textFieldEquipo1.setEnabled(false);
            textFieldEquipo2.setEnabled(false);

            textFieldEquipo1.setText("");
            textFieldEquipo2.setText("");

            equipo1.setText("Equipo: ");
            equipo2.setText("Equipo: ");
            textArea1.setText("Campos incompletos");
        } else {
            equipo1.setText("Equipo: " + p.getEquipo1().getNombre());
            equipo2.setText("Equipo: " + p.getEquipo2().getNombre());
            terminarButton.setEnabled(true);
            textFieldEquipo1.setEnabled(true);
            textFieldEquipo2.setEnabled(true);
        }
    }

    public void jugarPartido() {
        if (!textFieldEquipo1.getText().isEmpty()) {
            if (!textFieldEquipo2.getText().isEmpty()) {
                if (Integer.parseInt(textFieldEquipo1.getText()) == Integer.parseInt(textFieldEquipo2.getText())) {
                    textArea1.setText("No puede ser empate, ingresa el marcador final");
                } else {
                    if (Integer.parseInt(textFieldEquipo1.getText()) > Integer.parseInt(textFieldEquipo2.getText())) {
                        textArea1.setText(torneo.jugarPartido(Integer.parseInt(comboBoxPartido.getSelectedItem().toString()), p.getEquipo1().getNombre(), textFieldEquipo1.getText(), textFieldEquipo2.getText()));
                    } else {
                        textArea1.setText(torneo.jugarPartido(Integer.parseInt(comboBoxPartido.getSelectedItem().toString()), p.getEquipo2().getNombre(), textFieldEquipo1.getText(), textFieldEquipo2.getText()));
                    }
                    terminarButton.setEnabled(false);
                    textFieldEquipo1.setEnabled(false);
                    textFieldEquipo2.setEnabled(false);

                    textFieldEquipo1.setText("");
                    textFieldEquipo2.setText("");

                    equipo1.setText("Equipo: ");
                    equipo2.setText("Equipo: ");

                    mostrarTorneo();
                }
            } else {
                textArea1.setText("Ingresa el resultado del partido");
            }
        } else {
            textArea1.setText("Ingresa el resultado");
        }
    }

    public void actualizarEquiposBox(int numEquipos) {
        comboBoxEquipo.removeAllItems();
        comboBoxPartido.removeAllItems();
        for (int i = 1; i <= numEquipos; i++) {
            comboBoxEquipo.addItem("Equipo " + i);
            if (i < numEquipos) {
                comboBoxPartido.addItem(i);
            }
        }
    }

    public void iniciarBotones(boolean bool) {
        quemarDatosButton.setEnabled(bool);
        nombrarButton.setEnabled(bool);
        jugarButton.setEnabled(bool);
        textFieldNombreEquipo.setEnabled(bool);
        textFieldNombreEquipo.setText("");
        textFieldEquipo1.setText("");
        textFieldEquipo2.setText("");
    }

    public void quemarDatos() {
        Random rand = new Random();
        String[] nombresEquipos = {
                " L.D.U", " B.S.C", " M.C", " M.U", " R.M",
                " SANTOS", " CUENCA S.P", " EMELEC", " AUCAS", " FLAMENGO",
                " ARSENAL", " MUSHUC RUNA", " AL NASSR", " PSG", " NEWCASTLE",
                " BOCA JUNIIORS", " TECNICO UNIVERSITARIO", " LOJA", " ROMA", " BARCA",
                " RIVER", " IDV", " SPORTING", " JUVENTUS", "PORTO ",
                " TIGRE", " AMERICA", " PUMAS", " FENERBACHE", " SARANDI",
                " MACARA", " DELFIN"
        };

        int numEquipos = Integer.parseInt(comboNumEquipos.getSelectedItem().toString());

        for (int i = 0; i < numEquipos; i++) {
            torneo.asignarNombreEquipo("Equipo " + (i + 1), nombresEquipos[i]);
        }

        mostrarTorneo();


        for(int i = 0; i < numEquipos-1; i++){
            Partido p = torneo.obtenerPartidoPorId(torneo.obtenerNodosHojaYPadres().get(i));
            int res1 = rand.nextInt(5);
            int res2;

            do {
                res2 = rand.nextInt(5);
            } while (res1 == res2);

            String ganador = res1 > res2 ? p.getEquipo1().getNombre() : p.getEquipo2().getNombre();
            textArea1.setText(torneo.jugarPartido(torneo.obtenerNodosHojaYPadres().get(i), ganador, String.valueOf(res1), String.valueOf(res2)));
            mostrarTorneo();
        }

        quemarDatosButton.setEnabled(false);

    }

    public void mostrarTorneo() {
        textArea.setText(torneo.representarArbol());
    }

}
