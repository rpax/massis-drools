package com.massisframework.massis.dasi.logger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.massisframework.massis.dasi.agentViewer.VisorDialogo;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

public class LoggerWindow extends JFrame {

	private JPanel contentPane;
	private JList listaAgentes;
	private JTabbedPane tabbedPane;
	private JTextPane editorPane;
	private HashMap<String,StyledDocument> logs;
	private JScrollPane scroll;
	private JFrame a; 
	/**
	 * Create the frame.
	 */
	public LoggerWindow() {
		a = this;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		logs = new HashMap<String, StyledDocument>();
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Agentes");
		lblNewLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.add(lblNewLabel);
		
		listaAgentes = new JList<RobotAgent>();
		panel_1.add(listaAgentes);
		listaAgentes.setModel(new DefaultListModel());
		
		listaAgentes.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		            int index = listaAgentes.locationToIndex(evt.getPoint());
		            VisorDialogo v = new VisorDialogo(a,(RobotAgent)listaAgentes.getModel().getElementAt(index));
		            v.setVisible(true);
		        } 		    
		    }
		});
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		editorPane = new JTextPane();
		editorPane.setEditable(false);

		StyledDocument text = (StyledDocument) editorPane.getDocument();
		
		Style content = text.addStyle("contentStyle", null);
		StyleConstants.setBold(content, true);

		Style title = text.addStyle("titleStyle", null);
		StyleConstants.setFontSize(title, 40);

		try {
			text.insertString(text.getLength(), "Logger\n\n", title);
			text.insertString(text.getLength(), "Este es el panel de visionado de Logs asociados a la actividad de los agentes en la simulación.\n\n", content);			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scroll = new JScrollPane(editorPane);
		tabbedPane.addTab("Información", null, scroll, null);
	}
	public void appendInfo(String id, String texto, String tipo)
	{

		StyledDocument t = logs.get(id);
		Style content = t.getStyle("contentStyle");
		if (tipo.equals("titulo"))
			StyleConstants.setForeground(content, Color.BLUE);
		else if(tipo.equals("info"))
			StyleConstants.setForeground(content, Color.BLACK);
		else if(tipo.equals("envio"))
			StyleConstants.setForeground(content, Color.ORANGE);
		else
			StyleConstants.setForeground(content, Color.RED);

		try {
			t.insertString(t.getLength(), texto+"\n", content);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void addTab(String id)
	{
		JTextPane visorLog = new JTextPane();
		JScrollPane jsp = new JScrollPane(visorLog);
		visorLog.setEditable(false);

		StyledDocument visor = (StyledDocument) visorLog.getDocument();
		
		Style title = visor.addStyle("titleStyle", null);
		StyleConstants.setFontSize(title, 18);
		visor.addStyle("contentStyle", null);
		try {
			visor.insertString(visor.getLength(), "Log "+id+"\n\n", title);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		logs.put(id, visor);
		tabbedPane.addTab(id, jsp);
	}
	
	public JList<RobotAgent> getListaAgentes(){
			return listaAgentes;	
	}
	
}
