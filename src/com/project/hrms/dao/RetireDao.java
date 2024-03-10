package com.project.hrms.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import com.project.hrms.main.PersonDao;
import com.project.hrms.main.PersonVo;
import com.project.hrms.main.UserDao;
import com.project.hrms.main.UserVo;

public class RetireDao {

	public static void retireeProcess() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("사번을 입력하세요: ");
		String id = scan.nextLine();
		
		boolean loop = true;

		for (UserVo uv : UserDao.list) {
			
			if (uv.getId().equals(id)) {
				
				for (PersonVo p : PersonDao.list) {
					
					if (p.getId().equals(id)) {

						if (p.getWorkStatus().equals("true")) {
							
							System.out.println("------------------------------------------------------------------------------------------------");
							System.out.printf("%s님을 퇴직처리 하시겠습니까?(Y/N) ", p.getName());
							String input = scan.nextLine();
							
							while (loop){
								
								if (input.equals("Y") || input.equals("y")) {
									
									p.setWorkStatus("false");
									
									PersonDao.save();
									
									System.out.println("------------------------------------------------------------------------------------------------");
									System.out.println("처리가 완료되었습니다.\n");
									System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
									scan.nextLine();

									int sum = 0;
									sum += salary(p.getId(), 5);
									sum += salary(p.getId(), 6);
									sum += salary(p.getId(), 7);
									
									int calcSeverancePay = (sum/calc_prev_3_month_days()) * 30 * (calc_work_days(p.getBeginDate()) / 365);

									try {
										
										FileWriter fw = new FileWriter("data\\severancePay.txt", true);
										
										fw.write(p.getId() + "," + p.getBeginDate() + "," + LocalDate.now() + "," +  (calc_work_days(p.getBeginDate())/365) + "," + calcSeverancePay + "\n");
										
										fw.close();
										
									} catch(Exception e) {
										
										e.printStackTrace();
										
									}
									
									return;
									
								} else if (input.equals("N") || input.equals("n")) {
									
									System.out.println("------------------------------------------------------------------------------------------------");
									System.out.println("입력값이 유효하지 않습니다.\n");
									System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
									scan.nextLine();
									
									loop = false;
									
								} else {
									
									System.out.println("------------------------------------------------------------------------------------------------");
									System.out.println("입력값이 유효하지 않습니다.\n");
									System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
									scan.nextLine();
									
									loop = false;
									
								}
								
							}
							
						}
						
						else {
							
							System.out.println("------------------------------------------------------------------------------------------------");
							System.out.printf("%s님은 퇴사자 입니다\n", p.getName());
							System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
							scan.nextLine();
							
						}
						
						return;
						
					}
					
				}
				
			}
			
		}
		
	}

	public static void calcSeverancePay() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("사번을 입력하세요: ");
		String id = scan.nextLine();

		for (PersonVo p : PersonDao.list) {
			
			if (p.getId().equals(id)) {

				if (p.getWorkStatus().equals("true")) {
					
					int sum = 0;
					sum += salary(p.getId(), 5);
					sum += salary(p.getId(), 6);
					sum += salary(p.getId(), 7);
					
					System.out.println("================================================================================================");
					System.out.printf("%s님은 재직자입니다.\n",p.getName());
					System.out.printf("입사일 : %s\n", p.getBeginDate());
					System.out.printf("재직일수 : %d(일)\n", calc_work_days(p.getBeginDate()));

					int calcSeverancePay = (sum/calc_prev_3_month_days()) * 30 * (calc_work_days(p.getBeginDate()) / 365);
					
					System.out.printf("예상 퇴직금 : %,d\n", calcSeverancePay);
					
				} else {
					
					System.out.println("================================================================================================");
					System.out.printf("%s님은 퇴사자 입니다\n", p.getName());
					
					int retireeMoney = paidSeverancePay(p.getId());
					
					System.out.printf("지급된 퇴직금 : %,d\n", retireeMoney);
				}
				
				break;
				
			}
			
		}
		
	}

	public static int paidSeverancePay(String id) {
		
		try {
			
			String path = "data\\severancePay.txt";
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				if (temp[0].equals(id)) {
					
					return (Integer.parseInt(temp[4]));
					
				}
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return 0;
		
	}

	public static int calc_work_days(String strDate) {

        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        LocalDate dateTime = LocalDate.parse(strDate, formatter);
        
        int DiffDays = (int) ChronoUnit.DAYS.between(dateTime, now);
        
        return DiffDays;
        
	}

	public static int calc_prev_3_month_days() {
		
		LocalDate now = LocalDate.now();
		
		int days = 0;
		
		for (int i = 1; i < 4; i++) {
			
			LocalDate temp_date = now.minusMonths(i);

			days += temp_date.lengthOfMonth();
			
		}
		
		return days;
		
	}

	public static int salary(String id, int month) {
		
		int money = 0;
		
		try {
			
			String path = String.format("data\\PayStub%d.txt", month);
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				if (id.equals(temp[0])) {
					
					money = Integer.parseInt(temp[16]);
					
					break;			
					
				}
				
			}		
			
			reader.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return money;
		
	}
	
}
