package com.project.hrms.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.hrms.main.UserDao;
import com.project.hrms.vo.AnnualSubVo;
import com.project.hrms.vo.AnnualVo;

public class AnnualSubDao {

	public static ArrayList<AnnualSubVo> list;
	
	static {
		
		list = new ArrayList<AnnualSubVo>();
		
	}

	public static void load() {
		
		try {
			
			String path = "data\\AnnualLeaveUsage.txt";
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;

			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				list.add(new AnnualSubVo(temp[0], temp[1], temp[2], Integer.parseInt(temp[3]), temp[4]));
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static void save() {
		
		try {
			
			String path = "data\\AnnualLeaveUsage.txt";
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (AnnualSubVo a : list) {
				
				writer.write(String.format("%s,%s,%s,%d,%s\n", a.getId(), a.getStartAnnual(), a.getEndAnnal(), a.getDateAnnual(), a.getReason()));
			
			}

			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static int annualSubApply(String startAnnual, String endAnnual, String reason) {
		
		int result = 0;
		
		String[] stAn = startAnnual.split("-");
		String[] enAn = endAnnual.split("-");
		
		Calendar stCal = Calendar.getInstance();
		Calendar enCal = Calendar.getInstance();
		
		stCal.set(Integer.parseInt(stAn[0]), Integer.parseInt(stAn[1]) - 1, Integer.parseInt(stAn[2]));
		enCal.set(Integer.parseInt(enAn[0]), Integer.parseInt(enAn[1]) - 1, Integer.parseInt(enAn[2]));
		
		int anDate = (int)((enCal.getTimeInMillis() / 1000 / 60 / 60 / 24) - (stCal.getTimeInMillis() / 1000 / 60 / 60 / 24) + 1);
		
		int count = 0;
		
		for (int i = 0; i < anDate; i++) {
			
			stCal.add(Calendar.DAY_OF_WEEK, i);
			
			if (stCal.get(Calendar.DAY_OF_WEEK) == 1 || stCal.get(Calendar.DAY_OF_WEEK) == 7) {
				
				count++;
				
			}
			
			stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1])-1, Integer.parseInt(stAn[2]));
			
		}
		
		int totalDate = anDate - count;
		
		if (totalDate == 0) {
			
			result = 0;
			
		} else {
			
			for (AnnualVo a : AnnualDao.list) {
				
				if (a.getId().equals(UserDao.auth.getId())) {
					
					if (a.getLeftoverAnnual() - totalDate < 0) {
						
						result = -1;
						
					} else {
						
						a.setLeftoverAnnual(a.getLeftoverAnnual() - totalDate);
						a.setUsedAnnal(a.getUsedAnnal() + totalDate);
						
						list.add(new AnnualSubVo(UserDao.auth.getId(), startAnnual, endAnnual, totalDate, reason));
						
						result = 1;
						
					}
					
				}
				
			}
			
		}

		return result;

	}

	public static boolean annualCheck(String startAnnual, String endAnnual) {
		
		boolean result = true;

		String regex = "^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(startAnnual);
		
		if (!m.find()) {
			
			result = false;
			
		}
		
		m = p.matcher(endAnnual);
		
		if (!m.find()) {
			
			result = false;
			
		}
		
		if (result) {
			
			String[] stAn = startAnnual.split("-");
			String[] enAn = endAnnual.split("-");
			
			Calendar stCal = Calendar.getInstance();
			Calendar enCal = Calendar.getInstance();
			
			stCal.set(Integer.parseInt(stAn[0]) , Integer.parseInt(stAn[1]) - 1, Integer.parseInt(stAn[2]));
			enCal.set(Integer.parseInt(enAn[0]) , Integer.parseInt(enAn[1]) - 1, Integer.parseInt(enAn[2]));
			
			String reStCal=String.format("%tF", stCal);
			String reEnCal=String.format("%tF", enCal);
			
			if ((!reStCal.equals(startAnnual)) || (!reEnCal.equals(endAnnual))) {
				
				result = false;
				
			}
			
			long totalDate = (enCal.getTimeInMillis() / 1000 / 60 / 60 / 24) - (stCal.getTimeInMillis() / 1000 / 60 / 60 / 24);
			
			if (totalDate < 0) {
				
				result = false;
				
			}
			
		}
		
		return result;

	}

	public static String checkAnnualApply() {
		
		String result = "";
		
		for (AnnualSubVo a : list) {
			
			if (a.getId().equals(UserDao.auth.getId())) {
				
				result += String.format("%10s\t%10s\t%5s\t\t%4s\n", a.getStartAnnual(), a.getEndAnnal(), a.getDateAnnual(), a.getReason());
			
			}
			
		}
		
		return result;
		
	}

}
