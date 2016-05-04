package com.massisframework.massis.dasi.agentViewer;

import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.leader.LeaderRobot;
import com.massisframework.massis.dasi.apps.robots.subordinate.SubordinateRobot;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;
import javax.swing.BoxLayout;

public class VisorDialogo extends JPanel {

	private JScrollPane scroll;
	private Timer time; 
    private JTextPane editorPane;
    private RobotAgent agente;
    private Dialogo dia;
	public VisorDialogo(JFrame fr,RobotAgent a) {
        dia = new Dialogo(fr,this);
        dia.pack();
        
		time = new Timer();
        agente = a;
        editorPane = new JTextPane();

        scroll = new JScrollPane(editorPane);
        
        editorPane.setEditable(false);
        time = new Timer();
        time.schedule(new RepaintTask(),0, 3 * 1000);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(scroll);
        dia.setVisible(true);
	}
	class RepaintTask extends TimerTask {
		public void run() {
			StyledDocument text = (StyledDocument) editorPane.getDocument();

			try {
				text.remove(0, text.getLength());
				Style content = text.addStyle("contentStyle", null);
				StyleConstants.setBold(content, true);
				text.insertString(text.getLength(), "Identificador del agente: "+agente.getLowLevelAgent().getID()+"\n", content);
				text.insertString(text.getLength(), "Energia actual del agente: "+agente.getEnergy()+"\n", content);
				text.insertString(text.getLength(), "Localizacion actual del agente: "+agente.getLocation()+"\n", content);
				text.insertString(text.getLength(), "Clase java asociada a agente: "+agente.getClass().getName()+"\n", content);
				if(agente instanceof LeaderRobot)
				{
					text.insertString(text.getLength(), "Equipo al que pertenece el agente: "+agente.getTeamName()+"\n", content);
					LeaderRobot a = (LeaderRobot) agente;
					text.insertString(text.getLength(), "Agentes que ha reconocido como lideres:\n ",content);
					for(RobotAgent R : a.getTeamLeaders())
						text.insertString(text.getLength(), "Agente: "+R+"\n ",content);
					text.insertString(text.getLength(), "Agentes del equipo:\n",content);
					for(RobotAgent R : a.getTeamMembers())
						text.insertString(text.getLength(), "Agente: "+R+"\n ",content);
				}else if(agente instanceof SubordinateRobot){
					text.insertString(text.getLength(), "Equipo al que pertenece el agente: "+agente.getTeamName()+"\n", content);
					SubordinateRobot a = (SubordinateRobot) agente;
					text.insertString(text.getLength(), "Agente reconocido como lider: "+a.getLeader()+"\n ",content);
				}
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	  }
}
