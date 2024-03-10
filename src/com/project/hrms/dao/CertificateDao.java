package com.project.hrms.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.hrms.main.PersonDao;
import com.project.hrms.main.PersonVo;
import com.project.hrms.main.UserDao;
import com.project.hrms.vo.CertificateVo;

public class CertificateDao {

	public static ArrayList<CertificateVo> list;
	
	static {
		
		list = new ArrayList<CertificateVo>();
		
	}
	
	static String path = "data\\certiRqList.txt";
	
	public static void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				CertificateVo c = new CertificateVo(Integer.parseInt(temp[0]), temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
				
				CertificateDao.list.add(c);
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void save() {

		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (CertificateVo c : list) {
				
				writer.write(String.format("%d,%s,%s,%d,%d\n", c.getNo(), c.getRqDate(), c.getId(), c.getType(), c.getCertStatus()));
			
			}
			
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static void memberCertiRqList() {
		
		Scanner scan = new Scanner(System.in);
		
		int n = 1, listFlag = 0;

		for (CertificateVo c : list) {
			
			if (c.getId().equals(UserDao.auth.getId())) {

				if (listFlag == 0) {
					
					System.out.println("================================================================================================");
					System.out.println("[일련번호]\t[날짜]\t[사번]\t[이름]\t[부서]\t[신청 증명서 종류]\t[승인 상태]");
					
					listFlag = 1;
					
				}
				
				System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\n", n++, c.getRqDate(), c.getId(), UserDao.auth.getName(), UserDao.auth.getDepartment(),
								c.getType() == 1 ? "재직증명서" : "경력증명서", c.getCertStatus() == 1 ? "승인 완료" : "승인 전");
			
			}
			
		}
		
		if (listFlag == 0) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("신청 내역이 없습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
		}
		
	}
	
	public static void employmentCertiRq() {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.print("발급을 신청하시겠습니까?(Y/N): ");
			String input = scan.nextLine();
			System.out.println("------------------------------------------------------------------------------------------------");
			
			if (input.equalsIgnoreCase("Y")) {
				
				Calendar cal = Calendar.getInstance();
				
				CertificateVo c = new CertificateVo(list.size() + 1, String.format("%tF", cal), UserDao.auth.getId(), 1, 0);
				
				list.add(c);

				System.out.println("신청이 완료되었습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return;
				
				
			} else if (input.equalsIgnoreCase("N")) {
				
				System.out.println("신청이 취소되었습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return;
				
			} else {
				
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			}
			
		}
		
	}
	
	public static void careerCertiRq() {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.print("발급을 신청하시겠습니까?(Y/N): ");
			String input = scan.nextLine();
			System.out.println("------------------------------------------------------------------------------------------------");
			
			if (input.equalsIgnoreCase("Y")) {
				
				Calendar cal = Calendar.getInstance();
				
				CertificateVo c = new CertificateVo(list.size() + 1, String.format("%tF",  cal), UserDao.auth.getId(), 2, 0);
				
				list.add(c);
				
				System.out.println("신청이 완료되었습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return;
				
				
			} else if (input.equalsIgnoreCase("N")) {
				
				System.out.println("신청이 취소되었습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return;
				
			} else {
				
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
			}
			
		}
		
	}

	public static void adminCertiRqList() {
		
		System.out.println("================================================================================================");
		System.out.println("[일련번호]    [신청일]\t[사번]\t[신청자명]\t [부서]\t[증명서 종류]  [처리 상태]");
		
		for (CertificateVo c : list) {
			
			System.out.printf("%5d \t  %s\t%s\t %s\t %s\t %s    %s\n", c.getNo(), c.getRqDate(), c.getId(), getname(c), getdepartment(c),
							c.getType() == 1 ? "재직증명서" : "경력증명서", c.getCertStatus() == 0? "승인 전" : "승인 완료");

		}
		
	}

	private static String getdepartment(CertificateVo c) {
		
		for (PersonVo p : PersonDao.list) {
			
			if (c.getId().equals(p.getId())) {

				return p.getDepartment();
				
			}
			
		}
		
		return "";
		
	}

	private static String getname(CertificateVo c) {
		
		for (PersonVo p : PersonDao.list) {
			
			if (c.getId().equals(p.getId())) {
				
				return p.getName();
			}
			
		}
		
		return "";
		
	}

	public static void adminApprovalRq() {
		
		Scanner scan = new Scanner(System.in);
		
		try {

			while (true) {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.print("발급 신청을 승인할 일련번호를 입력하세요: ");
				String no = scan.nextLine();
				System.out.println("------------------------------------------------------------------------------------------------");

				for (CertificateVo c : list) {
					
					if (c.getNo() == Integer.parseInt(no)) {
						
						if (c.getCertStatus() == 0) {
							
							c.setCertStatus(1);
							
							System.out.println("신청이 승인되었습니다.\n");
							System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
							scan.nextLine();
							
							return;
							
						} 
						System.out.println("기승인 완료된 건입니다.\n");
						System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
						scan.nextLine();
						
						return;
						
					}
					
				}
				
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			}
			
		} catch (NumberFormatException e) {
			
			System.out.println("입력값이 유효하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
		}
		
	}
	
}
