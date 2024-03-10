package com.project.hrms.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import com.project.hrms.vo.AttendanceVo;

public class AttendanceDao {
	
	public static ArrayList<AttendanceVo> list;

	static {
		
		list = new ArrayList<AttendanceVo>();
		
	}

	public static boolean load(String year, String month) {
		
		try {
			
			list.clear();

			String path = String.format("data\\attendance\\%s\\%s.txt", year, month);
			
	        File file = new File(path);

	        if (file.exists()) {
	        	
				BufferedReader reader = new BufferedReader(new FileReader(path));
				
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					
					String[] temp = line.split(",");
					
					AttendanceVo a = new AttendanceVo(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9], temp[10]);
					
					list.add(a);
					
				}
				
				reader.close();
				
				return true;
				
	        }
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return false;
		
	}
	
	public static boolean modifyTime(String year, String month, String date, String time, String id, String input) {

		int inputYear = Integer.parseInt(year);
		int inputMonth = Integer.parseInt(month) - 1;
		int inputDate = Integer.parseInt(date);
		int inputHour = Integer.parseInt(time.split(":")[0]);
		int inputMinute = Integer.parseInt(time.split(":")[1]);
		
        Calendar modified = Calendar.getInstance();
        
        modified.set(inputYear, inputMonth, inputDate, inputHour, inputMinute);
        
        long modifiedTick = modified.getTimeInMillis();
        
        boolean isModified = false;
			
		switch (input) {
			
			case "1":
					
				for (AttendanceVo  a : list) {
				
					if (a.getDate().equals(date) && a.getId().equals(id)) {
						
						int endHour = Integer.parseInt(a.getEndTime().split(":")[0]);
						int endMinute = Integer.parseInt(a.getEndTime().split(":")[1]);
						
						Calendar original = Calendar.getInstance();
						
						original.set(inputYear, inputMonth, inputDate, endHour, endMinute);

						long originalTick = original.getTimeInMillis();
				        
				        if (modifiedTick < originalTick) {
				        	
				        	a.setStartTime(String.format("%d:%02d", inputHour, inputMinute));
				        	
				        	long calculatedMinute = (originalTick - modifiedTick) / 1000 / 60;
				        	long calculatedHour = calculatedMinute / 60;
				        	calculatedMinute %= 60;
				        	
				        	a.setWorkHours(String.format("%d:%02d", calculatedHour, calculatedMinute));

				        	if ((inputHour < 9) || (inputHour == 9 && inputMinute == 0)) {
				        		
				        		a.setIsLate("0");
				        		
				        	} else {
				        		
				        		a.setIsLate("1");
				        		
				        	}
				        	
				        	isModified = true;
				        	
				        }
						
					}
					
				}
				
				break;
				
			case "2":
					
				for (AttendanceVo  a : AttendanceDao.list) {
				
					if (a.getDate().equals(date) && a.getId().equals(id)) {

						int startHour = Integer.parseInt(a.getStartTime().split(":")[0]);
						int startMinute = Integer.parseInt(a.getStartTime().split(":")[1]);

						Calendar original = Calendar.getInstance();
						
						original.set(inputYear, inputMonth, inputDate, startHour, startMinute);

				        long originalTick = original.getTimeInMillis();
				        
				        if (modifiedTick > originalTick) {
				        	
				        	a.setEndTime(String.format("%d:%02d", inputHour, inputMinute));

				        	long calculatedMinute = (modifiedTick - originalTick) / 1000 / 60;
				        	long calculatedHour = calculatedMinute / 60;
				        	calculatedMinute %= 60;

				        	a.setWorkHours(String.format("%d:%02d", calculatedHour, calculatedMinute));
				        	
					        if (inputHour < 18) {
					        		
					        	a.setIsEarlyLeave("1");
					        	a.setOvertimeHours("0");
					        	a.setLateNightOvertimeHours("0");
					        		
					        } else if (inputHour < 22) {
					        		
					        	a.setIsEarlyLeave("0");
					        	a.setOvertimeHours(String.format("%d:%02d", (inputHour - 18), inputMinute));
					        	a.setLateNightOvertimeHours("0");
					        		
					        } else if (inputHour < 24) {
					        		
					        	a.setIsEarlyLeave("0");
					        	a.setOvertimeHours(String.format("%d:%02d", (inputHour - 18), 0));
					        	a.setLateNightOvertimeHours(String.format("%d:%02d", (inputHour - 22), inputMinute));
					        		
					        }
					        
					        if (modified.get(Calendar.DAY_OF_WEEK) == 1 || modified.get(Calendar.DAY_OF_WEEK) == 7) {
		                          
								a.setOvertimeHours("0");
								a.setLateNightOvertimeHours("0");
	                          
								if (inputHour > 18) {
	                             
									a.setHolidayOvertimeHours(String.format("%d:%02d", (inputHour - 18), inputMinute));
	                             
								}
	                          
							} else {
	                          
								a.setHolidayOvertimeHours("0");
	                          
							}
					        
					        isModified = true;
				        	
				        }

					}
				}
				
		}
		
		return isModified;
		
	}

	public static void save(String year, String month) {
		
		try {
					
			String path = String.format("data\\attendance\\%s\\%s.txt", year, month);
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (AttendanceVo a : AttendanceDao.list) {
				
				writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
										a.getId(), a.getDate(), a.getStartTime(), a.getEndTime(), a.getWorkHours(), a.getIsLate(), 
										a.getIsEarlyLeave(), a.getIsOnLeave(), a.getOvertimeHours(), a.getHolidayOvertimeHours(),
										a.getLateNightOvertimeHours()));
						
			}
					
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

}
