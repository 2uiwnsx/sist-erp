package com.project.hrms.dao;

import com.project.hrms.main.PersonDao;
import com.project.hrms.main.PersonVo;

public class PersonDepartmentCh {

	public static String checkDepartmentMember(String dp) {
		
		String result = "";
		
		for (PersonVo pv : PersonDao.list) {
			
			if (pv.getDepartment().equals(dp) && pv.getWorkStatus().equals("true")) {
				
				result += printDp(pv);
				
			}
			
		}
		
		return result;
		
	}

	public static String printDp(PersonVo pv) {
		
		String result = String.format("%s\t%2s\t%3s\t%s\n", pv.getDepartment(), pv.getPosition(), pv.getName(), pv.getTel());
		
		return result;
		
	}

}
