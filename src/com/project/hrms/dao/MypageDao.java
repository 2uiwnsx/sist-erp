package com.project.hrms.dao;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.hrms.main.UserDao;
import com.project.hrms.main.UserVo;

public class MypageDao {

	public static void checkUserInfo() {
		
		while (true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("================================================================================================");
			System.out.println("[마이페이지 - 인사 현황 조회]\n");
			System.out.println("[이름]\t[생년월일] \t[전화번호]\t[주소]\t[부서]\t[직급]\t[호봉]\t[입사일]\t[면허/자격]\t[학력/경력]\t[고과/상벌]");
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", UserDao.auth.getName(), UserDao.auth.getBirthDay(),
							UserDao.auth.getTel(), UserDao.auth.getAddr(), UserDao.auth.getDepartment(), UserDao.auth.getPosition(),
							UserDao.auth.getHobong(), UserDao.auth.getBeginDate(),
							UserDao.auth.getLicence().equals("") ? "*" : UserDao.auth.getLicence(),
							UserDao.auth.getSchool().equals("") ? "*" : UserDao.auth.getSchool(),
							UserDao.auth.getRating().equals("0") ? "*" : UserDao.auth.getRating());
			System.out.println("\nEnter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return;
			
		}

	}

	public static int changePw() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("================================================================================================");
		System.out.println("[마이페이지 - 비밀번호 변경]");

		String currentPw = null;
		String newPw = null;

		while (true) {
			
			System.out.print("현재 비밀번호를 입력하세요: ");
			String input = scan.nextLine();

			for (UserVo u : UserDao.list) {
				
				if (u.getId().equals(UserDao.auth.getId()) && u.getPw().equals(input)) {
					
					currentPw = u.getPw();
					
					break;
					
				}
				
			}

			if (currentPw == null) {
				
				System.out.println("현재 비밀번호와 일치하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
			
			} else {
				
				break;
			}
			
		}

		while (true) {
			
			System.out.println("※ 8 ~ 12자리 영문 또는 숫자");
			System.out.print("변경할 비밀번호를 입력하세요: ");
			newPw = scan.nextLine();
			System.out.println("------------------------------------------------------------------------------------------------");

			if (isValidPw(newPw) == false) {
				
				System.out.println("8 ~ 12자리 영문 또는 숫자를 입력하세요.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				continue;
				
			} else {

				while (true) {
					
					System.out.print("비밀번호를 변경하시겠습니까?(Y/N): ");
					String input = scan.nextLine();
					System.out.println("------------------------------------------------------------------------------------------------");
					
					if (input.equalsIgnoreCase("Y")) {
						
						for (UserVo u : UserDao.list) {
							
							if (u.getId().equals(UserDao.auth.getId())) {
								
								u.setPw(newPw);
								
							}
							
						}
						
						System.out.println("변경이 완료되었습니다.\n");
						System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");

						UserDao.save();
						UserDao.auth = null;
						
						scan.nextLine();
						
						return 0; 
						
					} else if (input.equalsIgnoreCase("N")) {
					
						System.out.println("변경이 취소되었습니다.\n");
						System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
						scan.nextLine();
						
						return 1;
						
					} else {
						
						System.out.println("입력값이 유효하지 않습니다.\n");
						System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
						scan.nextLine();
						
						continue;
						
					}
					
				}
				
			}
			
		}
		
	}

	private static boolean isValidPw(String newPw) {

		String regex = "^[A-Za-z0-9]{8,12}$";
		
		Pattern p1 = Pattern.compile(regex);
		
		Matcher m1 = p1.matcher(newPw);

		if (!m1.find()) {
			
			return false;
			
		}
		
		return true;
		
	}

}
