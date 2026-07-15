package Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private JButton Salva;


	/**
	 * Create the frame.
	 */
	public ScegliPlaylist(ControllerPlaylist mycPl, JFrame precedente, Brano b) {
		MycPl=mycPl;
		Precedente=precedente;
		MyBrano=b;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Indietro.setBounds(143, 498, 190, 80);
		contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.ToPrecedente(ScegliPlaylist.this, Precedente);
			}
		});
		
		JLabel Titolo = new JLabel("In quale playlist vuoi inserire "+b.getTitolo()+"?");
		Titolo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		Titolo.setBounds(143, 55, 621, 36);
		Titolo.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(Titolo);
		
		
		Salva = new JButton("Salva");
		Salva.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Salva.setBounds(574, 498, 190, 80);
		contentPane.add(Salva);
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(143, 103, 624, 370);
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
	        riga.setMaximumSize(new Dimension(PannelloPlaylist.getWidth(), 50));

	        JLabel titolo = new JLabel(playlist.getTitolo());
	        titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

	        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        
	        
	        JCheckBox Check= new JCheckBox();

	        pulsanti.add(Check);
	        
	        CheckPlaylist.add(playlist);
	        CheckSpunte.add(Check);
	        
	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(pulsanti, BorderLayout.WEST);

	        PannelloPlaylist.add(riga);
	        

	    }

	    PannelloPlaylist.revalidate();
	    PannelloPlaylist.repaint();
        Salva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.SalvaScegliPlaylist(ScegliPlaylist.this, Precedente, CheckPlaylist, CheckSpunte, MyBrano);
			}
		});
	}

}
