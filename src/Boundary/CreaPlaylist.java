package Boundary;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.ControllerPlaylist;

public class CreaPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ControllerPlaylist cPlay;
	JFrame Precedente;
	private JTextField TitoloTF;
	private JTextField DescrizioneTF;


	public CreaPlaylist(ControllerPlaylist cPlay, JFrame Precedente) {
		this.cPlay=cPlay;
		this.Precedente=Precedente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titolo = new JLabel("Titolo:");
		Titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Titolo.setBounds(241, 173, 112, 25);
		contentPane.add(Titolo);
		
		JLabel Descrizione = new JLabel("Descrizione:");
		Descrizione.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Descrizione.setBounds(241, 254, 112, 25);
		contentPane.add(Descrizione);
		
		TitoloTF = new JTextField();
		TitoloTF.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		TitoloTF.setBounds(363, 172, 276, 25);
		contentPane.add(TitoloTF);
		TitoloTF.setColumns(10);
		
		DescrizioneTF = new JTextField();
		DescrizioneTF.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		DescrizioneTF.setBounds(363, 253, 276, 25);
		contentPane.add(DescrizioneTF);
		DescrizioneTF.setColumns(10);
		
		JComboBox cmboxTipoPlaylist = new JComboBox();
		cmboxTipoPlaylist.setModel(new DefaultComboBoxModel(new String[] {"Pubblica", "Privata", "Condivisa"}));
		cmboxTipoPlaylist.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		cmboxTipoPlaylist.setBounds(394, 358, 117, 34);
		cmboxTipoPlaylist.setToolTipText("");
		contentPane.add(cmboxTipoPlaylist);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Indietro.setBounds(143, 498, 190, 80);
		contentPane.add(Indietro);
        Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cPlay.ToPrecedente(CreaPlaylist.this, Precedente);
			}
			});
		
		JButton Crea = new JButton("Crea");
		Crea.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Crea.setBounds(574, 498, 190, 80);
		contentPane.add(Crea);
        Crea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cPlay.CreaPlaylist(cmboxTipoPlaylist.getSelectedIndex(), TitoloTF.getText(), DescrizioneTF.getText());
				JOptionPane.showMessageDialog(null, "La Playlist è ora disponibile nella raccolta");
			}
			});

	}

}
