package com.project.hrms.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.hrms.main.PersonDao;
import com.project.hrms.main.PersonVo;
import com.project.hrms.vo.AttendanceVo;
import com.project.hrms.vo.DeducationVo;
import com.project.hrms.vo.PayVo;
import com.project.hrms.vo.SalaryStepModifyVo;

public class PayDao {
	
	public static ArrayList<PayVo> payList;
	
	static {
		
		PayDao.payList = new ArrayList<PayVo>();
		
	}

	public static boolean load(String year, String month) {
		
		PayDao.payList.clear();
		
		String path = String.format("data\\PayStub%s.txt", month);

		File file = new File(path);
		
		if (!file.exists()) {
			
			return false;
			
		}
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				PayVo pay = new PayVo(temp[0], temp[1], temp[2], temp[3], Integer.parseInt(temp[4]), Integer.parseInt(temp[5]),
									Integer.parseInt(temp[6]), Integer.parseInt(temp[7]), Integer.parseInt(temp[8]), Integer.parseInt(temp[9]),
									Integer.parseInt(temp[10]), Integer.parseInt(temp[11]), Integer.parseInt(temp[12]), Integer.parseInt(temp[13]),
									Integer.parseInt(temp[14]), Integer.parseInt(temp[15]), Integer.parseInt(temp[16]));
				
				PayDao.payList.add(pay);
				
			}
			
			reader.close();
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public static void listShow(String month, String id) {
		
		for (PayVo pay : PayDao.payList) {
			
			if (pay.getId().equals(id)) {

				System.out.println(pay.getPaymentDate());
				System.out.println("================================================================================================");
				System.out.printf("[%s님의 %s월 명세서]\n", pay.getName(), month);
				System.out.println("※ 단위: 원");
				System.out.println("[기본급]\t[직책수당]\t[연장근로수당]\t[휴일근로수당]");
				System.out.printf("%,d\t\t%,d\t\t%,d\t\t%,d\n", pay.getBasicPay(), pay.getPositionPay(), pay.getOvertimePay(), pay.getHolidayPay());
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.printf("[국민연금]\t[건강보험]\t[고용보험]\t[장기요양보험]\t[소득세]\t[지방소득세]\n");
				System.out.printf("%,d\t\t%,d\t\t%,d\t\t%,d\t\t%,d\t\t%,d\t\t\n", pay.getNationalPension(), pay.getHealthlnsurance(),
								pay.getEmploymentInsurance(), pay.getLongTermCareInsuracne(), pay.getIncomeTax(), pay.getLocalIncomeTax());
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("[지급총액]\t[공제총액]\t[실지급액]");
				System.out.printf("%,d\t%,d\t\t%,d\n", pay.getTotalPay(), pay.getTotalDeducations(), pay.getNetPay());
				
			}
			
		}
		
	}

	public static void listAdd(String month) {
		
		Scanner scan = new Scanner(System.in);
		
		String path = String.format("data\\PayStub%s.txt", month);

		File file = new File(path);

		if (file.exists()) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("이미 작성되어 있습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return;
			
		}
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			AttendanceDao.load("2023", month);
			
			PersonDao.load();
			
			String tempId = "";
			
			for (AttendanceVo attendance : AttendanceDao.list) {

				String userId = attendance.getId();
				
				String position = "", hobong = "";
				
				int basicPay = 0, positionPay = 0, totalPay = 0, totalDeducations = 0, netPay = 0;
				
				if (tempId.equals(userId)) {
					
					continue;
					
				}
				
				writer.write(attendance.getId() + ",");
				
				for (PersonVo person : PersonDao.list) {
					
					if (person.getId().equals(attendance.getId())) {
						
						writer.write(person.getName() + ",");
						writer.write(person.getDepartment() + ",");
						
						position = person.getPosition();
						hobong = person.getHobong();
						
						break;
						
					}
					
				}

				writer.write("2023-" + month + "-10,");
				
				for (SalaryStepModifyVo sv : SalaryStepModifyDao.list) {
					
					if (sv.getPosition().equals(position) && sv.getHobong().equals(hobong)) {
						
						basicPay = Integer.parseInt(sv.getBasicPay());
						positionPay = Integer.parseInt(sv.getPositionPay());
						
						writer.write(basicPay + ",");
						writer.write(positionPay + ",");
						
						break;
						
					}
					
				}

				int hour = 0, minute = 0;
				
				for (AttendanceVo attendanceTime : AttendanceDao.list) {
					
					if (attendanceTime.getId().equals(userId)) {
						
						String[] time = attendanceTime.getOvertimeHours().split(":");
						
						hour += Integer.parseInt(time[0]);
						minute += Integer.parseInt(time[1]);
						
					}
					
				}
				
				hour = hour + (minute / 60);
				
				int overtimePay = (int)(hour * 1.5 * 12440);
				
				writer.write(overtimePay + ",");
				writer.write(0 + ",");

				for (DeducationVo deducation : DeducationDao.list) {

					if (deducation.getSubscriberRate() <= 0) {
						
						continue;
						
					}
					
					writer.write((int)((basicPay + positionPay) * deducation.getSubscriberRate()) + ",");
					
					totalDeducations += (basicPay + positionPay) * deducation.getSubscriberRate();
					
				}
				
				totalPay = basicPay + positionPay + overtimePay;
				
				writer.write(totalPay + ",");
				writer.write(totalDeducations+ ",");
				
				netPay = totalPay - totalDeducations;
				
				writer.write(netPay+"\r\n");
				
				tempId = userId;
				
			}
			
			writer.close();
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("작성이 완료되었습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

	public static boolean isExistence(String id, String month) {

		String path = String.format("data\\PayStub%s.txt", month);
		
		File file = new File(path);
		
		if (!file.exists()) {
			
			return false;
			
		}
		
		PayDao.load("year", month);
		
		for (PayVo pay : PayDao.payList) {
			
			if (pay.getId().equals(id)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
}
