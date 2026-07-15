package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ControllerLogin MycLog;
	ControllerElementi MycEle;
	ControllerPlaylist MycPl;
	ControllerCerca MycCerca;
	public Home(ControllerLogin cLog) {
		this.MycLog=cLog;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			public void actionPerformed(ActionEvent e) {
				MycEle.HomeToMieiElementi();
			}
		});
		IMieiBrani.setBounds(87, 142, 190, 80);
		contentPane.add(IMieiBrani);
		
		JButton Cerca = new JButton("Cerca");
		Cerca.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Cerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycCerca.HomeToCerca();
			}
		});
		Cerca.setBounds(362, 297, 190, 80);
		contentPane.add(Cerca);
		
		JButton Profilo = new JButton("Profilo");
		Profilo.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Profilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycLog.HomeToProfilo();
			}
		});
		Profilo.setBounds(87, 453, 190, 80);
		contentPane.add(Profilo);
		
		JButton Raccolta = new JButton("Raccolta");
		Raccolta.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Raccolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.HomeToRaccolta();
			}
		});
		Raccolta.setBounds(627, 142, 190, 80);
		contentPane.add(Raccolta);
		
		JButton LogOut = new JButton("LogOut");
		LogOut.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		LogOut.setBounds(627, 453, 190, 80);
		contentPane.add(LogOut);
		
		JLabel Titolo = new JLabel("Home");
		Titolo.setFont(new Font("Lucida Grande", Font.BOLD, 45));
		Titolo.setBounds(87, 60, 730, 37);
		Titolo.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(Titolo);

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
