package com.project.hrms.dao;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.hrms.main.PersonDao;
import com.project.hrms.main.PersonVo;
import com.project.hrms.main.UserDao;
import com.project.hrms.main.UserVo;

public class PersonDepartmentDao {
	
	public static boolean changeDepartmentHierarchy(String input, PersonVo p) {
		
		Scanner scan = new Scanner(System.in);
		
		String[] position = {"사원", "주임", "대리", "과장", "차장", "부장"};
		String[] department = {"인사팀", "구매팀", "기획팀", "영업팀", "개발팀", "재무팀"};
		
		if (input.equals("1")) {
			
			int hobong = Integer.parseInt(p.getHobong());
			
			if (hobong < 4) {
				
				p.setHobong(String.format("%d", hobong + 1));
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("수정이 완료되었습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			} else {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("호봉을 더 이상 올릴 수 없습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return false;
				
			}
			
		} else if (input.equals("2")) {
			
			if (p.getPosition().equals(position[5])) {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("직급을 더 이상 올릴 수 없습니다\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return false;
				
			} else {
				
				for (int i = 0; i < position.length; i++) {
					
					if (position[i].equals(p.getPosition())) {
						
						p.setPosition(position[i + 1]);
						p.setHobong("1");
						
						break;
						
					}
					
				}
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("수정이 완료되었습니다\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			}
			
		} else if (input.equals("3")) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("변경될 부서를 입력하세요(인사팀, 구매팀, 기획팀, 영업팀, 개발팀, 재무팀): ");
			String dpInput = scan.nextLine();
			
			if (dpInput.equals(p.getDepartment())) {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("현재와 다른 부서를 입력하세요.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return false;
				
			}

			boolean frag = false;
			
			for (String dp : department) {
				
				if (dp.equals(dpInput)) {
					
					p.setDepartment(dpInput);
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("변경이 완료되었습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
					frag = true;
					
					return true;
					
				}
				
			}

			if (!frag) {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				return false;
				
			}
	          
		} else if (input.equals("4")) {
			
	          return true;
	          
	    }
	       
	    return true;
		
    }

	public static boolean changeEmployee(String modifyNumber,PersonVo p) {
		
		Scanner scan = new Scanner(System.in);
		
		String modify = "";
		
		System.out.println("================================================================================================");
	
		if (modifyNumber.equals("1")) {
			
			System.out.printf("1. 생년월일: ");
			modify = scan.nextLine();
			
			if (regexs("^[0-9]{4}-[0-9]{2}-[0-9]{2}", modify, "올바른 형식의 생일을 입력하세요.")) {
				
				p.setBirthDay(modify);
				
			} else {
				
				return false;
				
			}

		} else if (modifyNumber.equals("2")) {
			
			System.out.printf("2. 전화번호: ");
			modify = scan.nextLine();
			
			if (regexs("^010-[0-9]{3,4}-[0-9]{4}$", modify, "올바른 형식의 전화번호를 입력하세요.")) {
				
				p.setTel(modify);
				
			} else {
				
				return false;
				
			}
			
		} else if (modifyNumber.equals("3")) {
			
			System.out.printf("3. 주소: ");
			modify = scan.nextLine();
			
			if (regexs("^[가-힣]{1,5}시\\s[가-힣]{1,5}\\s[가-힣]{1,5}$", modify, "올바른 형식의 주소를 입력하세요.")) {
				
				p.setAddr(modify);
				
			} else {
				
				return false;
				
			}
			
		} else if (modifyNumber.equals("4")) {
			
			System.out.printf("4. 입사일: ");
			modify = scan.nextLine();
			
			if (regexs("^[0-9]{4}-[0-9]{2}-[0-9]{2}", modify, "올바른 형식의 날짜를 입력하세요.")) {
				
				p.setBeginDate(modify);
				
			} else {
				
				return false;
				
			}
			
		} else if (modifyNumber.equals("5")) {
			
			System.out.printf("5. 면허/자격: ");
			modify = scan.nextLine();
			
			p.setLicence(modify);
			
		} else if (modifyNumber.equals("6")) {
			
			System.out.printf("6. 학력/경력: ");
			modify = scan.nextLine();
			
			p.setSchool(modify);
			
		} else if(modifyNumber.equals("7")) {
			
			return true;
			
		} else {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("입력값이 유효하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}

		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("수정이 완료되었습니다.\n");
		System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
		scan.nextLine();
		
		return true;

	}

	public static boolean newEmployee(PersonVo newEmployee) {
		
		Scanner scan = new Scanner(System.in);

		PersonVo v = PersonDao.list.get(PersonDao.list.size() - 1);
		
		if (v.getId().contains("A")) {
			
			v = PersonDao.list.get(PersonDao.list.size() - 2);
			
		}
		
		String ExtractNewEmployeeNumber1 = v.getId().replace("M", "").replace("A", "");
		
		int NewEmployeeNumber = Integer.parseInt(ExtractNewEmployeeNumber1) + 1;
		
		String newEmployeeId = "M" + NewEmployeeNumber;

		if (isValid(newEmployee)) {

			newEmployee.setId(newEmployeeId);
			newEmployee.setWorkStatus("true");
			
			PersonDao.list.add(newEmployee);
			
			loginEmployeeAdd(newEmployee);

			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("등록이 완료되었습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return true;
		
		} else {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("등록에 실패했습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}

	}

	private static void loginEmployeeAdd(PersonVo newEmployee) {

		UserDao.list.add(new UserVo(newEmployee.getId(),"1111","1"));
		
	}

	public static boolean isValid(PersonVo newEmployee) {
		
		Scanner scan = new Scanner(System.in);
		
		String regex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}";
		
		Pattern p1 = Pattern.compile(regex);
		
		Matcher m1 = p1.matcher(newEmployee.getBirthDay());
		
		System.out.println("------------------------------------------------------------------------------------------------");

		if (!(m1.find())) {
			
			System.out.println("올바른 형식의 생일을 입력하세요.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}

		regex = "^010-[0-9]{3,4}-[0-9]{4}$";
		
		p1 = Pattern.compile(regex);
		
		m1 = p1.matcher(newEmployee.getTel());
		
		if (!(m1.find())) {
			
			System.out.println("올바른 형식의 전화번호를 입력하세요.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}

		regex = "^[가-힣]{1,5}시\\s[가-힣]{1,5}\\s[가-힣]{1,5}$";
		
		p1 = Pattern.compile(regex);
		
		m1 = p1.matcher(newEmployee.getAddr());
		
		if (!(m1.find())) {
			
			System.out.println("올바른 형식의 주소를 입력하세요.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}

		regex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
		
		p1 = Pattern.compile(regex);
		
		m1 = p1.matcher(newEmployee.getBeginDate());
		
		if (!(m1.find())) {
			
			System.out.println("올바른 형식의 날짜를 입력하세요.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}

		boolean frag = false;
		
		String[] dp = {"인사팀", "구매팀", "기획팀", "영업팀", "개발팀", "재무팀"};
		
		for (String list1 : dp) {
			
			if (list1.equals(newEmployee.getDepartment())){
				
				frag = true;
				
				break;
				
			}
			
		}
		
		if (!frag) {
			
			System.out.println("부서를 잘못 입력하셨습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}

		String[] po = {"주임", "부장", "차장", "대리", "사원", "과장"};
		
		frag = false;
		
		for (String list2 : po) {
			
			if (list2.equals(newEmployee.getPosition())){
				
				frag = true;
				
				break;
				
			}
			
		}
		
		if (!frag) {
			
			System.out.println("직급을 잘못 입력하셨습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}

		if (0 > Integer.parseInt(newEmployee.getHobong()) || 4 < Integer.parseInt(newEmployee.getHobong())) {
			
			System.out.println("호봉은 1 ~ 4만 입력 가능합니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}
		
		return true;

	}	

	private static boolean regexs(String regex, String ps, String msg) {
		
		String regexs = regex;
		
		Pattern p1 = Pattern.compile(regexs);
		
		Matcher m1 = p1.matcher(ps);
		
		if (!(m1.find())) {
			
			System.out.println(msg);
			
			return false;
			
		}
		
		return true;
		
	}

	public static void updateArrayList(List<PersonVo> list, PersonVo updatedObject) {
		
		for (int i = 0; i < list.size(); i++) {
			
			PersonVo old = list.get(i);
			
			if (old.getId().equals(updatedObject.getId())) {
				
				list.set(i, updatedObject);
				
				break;
				
			}
			
		}
		
	}
	
}
