package com.project.hrms.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.hrms.vo.SalaryStepModifyVo;

public class SalaryStepModifyDao {

	public static ArrayList<SalaryStepModifyVo> list;
	
	static {
		
		list = new ArrayList<SalaryStepModifyVo>();
		
	}
	
	static String path = "data\\hobong.txt";
	
	public static void load() {

		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null)	{
				
				String[] temp = line.split(",");
				
				SalaryStepModifyVo s = new SalaryStepModifyVo(temp[0], temp[1], temp[2], temp[3]);
				
				SalaryStepModifyDao.list.add(s);
				
			}

			reader.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			for (SalaryStepModifyVo s : SalaryStepModifyDao.list) {
				
				writer.write(String.format("%s,%s,%s,%s\n", s.getPosition(), s.getHobong(), s.getBasicPay(), s.getPositionPay()));
				
			}
			
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

	public static void hobongList() {
		
		System.out.println("================================================================================================");
		System.out.println("[직급]\t[호봉]\t [기본급]    [직책수당]");
		
		for (SalaryStepModifyVo s : list) {
			
			System.out.printf(" %s\t  %s\t%,7d   %,7d\n", s.getPosition(), s.getHobong(), Integer.parseInt(s.getBasicPay()), Integer.parseInt(s.getPositionPay()));
		
		}
		
	}

	public static boolean hobongBasicPayModify(String position, String hobong) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean frag = false;
		
		try {

			for (SalaryStepModifyVo s : list) {
				
				if (s.getPosition().equals(position) && s.getHobong().equals(hobong)) {
					
					frag = true;
					
					System.out.println("================================================================================================");
					System.out.printf("[%s]직급 [%s호봉]의 기본급은 '%,d(원)'입니다.\n", s.getPosition(), s.getHobong(), Integer.parseInt(s.getBasicPay()));
					System.out.print("변경할 기본급 금액(단위: 원)을 입력해주세요: ");
					String newpay = scan.nextLine();
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.printf("입력하신 금액은 [%,d(원)]입니다.", Integer.parseInt(newpay));
					
					while(true) {
						
						System.out.print("수정하시겠습니까?(Y/N): ");
						String input = scan.nextLine();
						
						if (input.equalsIgnoreCase("Y")) {
							
							s.setBasicPay(newpay);
							
							System.out.println("------------------------------------------------------------------------------------------------");
							System.out.println("수정이 완료되었습니다.\n");
							System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
							scan.nextLine();
							
							return true;
							
						} else if (input.equalsIgnoreCase("N")) {
							
							System.out.println("------------------------------------------------------------------------------------------------");
							System.out.println("수정이 취소되었습니다.\n");
							System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
							scan.nextLine();
							
							return true;
							
						} else {
							
							System.out.println("------------------------------------------------------------------------------------------------");
							System.out.println("입력값이 유효하지 않습니다.\n");
							System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
							scan.nextLine();
							
						}	
						
					}
					
				}
				
			}
			
		} catch (NumberFormatException e) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("입력값이 유효하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
		}
		
		return frag;

	}

	public static boolean hobongPositionPayModify(String position) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean frag = false;
		
		try {
	
			for (SalaryStepModifyVo s : list) {
				
				if (s.getPosition().equals(position)) {
					
					frag = true;
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.printf("[%s]직급직책수당은 '%,d(원)'입니다.\n", s.getPosition(), Integer.parseInt(s.getPositionPay()));
					System.out.print("변경할 직책수당 금액(단위: 원)을 입력해주세요: ");
					String newpay = scan.nextLine();
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.printf("입력하신 금액은 [%,d(원)]입니다.\n", Integer.parseInt(newpay));

					while (true) {
						
						System.out.print("수정하시겠습니까?(Y/N): ");
						String input = scan.nextLine();

						if (input.equalsIgnoreCase("Y")) {
							
							for (SalaryStepModifyVo s2 : list) {
								
								if (s2.getPosition().equals(position)) {
									
									s2.setPositionPay(newpay);
									
								}
								
							}
							
							System.out.println("------------------------------------------------------------------------------------------------");
							System.out.println("수정이 완료되었습니다.\n");
							System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
							scan.nextLine();
							
							return true;

						} else if (input.equalsIgnoreCase("N")) {

							System.out.println("------------------------------------------------------------------------------------------------");
							System.out.println("수정이 취소되었습니다.\n");
							System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
							scan.nextLine();
							
							return true;

						} else {

							System.out.println("------------------------------------------------------------------------------------------------");
							System.out.println("입력값이 유효하지 않습니다.\n");
							System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
							scan.nextLine();
							
						}

					}
					
				}
				
			}

		} catch (NumberFormatException e) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("입력값이 유효하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
		
		}
		
		return frag;
		
	}

}
