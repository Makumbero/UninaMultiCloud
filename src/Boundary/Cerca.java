package Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ControllerCerca;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cerca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textRicerca;
	ControllerCerca cCerca;

	public Cerca(ControllerCerca cCerca) {
		this.cCerca=cCerca;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setBounds(179, 92, 84, 20);
		contentPane.add(btnCerca);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cCerca.ToPrecedente(Cerca.this, cCerca.getPrecedente());
			}
		});
		btnIndietro.setBounds(18, 233, 84, 20);
		contentPane.add(btnIndietro);
		
		textRicerca = new JTextField();
		textRicerca.setBounds(112, 62, 230, 20);
		contentPane.add(textRicerca);
		textRicerca.setColumns(10);
		
		JComboBox cmboxTipoRicerca = new JComboBox();
		cmboxTipoRicerca.setModel(new DefaultComboBoxModel(new String[] {"Autore", "Brano", "Playlist"}));
		cmboxTipoRicerca.setToolTipText("Autore\r\nTitolo\r\nPlaylist\r\n");
		cmboxTipoRicerca.setBounds(28, 62, 74, 19);
		contentPane.add(cmboxTipoRicerca);

	}
}
