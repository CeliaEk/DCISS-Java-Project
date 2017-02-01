package arbrelexicographique;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interface extends ArbreLexicographique {
	private JFrame frame;
	private JTextField textField;
	private ArbreLexicographique arbre;
	private JTree tree;
	JLabel lblStatut = new JLabel();
	JLabel lblNbrMots = new JLabel("Nombre de mot(s) présent(s)");

	
	public Interface() {
		arbre = new ArbreLexicographique();
        initialize();
        arbre.setVue(tree);
        tree.setModel(arbre);
        lblStatut.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatut.setVerticalAlignment(SwingConstants.CENTER);
        Font font = new Font("Arial",Font.CENTER_BASELINE,20);
        lblStatut.setFont(font);
        frame.getContentPane().add(lblStatut, BorderLayout.SOUTH);
    }
	
	public void initialize() {
		
		TextArea textArea = new TextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		
		frame = new JFrame("Arbre Lexicographique");
		frame.setBounds(100, 100, 700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel Top = new JPanel();
		frame.getContentPane().add(Top, BorderLayout.NORTH);
		Top.setLayout(new GridLayout(2, 0, 0, 0));
		
		
		JPanel Menu = new JPanel();
		Top.add(Menu);
		Menu.setLayout(new GridLayout(1, 0, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		Menu.add(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmCharger = new JMenuItem("Charger");
		mntmCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Sélectionnez le fichier à importer");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);
				
				if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					arbre.charge(chooser.getSelectedFile().getAbsolutePath());
					textArea.setText(arbre.toString());
					lblNbrMots.setText("Nombre de mots : " + arbre.nbMots());
					lblStatut.setText("Fichier bien chargé ");
				}
				for (int i = 0; i < tree.getRowCount(); i++) {
				    tree.expandRow(i);
				}
			}
			});
		
		mnFichier.add(mntmCharger);
		
		JMenuItem mntmSauvegarder = new JMenuItem("Enregistrer");
		mntmSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Sélectionnez un emplacement pour sauvegarder votre arbre");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					arbre.sauve(chooser.getSelectedFile().getAbsolutePath());
					lblNbrMots.setText("Nombre de mots : " + arbre.nbMots());
					lblStatut.setText("Fichier sauvegardé ");
				}
			}
		});
		
		mnFichier.add(mntmSauvegarder);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFichier.add(mntmQuitter);
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JPanel Boutons = new JPanel();
		Top.add(Boutons);
		Boutons.setLayout(new GridLayout(1, 6, 0, 0));
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arbre.contient(textField.getText())){
					JOptionPane.showMessageDialog(frame,
						    "Ce mot est déjà présent dans l'arbre",
						    "Erreur",
						    JOptionPane.ERROR_MESSAGE);
				}
				if(textField.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame,
						    "Veuillez entrer un mot à ajouter",
						    "Erreur",
						    JOptionPane.ERROR_MESSAGE);
				}
				else{
				arbre.ajout(textField.getText().toLowerCase());
				textField.setText("");
				textArea.setText(arbre.toString());
				lblNbrMots.setText("Nombre de mots : " + arbre.nbMots());
				lblStatut.setText("Le mot a été ajouté ");
				}
				for (int i = 0; i < tree.getRowCount(); i++) {
				    tree.expandRow(i);
				}
			}
		});
		Boutons.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame,
						    "Entrez un mot à supprimer",
						    "Erreur",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(arbre.contient(textField.getText())){
					arbre.suppr(textField.getText());
					textField.setText("");
					textArea.setText(arbre.toString());
					lblNbrMots.setText("Nombre de mots : " + arbre.nbMots());
					lblStatut.setText("Le mot a été supprimé ");
					}
					else{
						JOptionPane.showMessageDialog(frame,
							    "Le mot n'est pas présent dans l'arbre",
							    "Erreur",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				for (int i = 0; i < tree.getRowCount(); i++) {
				    tree.expandRow(i);
				}
			}
		});
		Boutons.add(btnSupprimer);
		
		JButton btnChercher = new JButton("Chercher");
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame,
						    "Veuillez entrer un mot",
						    "Erreur",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if (arbre.contient(textField.getText())){
					lblStatut.setText("le mot " + textField.getText() + " est présent dans l'arbre ");
				} else {
					lblStatut.setText("le mot " + textField.getText() + " n'est pas présent dans l'arbre ");
				}
				
			}
		});
		Boutons.add(btnChercher);
		
		JButton btnPrefixe = new JButton("Prefixe");
		btnPrefixe.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(textField.getText().isEmpty()){
				JOptionPane.showMessageDialog(frame,
					    "Veuillez entrer un mot",
					    "Erreur",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if (arbre.prefixe(textField.getText())){
				lblStatut.setText("le mot "+textField.getText()+" est prefixe ");
			} else {
				lblStatut.setText("le mot "+textField.getText()+" n'est pas prefixe ");
			}
			textField.setText("");
			textArea.setText(arbre.toString());
		}
	});

		Boutons.add(btnPrefixe);
		
		JLabel lblQuoi = new JLabel("Quoi ?");
		lblQuoi.setHorizontalAlignment(SwingConstants.CENTER);
		Boutons.add(lblQuoi);
		
		textField = new JTextField();
		Boutons.add(textField);
		textField.setColumns(10);
		
		JPanel Main = new JPanel();
		frame.getContentPane().add(Main, BorderLayout.CENTER);
		Main.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		Main.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane PanelArbre = new JScrollPane();
		tabbedPane.addTab("Arbre", null, PanelArbre, null);
		
		tree = new JTree();
		PanelArbre.setViewportView(tree);
		
		JPanel PanelListe = new JPanel();
		tabbedPane.addTab("Liste", null, PanelListe, null);
		textArea.setText(arbre.toString());
		PanelListe.add(textArea);
		
		
		Main.add(lblNbrMots, BorderLayout.SOUTH);
	
	}

	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                
                    Interface window = new Interface();
                    window.frame.setVisible(true);
                  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}


