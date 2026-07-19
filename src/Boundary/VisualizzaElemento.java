package Boundary;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerElementi;
import Entity.Brano;
import Entity.Utente;

public class VisualizzaElemento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Titolo;
	JLabel Autore;
	JLabel Formato;
	JLabel Durata;
	JLabel Descrizione;
	JLabel DataCreazione;
	JLabel Dimensione;
	JLabel Visualizzazioni;
	JLabel Canali;
	JLabel Campionamento;
	JFrame Precedente;
	JTextArea DescrizioneIN;
	ControllerElementi MycEle;
	JButton AggiungiAPlaylist;
	public VisualizzaElemento(JFrame precedente, ControllerElementi mycEle) {
		Precedente=precedente;
		MycEle= mycEle;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Titolo = new JLabel("Titolo");
		Titolo.setFont(new Font("Lucida Grande", Font.BOLD, 34));
		Titolo.setBounds(6, 56, 888, 36);
		Titolo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Titolo);

		Autore = new JLabel("Autore");
		Autore.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Autore.setBounds(6, 104, 888, 25);
		Autore.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Autore);

		Formato = new JLabel("             Formato:  ");
		Formato.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Formato.setBounds(191, 162, 684, 25);
		contentPane.add(Formato);

		Durata = new JLabel("                Durata:  ");
		Durata.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Durata.setBounds(191, 199, 684, 25);
		contentPane.add(Durata);

		Descrizione = new JLabel("        Descrizione:  ");
		Descrizione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Descrizione.setBounds(191, 421, 178, 25);
		contentPane.add(Descrizione);
		
		DescrizioneIN= new JTextArea();
		DescrizioneIN.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		DescrizioneIN.setBounds(368, 422, 396, 65);
		DescrizioneIN.setEditable(false);
		DescrizioneIN.setLineWrap(true);
		DescrizioneIN.setWrapStyleWord(true);
		contentPane.add(DescrizioneIN);

		DataCreazione = new JLabel("  Data Creazione:  ");
		DataCreazione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		DataCreazione.setBounds(191, 236, 684, 25);
		contentPane.add(DataCreazione);

		Dimensione = new JLabel("Dimensione(MB):  ");
		Dimensione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Dimensione.setBounds(191, 273, 684, 25);
		contentPane.add(Dimensione);

		Visualizzazioni = new JLabel("  Visualizzazioni:  ");
		Visualizzazioni.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Visualizzazioni.setBounds(191, 310, 684, 25);
		contentPane.add(Visualizzazioni);

		Canali = new JLabel("                Canali:  ");
		Canali.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Canali.setBounds(191, 347, 684, 25);
		contentPane.add(Canali);

		Campionamento = new JLabel("Campionamento:  ");
		Campionamento.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Campionamento.setBounds(191, 384, 684, 25);
		contentPane.add(Campionamento);


		JButton Indietro = new JButton("Indietro");
		Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Indietro.setBounds(143, 498, 190, 80);
		contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycEle.ToPrecedente(VisualizzaElemento.this, Precedente);
			}
		});
		AggiungiAPlaylist = new JButton("Aggiungi a Playlist");
		AggiungiAPlaylist.setFont(new Font("Dialog", Font.PLAIN, 18));
		AggiungiAPlaylist.setBounds(574, 498, 190, 80);
		contentPane.add(AggiungiAPlaylist);
		}
	public void MostraElemento(Brano b) {
		Utente u= b.getCreatore();
		Date d= b.getDataCreazione();
		Titolo.setText(b.getTitolo());
		Autore.setText(u.getUsername());
		Formato.setText(Formato.getText() + b.getFormato());
		Durata.setText(Durata.getText() + MycEle.SecondiInStringa(b.getDurata()));
		DescrizioneIN.setText(b.getDescrizione());
		DataCreazione.setText(DataCreazione.getText() + d.toString());
		Dimensione.setText(Dimensione.getText() + b.getDimensione());
		Visualizzazioni.setText(Visualizzazioni.getText() + b.getVisualizzazioni());
		Canali.setText(Canali.getText() + b.getCanali());
		Campionamento.setText(Campionamento.getText() + b.getCampionamento());


		AggiungiAPlaylist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycEle.ScegliPlaylist(VisualizzaElemento.this, b);
			}
		});
	}

}
