package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ControllerCerca;
import Control.ControllerElementi;
import Control.ControllerLogin;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ControllerLogin cLog;
	ControllerElementi cEle;
	ControllerCerca cCerca;
	public Home(ControllerLogin cLog) {
		this.cLog=cLog;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("I miei brani");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cEle.HomeToMieiElementi(cEle.getMyUtente());
			}
		});
		btnNewButton.setBounds(39, 113, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cerca");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cCerca.HomeToCerca();
			}
		});
		btnNewButton_1.setBounds(119, 45, 84, 20);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Profilo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLog.HomeToProfilo();
			}
		});
		btnNewButton_2.setBounds(39, 198, 84, 20);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Raccolta");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(203, 113, 84, 20);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("LogOut");
		btnNewButton_4.setBounds(203, 198, 84, 20);
		contentPane.add(btnNewButton_4);

	}
	public void setControllerElementi(ControllerElementi cEle){
		this.cEle=cEle;
	}
	public void setControllerCerca(ControllerCerca cCerca){
		this.cCerca=cCerca;
	}
}
