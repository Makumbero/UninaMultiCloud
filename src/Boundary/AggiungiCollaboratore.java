package Boundary;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerElementi;
import Entity.Brano;

public class AggiungiCollaboratore extends JFrame {

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
	public AggiungiCollaboratore(JFrame precedente, ControllerElementi c) {
		Precedente=precedente;
		MycEle=c;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Titolo = new JLabel("Titolo: ");
		Titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Titolo.setBounds(158, 157, 175, 26);
		contentPane.add(Titolo);

		JLabel Formato = new JLabel("Formato: ");
		Formato.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Formato.setBounds(158, 195, 175, 26);
		contentPane.add(Formato);

		JLabel Durata = new JLabel("Durata: ");
		Durata.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Durata.setBounds(158, 233, 175, 26);
		contentPane.add(Durata);

		JLabel Descrizione = new JLabel("Descrizione: ");
		Descrizione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Descrizione.setBounds(158, 270, 175, 26);
		contentPane.add(Descrizione);

		JLabel Dimensione = new JLabel("Dimensione (MB): ");
		Dimensione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Dimensione.setBounds(158, 306, 175, 26);
		contentPane.add(Dimensione);

		JLabel Canali = new JLabel("Canali: ");
		Canali.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Canali.setBounds(158, 344, 175, 26);
		contentPane.add(Canali);

		JLabel Campionamento = new JLabel("Campionamento: ");
		Campionamento.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Campionamento.setBounds(158, 380, 175, 26);
		contentPane.add(Campionamento);



		FieldTitolo = new JTextField();
		FieldTitolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldTitolo.setBounds(370, 157, 394, 26);
		contentPane.add(FieldTitolo);
		FieldTitolo.setColumns(10);

		FieldFormato = new JTextField();
		FieldFormato.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldFormato.setBounds(370, 195, 394, 26);
		contentPane.add(FieldFormato);
		FieldFormato.setColumns(10);

		FieldDescrizione = new JTextField();
		FieldDescrizione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldDescrizione.setBounds(370, 269, 394, 26);
		contentPane.add(FieldDescrizione);
		FieldDescrizione.setColumns(10);

		FieldDurata = new JTextField();
		FieldDurata.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldDurata.setBounds(370, 232, 394, 26);
		contentPane.add(FieldDurata);
		FieldDurata.setColumns(10);

		FieldDimensione = new JTextField();
		FieldDimensione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldDimensione.setBounds(370, 305, 395, 26);
		contentPane.add(FieldDimensione);
		FieldDimensione.setColumns(10);

		FieldCanali = new JTextField();
		FieldCanali.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldCanali.setBounds(370, 343, 394, 26);
		contentPane.add(FieldCanali);
		FieldCanali.setColumns(10);

		FieldCampionamento = new JTextField();
		FieldCampionamento.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldCampionamento.setBounds(370, 380, 394, 26);
		contentPane.add(FieldCampionamento);
		FieldCampionamento.setColumns(10);


		JButton Indietro = new JButton("Indietro");
		Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Indietro.setBounds(143, 498, 190, 80);
		contentPane.add(Indietro);
        Indietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycEle.ToPrecedente(AggiungiCollaboratore.this, Precedente);
			}
			});

		JButton Crea = new JButton("Crea");
		Crea.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Crea.setBounds(574, 498, 190, 80);
		contentPane.add(Crea);
        Crea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycEle.SalvaCreazione(FieldTitolo.getText(), FieldFormato.getText(), FieldDescrizione.getText(), FieldDurata.getText(), FieldDimensione.getText(), FieldCanali.getText(), FieldCampionamento.getText());
			}
			});
	}

}
