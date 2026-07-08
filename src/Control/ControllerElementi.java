package Control;

import java.sql.Connection;

import javax.swing.JFrame;

import Boundary.*;
import Dao.*;
import Entity.*;

public class ControllerElementi {
	Utente MyUtente;
	Connection conn;
	Home MyHome;
	MieiElementi MyMieiElementi;
	ControllerLogin cLog;
	JDBCBranoDao MyBranoDao;
	VisualizzaElemento MyVisualizzaElemento;
	ModificaElemento MyModificaElemento;
	public ControllerElementi(Connection conn1, Utente u, Home h, ControllerLogin cLog) {
		conn=conn1;
		MyUtente=u;
		MyHome=h;
		this.cLog=cLog;
		MyMieiElementi = new MieiElementi(this, MyHome);
        MyBranoDao = new JDBCBranoDao(conn, cLog);
	}
	public void HomeToMieiElementi(Utente u) {
		MyHome.setVisible(false);
		MyMieiElementi.setVisible(true);
		MyMieiElementi.MostraElementi(MyBranoDao.CercaElementiPerEmail(MyUtente.getEmail()));
	}
	public Utente getMyUtente() {
		return MyUtente;
	}
	public void setMyUtente(Utente myUtente) {
		MyUtente = myUtente;
	}
	public void ToPrecedente(JFrame Attuale, JFrame Precedente) {
		Attuale.setVisible(false);
		Precedente.setVisible(true);
	}
	public void VisualizzaElemento(JFrame Precedente, Brano b) {
		Precedente.setVisible(false);
		MyVisualizzaElemento=new VisualizzaElemento(Precedente, this);
		MyVisualizzaElemento.setVisible(true);
		MyVisualizzaElemento.MostraElemento(b);
	}
	
	public void ModificaElemento(JFrame Precedente, Brano b) {
		Precedente.setVisible(false);
		MyModificaElemento=new ModificaElemento(Precedente, this, b);
		MyModificaElemento.setVisible(true);
	}
	
}
