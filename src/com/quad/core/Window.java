package com.quad.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import com.quad.entity.Folder;
import com.quad.system.Output;
import com.quad.system.Reader;
import com.quad.system.Writer;

public class Window {

	/*
	 * This Class creates the window for our application It creates a frame that is
	 * the outside of the window It creates a panel that is the inside of the window
	 * that controls all of the content
	 */

	// Frame for the widow
	private JFrame frame;
	private JPanel panel;
	private JPanel textPanel;
	private JPanel flowPanel;
	private JMenuBar menuBar;
	private JMenu file;
	private String txt;

	// file items
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem export;
	JFileChooser jfc;

	private Stack<Folder> folders;

	// button
	private Folder root;
	private Image folderImage;

	public Window(int width, int height, String title) {
		// create window
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		Dimension size = new Dimension(width, height);
		panel.setPreferredSize(size);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		// load images
		try {
			folderImage = ImageIO.read(getClass().getResourceAsStream("/folder.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create root folder
		root = new Folder("Original Folder");
		root.setParentFolder(root);
		txt = "Original Folder";

		folders = new Stack<Folder>();
		folders.push(root);

		// create menu bar
		menuBar = new JMenuBar();
		file = new JMenu("File");
		newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleNew();
			}
		});
		openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleImport();
			}
		});
		saveItem = new JMenuItem("Save Template");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSave();
			}
		});
		export = new JMenuItem("Export Files");
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleExport();
			}
		});
		file.add(newItem);
		file.add(openItem);
		file.add(saveItem);
		file.add(export);
		menuBar.add(file);
		frame.setJMenuBar(menuBar);
		frame.pack();

		createTitlePanel();
		createFlowPanel();
		createFolderView();
	}

	private void createFlowPanel() {
		flowPanel = new JPanel(new GridLayout(3, 3));
		flowPanel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(flowPanel, BorderLayout.CENTER);
		frame.pack();
	}

	private void createTitlePanel() {
		textPanel = new JPanel(new FlowLayout());
		textPanel.setBorder(BorderFactory.createEtchedBorder());
		JLabel text = new JLabel(txt);
		textPanel.add(text, new BorderLayout());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (folders.size() <= 1)
					return;
				folders.pop();
				display(folders.peek());
			}
		});
		textPanel.add(back, BorderLayout.EAST);
		panel.add(textPanel, BorderLayout.NORTH);
		frame.pack();
	}

	private void createFolderView() {
		JPanel folderPanel = new JPanel(new BorderLayout());
		folderPanel.setBorder(BorderFactory.createEtchedBorder());
		JButton button = new JButton("Create New Folder");
		button.setIcon(new ImageIcon("res/check.png"));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Folder folder = new Folder(JOptionPane.showInputDialog("Name of Folder?"));
				txt = folder.getName();
				JButton buttn = new JButton(folder.getName());
				buttn.setIcon(new ImageIcon(folderImage));
				folder.setButton(buttn);
				folders.peek().addToSubFolder(folder);
				flowPanel.add(buttn, BorderLayout.CENTER);
				handleButton(buttn, folder);
				frame.pack();
			}
		});
		folderPanel.add(button, BorderLayout.SOUTH);
		panel.add(folderPanel, BorderLayout.SOUTH);
		frame.pack();
	}

	private void handleButton(JButton button, Folder f) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				folders.push(f);
				display(folders.peek());
			}
		});
	}

	private void handleSave() {
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			Writer w = new Writer(root);
			w.write(file.getAbsolutePath() +"/" + JOptionPane.showInputDialog("Name of File?") + ".tplt");
		}
	}

	private void display(Folder folder) {
		txt = folder.getName();
		flowPanel.removeAll();
		flowPanel.revalidate();
		flowPanel.repaint();
		textPanel.removeAll();
		createTitlePanel();
		textPanel.repaint();
		textPanel.revalidate();
		for (Folder fld : folder.getSubFolders()) {
			flowPanel.add(fld.getButton());
		}
		frame.pack();
	}

	private void handleNew() {
		for (int i = 0; i < root.getSubFolders().size(); i++) {
			root.getSubFolders().remove(i);
		}
		display(root);
		frame.pack();
	}

	private void handleImport() {
		Reader reader = new Reader();
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			Folder f = reader.read(file.getAbsolutePath());
			root.setSubFolders(f.getSubFolders());
			loadButtons();
			display(root);
		}
	}

	private void loadButtons() {
		loadButtonsRedcursive(root);
	}

	private void loadButtonsRedcursive(Folder f) {
		JButton buttn = new JButton(f.getName());
		buttn.setIcon(new ImageIcon(folderImage));
		buttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleButton(buttn, f);
			}
		});
		f.setButton(buttn);
		if (f.getSubFolders().isEmpty())
			return;
		for (Folder fld : f.getSubFolders()) {
			loadButtonsRedcursive(fld);
		}
	}

	private void handleExport() {
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			Output output = new Output(file.getAbsolutePath(), root);
			output.outputDirectory();
		}
	}

}
