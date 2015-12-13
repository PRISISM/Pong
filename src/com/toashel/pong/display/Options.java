package com.toashel.pong.display;

import javax.swing.JFrame;
import javax.swing.JSpinner;

public class Options extends JFrame {

	public Options(String title) {
		super(title);
		JSpinner spin = new JSpinner();
		add(spin);
		
	}

}
