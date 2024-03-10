package com.project.hrms.admin.view;

import java.util.Scanner;

import com.project.hrms.dao.PersonDepartmentDao;
import com.project.hrms.main.PersonDao;
import com.project.hrms.main.PersonVo;

public class PersonDepartmentView {
	
	
	public static void PersonDepartmentChView() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[인사관리정보]");
			System.out.println("1. 신규입사자 등록");
			System.out.println("2. 재직자 정보 수정");
			System.out.println("3. 인사발령 등록");
			System.out.println("4. 메인화면");
			System.out.print("번호를 입력하세요: ");
			String select = scan.nextLine();

			if (select.equals("1")) {
				
				newEmployeeView();
				
			} else if (select.equals("2")) {
				
				changeEmployeeView();
				
			} else if (select.equals("3")) {
				
				changeDepartmentHierarchyView();
				
			} else if (select.equals("4")) {
				
				return;
				
			} else {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			}
			
		}

	}

	public static void changeDepartmentHierarchyView() {

		Scanner scan = new Scanner(System.in);

		System.out.println("================================================================================================");
		System.out.println("[인사발령 등록]");
		System.out.print("사번을 입력하세요: ");
		String id = scan.nextLine();
		
		PersonVo p = new PersonVo();
		
		for (PersonVo ps : PersonDao.list) {
			
			if (ps.getId().equals(id)) {
				
				p = ps;
				
				break;
				
			}
			
		}

		while (true) {
			
			if (p.getId() != null) {
				
				System.out.println("================================================================================================");
				System.out.printf("[%s %s %s님 %s호봉]\n", p.getDepartment(), p.getName(), p.getPosition(), p.getHobong());
				System.out.println("1. 호봉 승급");
				System.out.println("2. 승진");
				System.out.println("3. 부서 이동");
				System.out.println("4. 이전 화면");
				System.out.print("번호를 입력하세요: ");
				String input = scan.nextLine();
				
				if (PersonDepartmentDao.changeDepartmentHierarchy(input, p)) {
					
					return;
					
				}
				
			} else {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return;
			}
		
		}

	}

	private static void changeEmployeeView() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("================================================================================================");
		System.out.println("[재직자 정보 수정]");
		System.out.print("사번을 입력하세요: ");
		String id = scan.nextLine();
		
		PersonVo p = new PersonVo();
		
		for (PersonVo ps : PersonDao.list) {
			
			if (ps.getId().equals(id)) {
				
				p = ps;
				
				break;
				
			}
			
		}

		while (true) {
			
			if (p.getId() != null) {
				
				System.out.println("================================================================================================");
				System.out.printf("[%s %s %s님]\n", p.getDepartment(), p.getName(), p.getPosition());
				System.out.printf("1. 생년월일: %s\n", p.getBirthDay());
				System.out.printf("2. 전화번호: %s\n", p.getTel());
				System.out.printf("3. 주소: %s\n", p.getAddr());
				System.out.printf("4. 입사일: %s\n", p.getBeginDate());
				System.out.printf("5. 면허/자격: %s\n", p.getLicence());
				System.out.printf("6. 학력/경력: %s\n", p.getSchool());
				System.out.println("7. 이전 화면");
				System.out.print("수정할 항목의 번호를 입력하세요:");
				String modifyNumber = scan.nextLine();
				
				if (PersonDepartmentDao.changeEmployee(modifyNumber, p)) {
					
					return;
					
				}
				
			} else {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("해당 직원이 존재하지 않습니다.");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return;
				
			}

		}
		
	}
	
	public static void newEmployeeView() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("================================================================================================");
		System.out.println("[신규입사자 등록]");
		
		PersonVo newEmployee = new PersonVo();
		
		System.out.print("1. 이름을 입력하세요:");
		
		String name = scan.nextLine();
		newEmployee.setName(name);

		System.out.print("2. 생년월일을 입력하세요(XXXX-XX-XX):");
		
		String birthDay = scan.nextLine();
		newEmployee.setBirthDay(birthDay);

		System.out.print("3. 전화번호를 입력하세요(010-XXXX-XXXX):");
		
		String tel = scan.nextLine();
		newEmployee.setTel(tel);

		System.out.print("4. 주소를 입력하세요(xx시 xx구 xx동):");
		
		String addr = scan.nextLine();
		newEmployee.setAddr(addr);

		System.out.print("5. 입사일을 입력하세요(XXXX-XX-XX):");
		
		String beginDate = scan.nextLine();
		newEmployee.setBeginDate(beginDate);

		System.out.print("6. 부서를 입력하세요(인사팀, 구매팀, 기획팀, 영업팀, 개발팀, 재무팀):");
		
		String department = scan.nextLine();
		newEmployee.setDepartment(department);

		System.out.print("7. 직급을 입력하세요(사원, 주임, 대리, 과장, 차장, 부장):");
		
		String position = scan.nextLine();
		newEmployee.setPosition(position);

		System.out.print("8. 호봉을 입력하세요(1 ~ 4):");
		
		String hobong = scan.nextLine();
		newEmployee.setHobong(hobong);
		
		System.out.print("9. 면허/자격을 입력하세요:");
		
		String licence = scan.nextLine();
		newEmployee.setLicence(licence);
		
		System.out.print("10. 학력/경력을 입력하세요:");
		
		String school = scan.nextLine();
		newEmployee.setSchool(school);
		newEmployee.setRating("0");
		
		if (PersonDepartmentDao.newEmployee(newEmployee)) {
			
			return;
			
		}

	}

}