package Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Control.*;
import Entity.*;

import javax.swing.JLabel;
import java.awt.Font;

public class VisualizzaPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloBrani;
	private ControllerPlaylist MycPl;
	private ControllerElementi MycEle;
	private JFrame Precedente;
	/**
	 * Create the frame.
	 */
	public VisualizzaPlaylist(Playlist Playlist, List<Brano> Brani, ControllerPlaylist mycPl, ControllerElementi mycEle, JFrame precedente) {
		MycPl=mycPl;
		MycEle=mycEle;
		Precedente=precedente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titolo = new JLabel(Playlist.getTitolo());
		Titolo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		Titolo.setHorizontalAlignment(JLabel.CENTER);
		Titolo.setBounds(143, 6, 624, 25);
		contentPane.add(Titolo);
		
		JLabel Creatore = new JLabel(Playlist.getCreatore().getUsername());
		Creatore.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		Creatore.setHorizontalAlignment(JLabel.CENTER);
		Creatore.setBounds(143, 43, 624, 20);
		contentPane.add(Creatore);
		
		JLabel Data = new JLabel("Data Creazione: "+Playlist.getDataCreazione().toString());
		Data.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		Data.setBounds(143, 476, 276, 25);
		contentPane.add(Data);
		
		JLabel Tipo = new JLabel();
		Tipo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		Tipo.setText("Tipo");
		if(Playlist instanceof PlaylistPrivata) {
			Tipo.setText("Playlist Privata");
		}else if(Playlist instanceof PlaylistPubblica) {
			Tipo.setText("Platlist Pubblica");
		}else {
			Tipo.setText("Playlist Condivisa");
		}
		Tipo.setBounds(643, 476, 124, 25);
		contentPane.add(Tipo);
		
		JLabel Descrizione = new JLabel("Descrizione: "+Playlist.getDescrizione());
		Descrizione.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		Descrizione.setBounds(143, 75, 624, 16);
		contentPane.add(Descrizione);
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(143, 103, 624, 370);
		contentPane.add(Scorrimento);
		
        PannelloBrani = new JPanel();
		Scorrimento.setViewportView(PannelloBrani);
		
		   PannelloBrani.removeAll();

		    PannelloBrani.setLayout(new BoxLayout(PannelloBrani, BoxLayout.Y_AXIS));
		    
		    JButton Indietro = new JButton("Indietro");
		    Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		    Indietro.setBounds(356, 498, 190, 80);
		    contentPane.add(Indietro);
			Indietro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MycPl.ToPrecedente(VisualizzaPlaylist.this, Precedente);
				}
			});
		    
		    
		    for (Brano brano : Brani) {

		        JPanel riga = new JPanel(new BorderLayout());
		        riga.setMaximumSize(new Dimension(Scorrimento.getWidth(), 50));

		        JButton titolo = new JButton(brano.getTitolo());
		        titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				titolo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MycEle.VisualizzaElemento(VisualizzaPlaylist.this, brano);
					}
				});
		        
		        riga.add(titolo, BorderLayout.CENTER);

		        PannelloBrani.add(riga);
		    }

		    PannelloBrani.revalidate();
		    PannelloBrani.repaint();

	}
}
