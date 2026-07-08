package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.*;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import Entity.*;

public class MieiElementi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloBrani;
	private ControllerElementi MycEle;
	private JFrame Precedente;
	/**
	 * Create the frame.
	 */
	public MieiElementi(ControllerElementi mycEle, JFrame precedente) {
		MycEle=mycEle;
		Precedente=precedente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setBounds(93, 334, 159, 61);
		contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycEle.ToPrecedente(MieiElementi.this, Precedente);
			}
		});
		
		JButton CreaElemento = new JButton("CreaElemento");
		CreaElemento.setBounds(373, 334, 159, 61);
		contentPane.add(CreaElemento);
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(71, 39, 467, 271);
		contentPane.add(Scorrimento);
		
        PannelloBrani = new JPanel();
		Scorrimento.setViewportView(PannelloBrani);

	}
	public void MostraElementi(List <Brano> Brani) {
	    PannelloBrani.removeAll();

	    PannelloBrani.setLayout(new BoxLayout(PannelloBrani, BoxLayout.Y_AXIS));

	    for (Brano brano : Brani) {

	        JPanel riga = new JPanel(new BorderLayout());

	        JButton titolo = new JButton(brano.getTitolo());
			titolo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MycEle.VisualizzaElemento(MieiElementi.this, brano);
				}
			});

	        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	        JButton Modifica = new JButton("Modifica");
	        JButton Elimina = new JButton("Elimina");

	        pulsanti.add(Modifica);
	        pulsanti.add(Elimina);

	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(pulsanti, BorderLayout.EAST);

	        PannelloBrani.add(riga);
	    }

	    PannelloBrani.revalidate();
	    PannelloBrani.repaint();
	}
}
