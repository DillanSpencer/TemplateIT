package com.quad.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.quad.entity.Folder;

public class Reader {

	private Folder root;
	private FileInputStream fi;
	private ObjectInputStream oi;

	public Reader() {
		// TODO Auto-generated constructor stub
	}

	public Folder read(String fileName) {
		try {
			fi = new FileInputStream(new File(fileName));
			oi = new ObjectInputStream(fi);
			root = (Folder) oi.readObject();
			System.out.println(root.getSubFolders().get(0));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return root;
	}

}
