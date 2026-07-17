package Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerElementi;
import Control.ControllerPlaylist;
import Entity.Brano;
import Entity.Playlist;
import Entity.PlaylistCondivisa;
import javax.swing.JLabel;

public class ModificaPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField FieldTitolo;
	private JTextField FieldDescrizione;
	private JPanel contentPane;
	private JPanel PannelloBrani;
	private ControllerPlaylist MycPl;
	private ControllerElementi MycEle;
	private JFrame Precedente;
	/**
	 * Create the frame.
	 */
	public ModificaPlaylist(Playlist Playlist, List<Brano> Brani, ControllerPlaylist mycPl, ControllerElementi mycEle, JFrame precedente) {
		MycPl=mycPl;
		MycEle=mycEle;
		Precedente=precedente;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        contentPane.setLayout(null);

		FieldTitolo = new JTextField(Playlist.getTitolo());
		FieldTitolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldTitolo.setBounds(230, 24, 372, 26);
		contentPane.add(FieldTitolo);
		FieldTitolo.setColumns(10);

		FieldDescrizione = new JTextField(Playlist.getDescrizione());
		FieldDescrizione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FieldDescrizione.setBounds(277, 60, 347, 26);
		contentPane.add(FieldDescrizione);
		FieldDescrizione.setColumns(10);

	    JButton Salva = new JButton("Salva");
	    Salva.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
	    Salva.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		MycPl.SalvaModifiche(Playlist, FieldTitolo.getText(), FieldDescrizione.getText());
	    	}
	    });
	    Salva.setBounds(634, 11, 130, 80);
	    contentPane.add(Salva);

        JButton Indietro = new JButton("Indietro");
        Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        Indietro.setBounds(143, 498, 190, 80);
        contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycPl.ToPrecedente(ModificaPlaylist.this, Precedente);
			}
		});

		if(Playlist instanceof PlaylistCondivisa) {
	        JButton Collaboratori = new JButton("Collaboratori");
	        Collaboratori.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
	        Collaboratori.setBounds(359, 498, 190, 80);
	        contentPane.add(Collaboratori);
			Collaboratori.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MycPl.Collaboratori(ModificaPlaylist.this, Playlist);
				}
			});
		}

        JButton Elimina = new JButton("Elimina");
        Elimina.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        Elimina.setBounds(574, 498, 190, 80);
        contentPane.add(Elimina);
		Elimina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycPl.EliminaPlaylist(ModificaPlaylist.this, Playlist);
			}
		});

		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(143, 103, 624, 370);
		contentPane.add(Scorrimento);

        PannelloBrani = new JPanel();
		Scorrimento.setViewportView(PannelloBrani);

		   PannelloBrani.removeAll();

		    PannelloBrani.setLayout(new BoxLayout(PannelloBrani, BoxLayout.Y_AXIS));
		    
		    JLabel Titolo = new JLabel("Titolo:");
		    Titolo.setFont(new Font("Dialog", Font.PLAIN, 25));
		    Titolo.setBounds(143, 22, 77, 26);
		    contentPane.add(Titolo);
		    
		    JLabel lblDescrizione = new JLabel("Descrizione:");
		    lblDescrizione.setFont(new Font("Dialog", Font.PLAIN, 25));
		    lblDescrizione.setBounds(143, 58, 164, 26);
		    contentPane.add(lblDescrizione);



		    for (Brano brano : Brani) {
		    	 JPanel riga = new JPanel(new BorderLayout());
			        riga.setMaximumSize(new Dimension(Scorrimento.getWidth(), 50));

			        JButton titolo = new JButton(brano.getTitolo());
			        titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
					titolo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							MycEle.VisualizzaElemento(ModificaPlaylist.this, brano);
						}
					});

			        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));

			        JButton Rimuovi = new JButton("Rimuovi");
			        Rimuovi.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
					Rimuovi.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							MycPl.RimuoviElemento(ModificaPlaylist.this, Precedente, Playlist, brano);
						}
					});

			        pulsanti.add(Rimuovi);

			        riga.add(titolo, BorderLayout.CENTER);
			        riga.add(pulsanti, BorderLayout.EAST);

			        PannelloBrani.add(riga);
	}
	}	
}