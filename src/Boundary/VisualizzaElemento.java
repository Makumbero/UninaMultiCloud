package Boundary;
import Entity.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.*;
import Entity.*;

import javax.swing.JLabel;
import javax.swing.JButton;

public class VisualizzaElemento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
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
	ControllerElementi MycEle;
	public VisualizzaElemento(JFrame precedente, ControllerElementi mycEle) {
		Precedente=precedente;
		MycEle= mycEle;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Titolo = new JLabel("Titolo: ");
		Titolo.setBounds(22, 36, 386, 16);
		contentPane.add(Titolo);
		
		Autore = new JLabel("Autore: ");
		Autore.setBounds(22, 64, 386, 16);
		contentPane.add(Autore);
		
		Formato = new JLabel("Formato: ");
		Formato.setBounds(22, 92, 386, 16);
		contentPane.add(Formato);
		
		Durata = new JLabel("Durata: ");
		Durata.setBounds(22, 120, 386, 16);
		contentPane.add(Durata);
		
		Descrizione = new JLabel("Descrizione: ");
		Descrizione.setBounds(22, 148, 386, 16);
		contentPane.add(Descrizione);
		
		DataCreazione = new JLabel("Data Creazione: ");
		DataCreazione.setBounds(22, 176, 386, 16);
		contentPane.add(DataCreazione);
		
		Dimensione = new JLabel("Dimensione: ");
		Dimensione.setBounds(22, 204, 386, 16);
		contentPane.add(Dimensione);
		
		Visualizzazioni = new JLabel("Visualizzazioni: ");
		Visualizzazioni.setBounds(22, 232, 386, 16);
		contentPane.add(Visualizzazioni);
		
		Canali = new JLabel("Canali: ");
		Canali.setBounds(22, 260, 386, 16);
		contentPane.add(Canali);
		
		Campionamento = new JLabel("Campionamento: ");
		Campionamento.setBounds(22, 288, 386, 16);
		contentPane.add(Campionamento);
		
		JButton AggiungiAPlaylist = new JButton("Aggiungi a Playlist");
		AggiungiAPlaylist.setBounds(415, 336, 182, 49);
		contentPane.add(AggiungiAPlaylist);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setBounds(123, 336, 182, 49);
		contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycEle.ToPrecedente(VisualizzaElemento.this, Precedente);
			}
		});}
	public void MostraElemento(Brano b) {
		Utente u= b.getCreatore();
		Date d= b.getDataCreazione();
		Titolo.setText(Titolo.getText() + b.getTitolo());
		Autore.setText(Autore.getText() + u.getUsername());
		Formato.setText(Formato.getText() + b.getFormato());
		Durata.setText(Durata.getText() + b.getDurata());
		Descrizione.setText(Descrizione.getText() + b.getDescrizione());
		DataCreazione.setText(DataCreazione.getText() + d.toString());
		Dimensione.setText(Dimensione.getText() + b.getDimensione());
		Visualizzazioni.setText(Visualizzazioni.getText() + b.getVisualizzazioni());
		Canali.setText(Canali.getText() + b.getCanali());
		Campionamento.setText(Campionamento.getText() + b.getCampionamento());
	}

}
