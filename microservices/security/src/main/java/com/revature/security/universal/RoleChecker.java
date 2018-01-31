package com.revature.security.universal;

public class RoleChecker {
	public boolean allowedRoles(String userrole, String...roles) {
		boolean hasrole = false;
		for(int i=0;i<roles.length;i++) {
			if(userrole.equals(roles[i])) hasrole=true;
		}
		return hasrole;
	}
	
	public boolean testing(String userrole) {
		System.out.println(userrole);
		return true;
	}
}
