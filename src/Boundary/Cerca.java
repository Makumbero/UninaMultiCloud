package Boundary;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerCerca;

public class Cerca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textRicerca;
	ControllerCerca cCerca;
	JFrame Precedente;

	public Cerca(ControllerCerca cCerca,JFrame Precedente) {
		this.cCerca=cCerca;
		this.Precedente=Precedente;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);



		JButton btnIndietro = new JButton("Indietro");
        btnIndietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        btnIndietro.setBounds(143, 498, 190, 80);
		btnIndietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCerca.ToPrecedente(Cerca.this,Precedente);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnIndietro);

		textRicerca = new JTextField();
		textRicerca.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textRicerca.setBounds(315, 253, 424, 35);
		contentPane.add(textRicerca);
		textRicerca.setColumns(10);

		JComboBox cmboxTipoRicerca = new JComboBox();
		cmboxTipoRicerca.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		cmboxTipoRicerca.setBounds(174, 253, 117, 34);
		cmboxTipoRicerca.setModel(new DefaultComboBoxModel(new String[] {"Autore", "Brano", "Playlist"}));
		cmboxTipoRicerca.setToolTipText("Autore\r\nTitolo\r\nPlaylist\r\n");
		contentPane.add(cmboxTipoRicerca);

		JButton btnCerca = new JButton("Cerca");
        btnCerca.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        btnCerca.setBounds(579, 498, 190, 80);
		btnCerca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCerca.CercaToRisultatiRicerca(cmboxTipoRicerca.getSelectedIndex(), textRicerca.getText(), Cerca.this);
			}
		});
		contentPane.add(btnCerca);

	}
}
