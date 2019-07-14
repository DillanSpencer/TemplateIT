package com.quad.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.quad.entity.Folder;

public class Writer {
	
	//root folder to write
	private Folder root;
	private FileOutputStream f;
	private ObjectOutputStream o;
	
	public Writer(Folder root) {
		// TODO Auto-generated constructor stub
		this.root = root;
	}
	
	public void write(String fileName) {
		try {
			 f = new FileOutputStream(new File(fileName));
			 try {
				o = new ObjectOutputStream(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Write objects to file
			o.writeObject(root);

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
