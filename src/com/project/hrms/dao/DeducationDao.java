package com.project.hrms.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.hrms.vo.DeducationVo;

public class DeducationDao {

	public static ArrayList<DeducationVo> list;
	
	static {
		
		list = new ArrayList<DeducationVo>();
		
	}
	
	public static void load() {
		
		String path = "data\\deducation.txt";
		
		File file = new File(path);
		
		if (!file.exists()) {
			
			return;
			
		}
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");

				list.add(new DeducationVo(Integer.parseInt(temp[0]), temp[1], Float.parseFloat(temp[2]),
						Float.parseFloat(temp[3]), Boolean.parseBoolean(temp[4])));
				
			}
			
			reader.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static void listShow() {
		
		System.out.println("================================================================================================");
		System.out.printf("%3s%10s\t\t%s\n", "[번호]", "[항목]", "[사용여부]");
		
		for (DeducationVo deducation : DeducationDao.list) {
			
			System.out.printf("%3d%10s\t\t%b\n", deducation.getNum(), deducation.getDeducation(), deducation.isToUse());
			
		}
		
	}

	public static boolean toUseChange(int num) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean frag = false;
		
		for (DeducationVo deducation : DeducationDao.list) {
			
			if (deducation.getNum() == num) {
				
				deducation.setToUse(!deducation.isToUse());
				
				frag = true;
				
				break;
				
			}
			
		}
		
		if (!frag) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("입력값이 유효하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("변경이 완료되었습니다.\n");
		System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
		scan.nextLine();
		
		return true;
		
	}

	public static boolean itemAdd(String item, double subscriberRate, double companyRate) {
		
		Scanner scan = new Scanner(System.in);
		
		if (subscriberRate <= 0 || subscriberRate > 100 || companyRate <= 0 || companyRate > 100) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("부담율은 0% 이하 또는 100% 초과가 될 수 없습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
		}
		
		Integer maxNo = DeducationDao.list.stream().map(s -> s.getNum()).max((a,b) -> a- b).get();
		
		int no = maxNo +1;
		
		DeducationVo d = new DeducationVo(no, item, subscriberRate * 0.01, companyRate * 0.01, true);
		
		DeducationDao.list.add(d);
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("추가가 완료되었습니다.\n");
		System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
		scan.nextLine();
		
		return true;
		
		
	}

	public static boolean delete(int num) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean frag = false;
		
		for (DeducationVo d : DeducationDao.list) {
			
			if (d.getNum() == num) {
				
				frag = true;
				
				DeducationDao.list.remove(d);
				
				break;
				
			}
			
		}
		
		if (!frag) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("입력값이 유효하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("삭제가 완료되었습니다.\n");
		System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
		scan.nextLine();
		
		return true;
		
	}


	public static boolean subscriberRate(String num) {
		
		Scanner scan = new Scanner(System.in);
		
		for (DeducationVo d : DeducationDao.list) {
			
			if (d.getNum() == Integer.parseInt(num)) {
				
				System.out.println("================================================================================================");
				System.out.printf("현재 가입자부담율(단위: %%): %.2f\n", d.getSubscriberRate() * 100);
				System.out.print("수정할 가입자부담율(단위: %): ");
				double rate = scan.nextDouble();
				System.out.println("------------------------------------------------------------------------------------------------");
				
				if (rate <= 0 || rate > 100) {

					System.out.println("부담율은 0% 이하 또는 100% 초과가 될 수 없습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
					return false;
					
				}
				
				d.setSubscriberRate(rate / 100);
				
				break;
				
			}
			
		}

		System.out.println("수정이 완료되었습니다.\n");
		System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
		scan.nextLine();
		
		return true;
		
	}

	public static boolean companyRate(String num) {
		
		Scanner scan = new Scanner(System.in);
		
		for (DeducationVo d : DeducationDao.list) {
			
			if (d.getNum() == Integer.parseInt(num)) {
				
				System.out.println("================================================================================================");
				System.out.printf("현재 사업주부담율(단위: %%): %.2f\n", d.getCompanyRate() * 100);
				System.out.print("수정할 사업주부담율(단위: %):");
				double rate = scan.nextDouble();
				System.out.println("------------------------------------------------------------------------------------------------");
				
				if (rate <= 0 || rate > 100) {
					
					System.out.println("부담율은 0% 이하 또는 100% 초과가 될 수 없습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
					return false;
					
				}
				
				d.setCompanyRate(rate / 100);
				
				break;
				
			}
			
		}
		
		System.out.println("수정이 완료되었습니다.\n");
		System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
		scan.nextLine();
		
		return true;
		
	} 
	
	public static void save() {
		
		try {
			
			String path = "data\\Deducation.txt";
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (DeducationVo d : DeducationDao.list) {
				
				writer.write(String.format("%d,%s,%f,%f,%b\n", d.getNum(), d.getDeducation(), d.getSubscriberRate(), d.getCompanyRate(), d.isToUse()));
						
			}
					
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
