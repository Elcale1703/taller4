package consola.taller4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.Tablero;

import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("unused")
public class Consola {
	
	private Tablero tablero;
	
	private int jugadas = 0;
		
	public static void main(String[] args) {
		Consola consola = new Consola();
        consola.init(); // Método para inicializar la interfaz gráfica
	}
	
	private void init() {
		FlatLightLaf.install();
	    // Crear el marco principal
	    JFrame frame = new JFrame("LightsOut");
	    
	    File imageFile = new File("./data/luz.png");
	    ImageIcon encendidoIcon = new ImageIcon(imageFile.getAbsolutePath());
	    
	    File imageFile2 = new File("./data/luz.png");
	    ImageIcon apagadoIcon = new ImageIcon(imageFile.getAbsolutePath());

	    // Establecer el diseño principal
	    frame.setLayout(new BorderLayout());

	    // Panel superior con radio buttons
	    JPanel topPanel = new JPanel();
	    topPanel.setLayout(new FlowLayout());
	    topPanel.setBackground(new Color(85, 152, 224)); // Color azul claro

	    JLabel label1Top = new JLabel("Tamano: ");
	    JLabel label2Top = new JLabel("Dificultad: ");
	    JRadioButton radioButton1 = new JRadioButton("Facil");
	    JRadioButton radioButton2 = new JRadioButton("Medio");
	    JRadioButton radioButton3 = new JRadioButton("Dificil");
	    
	    JComboBox<String> comboBox = new JComboBox<>(new String[]{"5x5", "10x10", "15x15"});
	    
	    tablero = new Tablero(5);

	    // Agrega un ActionListener al JComboBox para detectar los cambios en la selección
	            
	            // Crea un nuevo Tablero con el tamaño seleccionad

	    label1Top.setForeground(Color.WHITE);
	    Font fontNegrita = new Font("Arial", Font.BOLD, 12);
	    label1Top.setFont(fontNegrita);
	    label2Top.setForeground(Color.WHITE);
	    label2Top.setFont(fontNegrita);
	    radioButton1.setForeground(Color.WHITE);
	    radioButton2.setForeground(Color.WHITE);
	    radioButton3.setForeground(Color.WHITE);

	    ButtonGroup radioButtonGroup = new ButtonGroup();
	    radioButton1.setSelected(true); // Establecer uno de los RadioButtons seleccionado por defecto
	    radioButtonGroup.add(radioButton1);
	    radioButtonGroup.add(radioButton2);
	    radioButtonGroup.add(radioButton3);

	    topPanel.add(label1Top);
	    topPanel.add(comboBox);
	    topPanel.add(label2Top);
	    topPanel.add(radioButton1);
	    topPanel.add(radioButton2);
	    topPanel.add(radioButton3);

	    // Panel del medio con un JComboBox para desplegar opciones
	 // Panel de la mitad con BorderLayout
	    JPanel middlePanel = new JPanel();
	    middlePanel.setLayout(new BorderLayout());

	    // Panel izquierdo para la lógica del juego
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new FlowLayout());
	    

	    // Agrega la lógica del juego (clase Tablero) al panel izquierd
	    // Puedes ajustar el tamaño del tablero según tus necesidades
	    comboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Obtiene el elemento seleccionado en el JComboBox
	            String selectedSize = (String) comboBox.getSelectedItem();
	            
	            int size = 5;
	            int numButtons = 5;  // Tamaño predeterminado (5x5)

	            if (selectedSize.equals("10x10")) {
	                size = 10;
	                numButtons = 10;
	            } else if (selectedSize.equals("15x15")) {
	                size = 15;
	                numButtons = 15;
	            }
	            
	            tablero = new Tablero(size);
	            System.out.println(tablero);
	            
	            JPanel boardPanel = new JPanel(new GridLayout(size, size));
	            JButton[][] buttons = new JButton[size][size];
	            
	            

	            for (int i = 0; i < size; i++) {
	                for (int j = 0; j < size; j++) {
	                    JButton button = new JButton(apagadoIcon);
	                    button.setBackground(new Color(0, 0, 0));
	                    button.addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            // Cambia la apariencia del botón y actualiza la lógica del juego
	                            if (button.getIcon() == apagadoIcon) {
	                            	
	                                button.setIcon(encendidoIcon);
	                                jugadas = tablero.darJugadas();
	                                System.out.println(jugadas);
	                                button.setBackground(new Color(255, 255, 0));
	                                // Aquí debes implementar la lógica para cambiar el estado del botón y su vecindad.
	                            } else {
	                                button.setIcon(apagadoIcon);
	                                // Aquí debes implementar la lógica para cambiar el estado del botón y su vecindad.
	                            }
	                        }
	                    });
	                    boardPanel.add(button);
	                    buttons[i][j] = button;
	                }
	            }

	            // Agrega el panel del tablero al panel izquierdo
	            leftPanel.removeAll();
	            leftPanel.add(boardPanel);
	            leftPanel.revalidate();
	            leftPanel.repaint();

	            // Aquí puedes configurar el tablero y gestionar las jugadas
	        }
	    });
	        
	    
	    
	    // Agrega métodos y lógica para interactuar con el tablero aquí
	    
	    
	    // Agrega el panel izquierdo al lado izquierdo del panel de la mitad
	    middlePanel.add(leftPanel, BorderLayout.WEST);

	    // Panel derecho para los botones
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

	    // Crea los botones y agrégales funcionalidad
	    JLabel espacio3 = new JLabel(" ");
	    JLabel espacio4 = new JLabel(" ");
	    JLabel espacio5 = new JLabel(" ");
	    JLabel espacio6 = new JLabel(" ");
	    JLabel espacio7 = new JLabel(" ");
	    JLabel espacio = new JLabel(" ");
	    JLabel espacio1 = new JLabel(" ");
	    JLabel espacio2 = new JLabel(" ");
	    JButton button1 = new JButton("           NUEVO          ");
	    JButton button2 = new JButton("         REINICIAR       ");
	    JButton button3 = new JButton("          TOP-10           ");
	    JButton button4 = new JButton("CAMBIAR JUGADOR");

	    // Configura un BoxLayout con alineación centrada

	    // Configura la apariencia de los botones
	    button1.setForeground(Color.WHITE);
	    button2.setForeground(Color.WHITE);
	    button3.setForeground(Color.WHITE);
	    button4.setForeground(Color.WHITE);

	    button1.setBackground(new Color(85, 152, 224));
	    button2.setBackground(new Color(85, 152, 224));
	    button3.setBackground(new Color(85, 152, 224));
	    button4.setBackground(new Color(85, 152, 224));

	    // Establece un tamaño fijo para todos los botones
	    Dimension buttonSize = new Dimension(140, 30); 
	    button1.setPreferredSize(buttonSize);
	    button2.setPreferredSize(buttonSize);
	    button3.setPreferredSize(buttonSize);
	    button4.setPreferredSize(buttonSize);

	    // Agrega los botones al panel derecho
	    rightPanel.add(espacio3);
	    rightPanel.add(espacio4);
	    rightPanel.add(espacio5);
	    rightPanel.add(espacio6);
	    rightPanel.add(espacio7);
	    rightPanel.add(button1);
	    rightPanel.add(espacio);
	    rightPanel.add(button2);
	    rightPanel.add(espacio1);
	    rightPanel.add(button3);
	    rightPanel.add(espacio2);
	    rightPanel.add(button4);

	    // Agrega el panel derecho al lado derecho del panel de la mitad
	    middlePanel.add(rightPanel, BorderLayout.EAST);
	    

	    // Agregar un ActionListener para el JComboBox

	    // middlePanel.add(comboBox);

	    // Panel inferior con etiquetas y campos de texto
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setLayout(new GridLayout());
	    

	    JLabel label1 = new JLabel("Jugadas:");
	    JTextField textField1 = new JTextField(jugadas); // Configura el valor inicial y lo hace no editable
	    textField1.setEditable(false); // Hace que el campo de texto sea no editable

	    JLabel label2 = new JLabel("Jugador:");
	    JTextField textField2 = new JTextField(""); // Configura el valor inicial
	    textField2.setEditable(false); // Hace que el campo de texto sea no editable

	    
	    bottomPanel.add(label1);
	    bottomPanel.add(textField1);
	    bottomPanel.add(label2);
	    bottomPanel.add(textField2);

	    // Agregar los paneles al marco principal
	    frame.add(topPanel, BorderLayout.NORTH);
	    frame.add(middlePanel, BorderLayout.CENTER);
	    frame.add(bottomPanel, BorderLayout.SOUTH);

	    // Configurar el marco
	    frame.setSize(500, 400);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}    
}
	
	

