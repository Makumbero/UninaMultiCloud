package Boundary;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import Control.*;
import Entity.*;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class ModificaElemento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldTitolo;
	private JTextField FieldFormato;
	private JTextField FieldDescrizione;
	private Brano MyBrano;
	private ControllerElementi MycEle;
	private JFrame Precedente;
	private JTextField FieldDurata;
	private JTextField FieldDimensione;
	private JTextField FieldCanali;
	private JTextField FieldCampionamento;
	/**
	 * Create the frame.
	 */
	public ModificaElemento(JFrame precedente, ControllerElementi c, Brano b) {
		Precedente=precedente;
		MycEle=c;
		MyBrano=b;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titolo = new JLabel("Titolo: ");
		Titolo.setBounds(24, 58, 61, 16);
		contentPane.add(Titolo);
		
		JLabel Formato = new JLabel("Formato: ");
		Formato.setBounds(24, 86, 61, 16);
		contentPane.add(Formato);
		
		JLabel Durata = new JLabel("Durata: ");
		Durata.setBounds(24, 114, 61, 16);
		contentPane.add(Durata);
		
		JLabel Descrizione = new JLabel("Descrizione: ");
		Descrizione.setBounds(24, 142, 105, 16);
		contentPane.add(Descrizione);
		
		JLabel Dimensione = new JLabel("Dimensione (MB): ");
		Dimensione.setBounds(24, 170, 124, 16);
		contentPane.add(Dimensione);
		
		JLabel Canali = new JLabel("Canali: ");
		Canali.setBounds(24, 198, 61, 16);
		contentPane.add(Canali);
		
		JLabel Campionamento = new JLabel("Campionamento: ");
		Campionamento.setBounds(24, 226, 124, 16);
		contentPane.add(Campionamento);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setBounds(149, 328, 142, 53);
		contentPane.add(Indietro);
        Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycEle.ToPrecedente(ModificaElemento.this, Precedente);
			}
			});
		
		JButton SalvaModifiche = new JButton("Salva Modifiche");
		SalvaModifiche.setBounds(430, 328, 142, 53);
		contentPane.add(SalvaModifiche);
		
		
		FieldTitolo = new JTextField(MyBrano.getTitolo());
		FieldTitolo.setBounds(161, 53, 394, 26);
		contentPane.add(FieldTitolo);
		FieldTitolo.setColumns(10);
		String TitoloIniziale=FieldTitolo.getText();
		
		FieldFormato = new JTextField(MyBrano.getFormato());
		FieldFormato.setBounds(161, 81, 394, 26);
		contentPane.add(FieldFormato);
		FieldFormato.setColumns(10);
		String FormatoIniziale=FieldFormato.getText();
		
		FieldDescrizione = new JTextField(MyBrano.getDescrizione());
		FieldDescrizione.setBounds(161, 137, 394, 26);
		contentPane.add(FieldDescrizione);
		FieldDescrizione.setColumns(10);
		String DescrizioneIniziale=FieldDescrizione.getText();
		
		FieldDurata = new JTextField(String.valueOf(MyBrano.getDurata()));
		FieldDurata.setBounds(161, 109, 394, 26);
		contentPane.add(FieldDurata);
		FieldDurata.setColumns(10);
		String DurataIniziale=FieldDurata.getText();
		
		FieldDimensione = new JTextField(String.valueOf(MyBrano.getDimensione()));
		FieldDimensione.setBounds(160, 165, 395, 26);
		contentPane.add(FieldDimensione);
		FieldDimensione.setColumns(10);
		String DimensioneIniziale=FieldDimensione.getText();
		
		FieldCanali = new JTextField(String.valueOf(MyBrano.getCanali()));
		FieldCanali.setBounds(161, 193, 394, 26);
		contentPane.add(FieldCanali);
		FieldCanali.setColumns(10);
		String CanaliIniziale=FieldCanali.getText();
		
		FieldCampionamento = new JTextField(String.valueOf(MyBrano.getCampionamento()));
		FieldCampionamento.setBounds(161, 221, 394, 26);
		contentPane.add(FieldCampionamento);
		FieldCampionamento.setColumns(10);
		String CampionamentoIniziale=FieldCampionamento.getText();

        SalvaModifiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(TitoloIniziale.equals(FieldTitolo.getText()))) {
					System.out.println("Titolo Modificato");
				}
			}
		});
	}
}
