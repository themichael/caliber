package com.revature.caliber.security.impl;

import org.springframework.stereotype.Component;

@Component("securityTest")
public class SecurityTest {
	public boolean canPass() {
		return true;
	}
}
