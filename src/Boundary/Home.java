package Boundary;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerCerca;
import Control.ControllerElementi;
import Control.ControllerLogin;
import Control.ControllerPlaylist;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ControllerLogin MycLog;
	ControllerElementi MycEle;
	ControllerPlaylist MycPl;
	ControllerCerca MycCerca;
	public Home(ControllerLogin cLog) {
		this.MycLog=cLog;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton IMieiBrani = new JButton("I miei brani");
		IMieiBrani.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		IMieiBrani.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycEle.HomeToMieiElementi();
			}
		});
		IMieiBrani.setBounds(87, 178, 190, 80);
		contentPane.add(IMieiBrani);

		JButton Cerca = new JButton("Cerca");
		Cerca.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Cerca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycCerca.HomeToCerca();
			}
		});
		Cerca.setBounds(87, 88, 190, 80);
		contentPane.add(Cerca);

		JButton Profilo = new JButton("Profilo");
		Profilo.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Profilo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycLog.HomeToProfilo();
			}
		});
		Profilo.setBounds(87, 366, 190, 80);
		contentPane.add(Profilo);

		JButton Raccolta = new JButton("Raccolta");
		Raccolta.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Raccolta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycPl.HomeToRaccolta();
			}
		});
		Raccolta.setBounds(87, 268, 190, 80);
		contentPane.add(Raccolta);

		JButton LogOut = new JButton("LogOut");
		LogOut.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		LogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MycLog.Logout();
			}
		});
		LogOut.setBounds(87, 456, 190, 80);
		contentPane.add(LogOut);



		JLabel Nuvola = new JLabel("cia");
		ImageIcon iconaOriginale = new ImageIcon(Home.class.getResource("/Images/NUVOLOZZA-unsplash.png"));
		Image immagineScalata = iconaOriginale.getImage().getScaledInstance(559, 374, Image.SCALE_SMOOTH);
		Nuvola.setIcon(new ImageIcon(immagineScalata));
		Nuvola.setBounds(312, 118, 559, 374);
		contentPane.add(Nuvola);

	}
	public void setControllerElementi(ControllerElementi cEle){
		MycEle=cEle;
	}
	public void setControllerPlaylist(ControllerPlaylist cPl) {
		MycPl=cPl;
	}
	public void setControllerCerca(ControllerCerca cCerca){
		MycCerca=cCerca;
	}
}
