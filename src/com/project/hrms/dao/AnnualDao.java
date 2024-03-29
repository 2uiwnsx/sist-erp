package com.project.hrms.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.hrms.main.UserDao;
import com.project.hrms.vo.AnnualVo;

public class AnnualDao {

	public static ArrayList<AnnualVo> list;
	
	static {
		
		list = new ArrayList<AnnualVo>();
		
	}
	
	public static void load() {
		
		try {
			
			String path = "data\\annual.txt";
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;

			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				list.add(new AnnualVo(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static void save() {
		
		try {
			
			String path = "data\\annual.txt";
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (AnnualVo a : list) {
				
				writer.write(String.format("%s,%d,%d,%d\n", a.getId(), a.getAllotmentAnnual(), a.getUsedAnnal(), a.getLeftoverAnnual()));
				
			}

			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static AnnualVo checkAnnualApply() {
		
		AnnualVo a = new AnnualVo();
		
		String id = UserDao.auth.getId();
		
		for (AnnualVo an : list) {
			
			if (a.getId().equals(id)) {
				
				a = an;
				
				break;
			}
			
		}
		
		return a;
		
	}

}
