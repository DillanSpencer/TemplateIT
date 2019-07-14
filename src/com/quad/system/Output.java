package com.quad.system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.quad.entity.Folder;

public class Output {
	
	/*
	 * This class will take a directory and output the files
	 */
	
	private String directory;
	private Folder root;
	
	public Output(String dir, Folder root) {
		this.directory = dir;
		this.root = root;
	}
	
	public void outputDirectory() {
		output(root, directory);
	}
	
	public void output(Folder folder, String dir) {
		String temp = dir;
		
		if(!folder.getParentsName().equals("root")) temp += ("\\" + folder.getParentsName()); 
		
		try {
			Files.createDirectories(Paths.get(temp + "\\" + folder.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//base case
		if(folder.getSubFolders().size() <= 0) return;
	
		for(Folder f : folder.getSubFolders()) {
			output(f, temp);
		}
	}

}
