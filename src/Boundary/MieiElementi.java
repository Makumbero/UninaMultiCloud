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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerElementi;
import Entity.Brano;

public class MieiElementi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloBrani;
	private ControllerElementi MycEle;
	private JFrame Precedente;

	public MieiElementi(ControllerElementi mycEle, JFrame precedente) {
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

		JButton Indietro = new JButton("Indietro");
		Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Indietro.setBounds(143, 498, 190, 80);
		contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycEle.ToPrecedente(MieiElementi.this, Precedente);
			}
		});

		JButton CreaElemento = new JButton("CreaElemento");
		CreaElemento.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		CreaElemento.setBounds(574, 498, 190, 80);
		contentPane.add(CreaElemento);
        CreaElemento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycEle.CreaElemento(MieiElementi.this);
			}
		});

		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(143, 103, 624, 370);
		contentPane.add(Scorrimento);

        PannelloBrani = new JPanel();
		Scorrimento.setViewportView(PannelloBrani);

		JLabel Titolo = new JLabel("I Miei Brani:");
		Titolo.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		Titolo.setBounds(143, 55, 621, 36);
		Titolo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Titolo);

	}
	public void MostraElementi(List <Brano> Brani) {
	    PannelloBrani.removeAll();

	    PannelloBrani.setLayout(new BoxLayout(PannelloBrani, BoxLayout.Y_AXIS));

	    for (Brano brano : Brani) {

	        JPanel riga = new JPanel(new BorderLayout());
	        riga.setMaximumSize(new Dimension(PannelloBrani.getWidth(), 50));

	        JButton titolo = new JButton(brano.getTitolo());
	        titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			titolo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MycEle.VisualizzaElemento(MieiElementi.this, brano);
				}
			});

	        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	        JButton Modifica = new JButton("Modifica");
	        Modifica.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

	        JButton Elimina = new JButton("Elimina");
	        Elimina.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

	        pulsanti.add(Modifica);
	        Modifica.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MycEle.ModificaElemento(MieiElementi.this, brano);
				}
			});

	        pulsanti.add(Elimina);
	        Elimina.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MycEle.EliminaElemento(brano, MieiElementi.this);
				}
			});

	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(pulsanti, BorderLayout.EAST);

	        PannelloBrani.add(riga);
	    }

	    PannelloBrani.revalidate();
	    PannelloBrani.repaint();
	}

}
