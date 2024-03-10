package com.project.hrms.dao;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.hrms.vo.PayItemVo;

public class PayItemDao {


	public static ArrayList<PayItemVo> list;
	
	static {
		
		PayItemDao.list = new ArrayList<PayItemVo>();
		
	}

	public static void load() {
		
		String path = "data\\payItem.txt";
		
		File file = new File(path);
		
		if (!file.exists()) {
			
			return;
			
		}
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				PayItemVo payItem = new PayItemVo(Integer.parseInt(temp[0]), temp[1], Boolean.parseBoolean(temp[2]));
				
				PayItemDao.list.add(payItem);
				
			}
			
			reader.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void listShow() {
		
		System.out.println("================================================================================================");
		System.out.printf("%3s%10s\t\t%s\n", "[번호]", "[항목]", "[사용여부]");
		
		for (PayItemVo payItem : PayItemDao.list) {
			
			System.out.printf("%3d%10s\t\t%b\n", payItem.getNum(), payItem.getPayItem(), payItem.isToUse());
			
		}
		
	}

	public static boolean toUseChange(int num) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean frag = false;
		
		for (PayItemVo payItem : PayItemDao.list) {
			
			if (payItem.getNum() == num) {
				
				payItem.setToUse(!payItem.isToUse());
				
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
		
		return frag;
		
	}

	public static void itemAdd(String item) {
		
		Scanner scan = new Scanner(System.in);

		Integer maxNo = PayItemDao.list.stream().map(s -> s.getNum()).max((a, b) -> a - b).get();
				
		int no = maxNo + 1;
		
		PayItemVo d = new PayItemVo(no, item, true);
		
		PayItemDao.list.add(d);
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("추가가 완료되었습니다.\n");
		System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
		scan.nextLine();
		
	}

	public static boolean delete(int num) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean frag = false;
		
		for (PayItemVo payItem : PayItemDao.list) {
			
			if (payItem.getNum() == num) {
				
				frag = true;
				
				PayItemDao.list.remove(payItem);
				
				break;
				
			}
			
		}
		
		if (frag) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("삭제가 완료되었습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return true;
			
		} else {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("해당 항목이 존재하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}
		
	} 
	
}
