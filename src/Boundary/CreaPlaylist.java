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
import javax.swing.JTextArea;

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
		Descrizione.setBounds(186, 253, 167, 25);
		contentPane.add(Descrizione);
		
		TitoloTF = new JTextField();
		TitoloTF.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		TitoloTF.setBounds(365, 172, 276, 25);
		contentPane.add(TitoloTF);
		TitoloTF.setColumns(10);
		
		DescrizioneTF = new JTextField();
		DescrizioneTF.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		DescrizioneTF.setBounds(365, 253, 276, 25);
		contentPane.add(DescrizioneTF);
		DescrizioneTF.setColumns(10);
		
		JTextArea DescrizioneTipo = new JTextArea();
		DescrizioneTipo.setText("Una Playlist Pubblica è visibile da tutti gli utenti registrati.\nSoltanto il creatore della Playlist può modificarla o eliminarla.");
		DescrizioneTipo.setBounds(365, 336, 276, 80);
		DescrizioneTipo.setEditable(false);
		DescrizioneTipo.setLineWrap(true);
		DescrizioneTipo.setWrapStyleWord(true);
		contentPane.add(DescrizioneTipo);
		
		JComboBox cmboxTipoPlaylist = new JComboBox();
		cmboxTipoPlaylist.setModel(new DefaultComboBoxModel(new String[] {"Pubblica", "Privata", "Condivisa"}));
		cmboxTipoPlaylist.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		cmboxTipoPlaylist.setBounds(186, 336, 150, 70);
		cmboxTipoPlaylist.setToolTipText("");
		contentPane.add(cmboxTipoPlaylist);
	       cmboxTipoPlaylist.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int Tipo=cmboxTipoPlaylist.getSelectedIndex();
					if(Tipo==0) {
						DescrizioneTipo.setText("Una Playlist Pubblica è visibile da tutti gli utenti registrati.\nSoltanto il creatore della Playlist può modificarla o eliminarla.");
					}else if(Tipo==1) {
						DescrizioneTipo.setText("Una Playlist Privata è visibile soltanto dal creatore della Playlist.");
					}else {
						DescrizioneTipo.setText("Una Playlist Condivisa può essere vista o modificata dagli utenti scelti dal creatore della Playlist.\nSoltanto il creatore può eliminare la Playlist o modificare la lista di collaboratori.");
					}
				}
				});
		
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
			}
			});

	}
}
