package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerCerca;
import Entity.Brano;

public class RisultatiRicerca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloRisultati;
	ControllerCerca cCerca;

	public RisultatiRicerca(ControllerCerca cCerca) {
		this.cCerca=cCerca;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(61, 10, 339, 195);
		contentPane.add(Scorrimento);
		
        PannelloRisultati = new JPanel();
		Scorrimento.setViewportView(PannelloRisultati);

		
	}
	public void MostraElementi(List <Brano> Brani) {
	    PannelloRisultati.removeAll();

	    PannelloRisultati.setLayout(new BoxLayout(PannelloRisultati, BoxLayout.Y_AXIS));

	    for (Brano brano : Brani) {

	        JPanel riga = new JPanel(new BorderLayout());

	        JButton titolo = new JButton(brano.getTitolo());
			titolo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cCerca.VisualizzaElemento( brano);
				}
			});

	        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	        JButton Modifica = new JButton("Modifica");
	        
	        JButton Elimina = new JButton("Elimina");

	        pulsanti.add(Modifica);
	        Modifica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//MycEle.ModificaElemento(MieiElementi.this, brano);
				}
			});
	        
	        pulsanti.add(Elimina);
	        Elimina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Brano eliminato: "+ brano.getTitolo());
				}
			});
	        
	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(pulsanti, BorderLayout.EAST);

	        PannelloRisultati.add(riga);
	    }

	    PannelloRisultati.revalidate();
	    PannelloRisultati.repaint();
	}

}
