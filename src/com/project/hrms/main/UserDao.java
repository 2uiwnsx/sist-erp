package com.project.hrms.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class UserDao {

	public static PersonVo auth;
	
	public static ArrayList<UserVo> list;
	
	static {
		
		list = new ArrayList<UserVo>();
		
	}
	
	public static void load() {
		
		try {
			
			String path = "data\\login.txt";
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;

			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				list.add(new UserVo(temp[0],temp[1],temp[2]));
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			
			System.out.println("at UserDao.load");
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void save() {
		
		try {
			
			String path = "data\\login.txt";
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (UserVo u : list) {

				writer.write(String.format("%s,%s,%s\n", u.getId() , u.getPw(), u.getLevel()));
				
			}

			writer.close();
			
		} catch (Exception e) {
			
			System.out.println("at UserDao.save");
			
			e.printStackTrace();
			
		}

	}
	
	public static String getLevel(String id, String pw) {
		
		String level = null;

		for (UserVo u : list) {
			
			if (u.getId().equals(id) && u.getPw().equals(pw)) {
				
				level = u.getLevel();
				
				for (PersonVo p : PersonDao.list) {
					
					if (p.getId().equals(id)) {
						
						auth = p;
						
						break;
						
					}
					
				}
				
				break;
				
			}
			
		}
		
		return level;
		
	}

}
