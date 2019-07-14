package com.quad.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;

public class Folder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// folder name
	private String name;
	private String parentsName;
	private JButton button;

	// list of sub folders
	private ArrayList<Folder> subFolders;
	private Folder parentFolder;

	public Folder(String name) {
		this.name = name;
		this.parentsName = "root";
		subFolders = new ArrayList<Folder>();
	}

	public Folder(String name, JButton button) {
		this.name = name;
		this.parentsName = "root";
		subFolders = new ArrayList<Folder>();
		this.button = button;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParentsName(String name) {
		this.parentsName = name;
	}

	public String getParentsName() {
		return parentsName;
	}

	public void createSubFolder(String name) {
		Folder f = new Folder(name);
		f.setParentsName(this.getName());
		subFolders.add(f);
	}

	public void addToSubFolder(Folder f) {
		f.setParentsName(this.getName());
		subFolders.add(f);
	}

	public String toString() {
		String s = "";
		for (Folder f : subFolders) {
			s += f.getName() + " ";
		}
		return s;
	}

	public ArrayList<Folder> getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(ArrayList<Folder> subFolders) {
		this.subFolders = subFolders;
	}

	public Folder getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

}
