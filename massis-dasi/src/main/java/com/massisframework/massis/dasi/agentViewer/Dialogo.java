package com.massisframework.massis.dasi.agentViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

public class Dialogo extends JDialog {

	private final VisorDialogo contentPanel;

	/**
	 * Create the dialog.
	 */
	public Dialogo(JFrame fr, VisorDialogo v) {
		super(fr,false);
		this.setTitle("Informacion del agente");
		contentPanel = v;
		setBounds(100, 100, 450, 400);
	    setPreferredSize(new Dimension(400,400));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
	}

}
