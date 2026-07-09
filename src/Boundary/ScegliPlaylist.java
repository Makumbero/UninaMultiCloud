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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Control.*;
import Entity.*;

public class ScegliPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloPlaylist;
	private ControllerPlaylist MycPl;
	private JFrame Precedente;
	private Brano MyBrano;



	/**
	 * Create the frame.
	 */
	public ScegliPlaylist(ControllerPlaylist mycPl, JFrame precedente, Brano b) {
		MycPl=mycPl;
		Precedente=precedente;
		MyBrano=b;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setBounds(154, 322, 93, 29);
		contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.ToPrecedente(ScegliPlaylist.this, Precedente);
			}
		});
		
        
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(90, 17, 464, 281);
		contentPane.add(Scorrimento);
		
        PannelloPlaylist = new JPanel();
        Scorrimento.setViewportView(PannelloPlaylist);

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
		JButton Salva = new JButton("Salva");
		Salva.setBounds(380, 322, 76, 29);
		contentPane.add(Salva);
        Salva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.SalvaScegliPlaylist(ScegliPlaylist.this, Precedente, CheckPlaylist, CheckSpunte, MyBrano);
			}
		});
	}

}
