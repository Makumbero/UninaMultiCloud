package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton IMieiBrani = new JButton("I miei brani");
		IMieiBrani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycEle.HomeToMieiElementi();
			}
		});
		IMieiBrani.setBounds(39, 113, 85, 21);
		contentPane.add(IMieiBrani);
		
		JButton Cerca = new JButton("Cerca");
		Cerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycCerca.HomeToCerca();
			}
		});
		Cerca.setBounds(119, 45, 84, 20);
		contentPane.add(Cerca);
		
		JButton Profilo = new JButton("Profilo");
		Profilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycLog.HomeToProfilo();
			}
		});
		Profilo.setBounds(39, 198, 84, 20);
		contentPane.add(Profilo);
		
		JButton Raccolta = new JButton("Raccolta");
		Raccolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.HomeToRaccolta();
			}
		});
		Raccolta.setBounds(203, 113, 84, 20);
		contentPane.add(Raccolta);
		
		JButton LogOut = new JButton("LogOut");
		LogOut.setBounds(203, 198, 84, 20);
		contentPane.add(LogOut);

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
