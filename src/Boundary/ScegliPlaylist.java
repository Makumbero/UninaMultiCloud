package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.*;
import Entity.*;

public class ScegliPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloPlaylist;
	private ControllerPlaylist MycPl;
	private JFrame Precedente;



	/**
	 * Create the frame.
	 */
	public ScegliPlaylist(ControllerPlaylist mycPl, JFrame precedente) {
		MycPl=mycPl;
		Precedente=precedente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setBounds(93, 334, 159, 61);
		contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.ToPrecedente(ScegliPlaylist.this, Precedente);
			}
		});
		
		JButton Salva = new JButton("Salva");
		Salva.setBounds(373, 334, 159, 61);
		contentPane.add(Salva);
        Salva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

	}
	public void MostraPlaylist(List <Playlist> ListaPlaylist) {
	    PannelloPlaylist.removeAll();

	    PannelloPlaylist.setLayout(new BoxLayout(PannelloPlaylist, BoxLayout.Y_AXIS));
	    
	    ArrayList<Playlist> CheckPlaylist = new ArrayList<>();
	    ArrayList<JCheckBox> CheckSpunte = new ArrayList<>();
	    
	    for (Playlist playlist : ListaPlaylist) {

	        JPanel riga = new JPanel(new BorderLayout());

	        JLabel titolo = new JLabel(playlist.getTitolo());

	        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        
	        
	        JCheckBox Check= new JCheckBox();

	        pulsanti.add(Check);
	        
	        CheckPlaylist.add(playlist);
	        CheckSpunte.add(Check);
	        
	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(pulsanti, BorderLayout.EAST);

	        PannelloPlaylist.add(riga);
	    }

	    PannelloPlaylist.revalidate();
	    PannelloPlaylist.repaint();
	}

}
