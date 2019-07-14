package com.quad.core;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class AppCore {

	// Main app - This is where the program is run from

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create new window object
		 Window window = new Window(640, 480, "Auto Folder");
		
	}

}
