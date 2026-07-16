package Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerCerca;
import Entity.Brano;
import Entity.Playlist;
import Entity.PlaylistCondivisa;
import Entity.PlaylistPubblica;
import Entity.Utente;

public class RisultatiRicerca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloRisultati;
	ControllerCerca cCerca;
	JFrame Precedente;
	JLabel risultato;

	public RisultatiRicerca(ControllerCerca cCerca,JFrame Precedente) {
		this.cCerca=cCerca;
		this.Precedente=Precedente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setBounds(143, 103, 624, 370);
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(Scorrimento);
		
        PannelloRisultati = new JPanel();
		Scorrimento.setViewportView(PannelloRisultati);
		
		JButton btnIndietro = new JButton("Indietro");
	    btnIndietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
	    btnIndietro.setBounds(356, 498, 190, 80);
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cCerca.ToPrecedente(RisultatiRicerca.this, Precedente);
			}
		});
		contentPane.add(btnIndietro);
		
		risultato = new JLabel("Risultati");
		risultato.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		risultato.setBounds(143, 66, 624, 25);
		contentPane.add(risultato);

		
	}
	public void MostraElementi(List <Brano> Brani, String ricerca) {
	    PannelloRisultati.removeAll();
	    
	    PannelloRisultati.setLayout(new BoxLayout(PannelloRisultati, BoxLayout.Y_AXIS));
	    

	    risultato.setText("Risultati della ricerca Brani per \""+ricerca+"\"");
	    
	    for (Brano brano : Brani) {

	        JPanel riga = new JPanel(new BorderLayout());
	        riga.setMaximumSize(new Dimension(PannelloRisultati.getWidth(), 50));

	        JButton titolo = new JButton(brano.getTitolo());

			titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			titolo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cCerca.VisualizzaElemento( brano);
				}
			});

	        JPanel autore = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        autore.setPreferredSize(new Dimension(PannelloRisultati.getWidth()/2, 50));
	        autore.setMinimumSize(new Dimension(PannelloRisultati.getWidth()/2, 50));
	        autore.setMaximumSize(new Dimension(PannelloRisultati.getWidth()/2, 50));

	        JLabel username = new JLabel(brano.getCreatore().getUsername());
			username.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			
			autore.add(username);




	        
	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(autore, BorderLayout.EAST);

	        PannelloRisultati.add(riga);
	    }

	    PannelloRisultati.revalidate();
	    PannelloRisultati.repaint();
	}
	public void MostraPlaylist(List <Playlist> listaPlaylist, String ricerca) {
	    PannelloRisultati.removeAll();

	    PannelloRisultati.setLayout(new BoxLayout(PannelloRisultati, BoxLayout.Y_AXIS));

	    risultato.setText("Risultati della ricerca Playlist per \""+ricerca+"\"");
	    
	    for (Playlist p : listaPlaylist) {

	        JPanel riga = new JPanel(new BorderLayout());
	        riga.setMaximumSize(new Dimension(PannelloRisultati.getWidth(), 50));

	        JButton titolo = new JButton(p.getTitolo());
			titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			titolo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cCerca.VisualizzaPlaylist(RisultatiRicerca.this, p);
				}
			});

	        JPanel autore = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        
	        JLabel username = new JLabel(p.getCreatore().getUsername());
			username.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

			autore.add(username);

	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(autore, BorderLayout.EAST);

	        PannelloRisultati.add(riga);
	    }

	    PannelloRisultati.revalidate();
	    PannelloRisultati.repaint();
	}
	public void MostraUser(List<Utente> listautente, String ricerca, Playlist playlist) {
		listautente=cCerca.rimuoviUtentiIndesiderati(listautente,playlist );
		PannelloRisultati.removeAll();

	    PannelloRisultati.setLayout(new BoxLayout(PannelloRisultati, BoxLayout.Y_AXIS));

	    risultato.setText("Risultati della ricerca Utenti per \""+ricerca+"\"");
	    for (Utente u : listautente) {

	        JPanel riga = new JPanel(new BorderLayout());
	        riga.setMaximumSize(new Dimension(PannelloRisultati.getWidth(), 50));

	        JButton titolo = new JButton(u.getUsername());
			titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			titolo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cCerca.getcPlay().AggiungiCollaboratore(playlist.getId(), u);
					cCerca.ToPrecedente(RisultatiRicerca.this, Precedente);
				}
			});

	        JPanel autore = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        
	        JLabel username = new JLabel(u.getEmail());
			username.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

			autore.add(username);

	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(autore, BorderLayout.EAST);

	        PannelloRisultati.add(riga);
	    }

	    PannelloRisultati.revalidate();
	    PannelloRisultati.repaint();
		
	}
}
