package com.revature.caliber.mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App{
	
	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@caliber.cgbbs6xdwjwh.us-west-2.rds.amazonaws.com:1521:orcl",
				"caliber", "DanP!ckles");
		
		ResultSet rs = conn.prepareStatement("select * from caliber_grade").executeQuery();
		int x = 1;
		while(rs.next()){
			int grade = rs.getInt("SCORE");
			Statement stmt = conn.createStatement();
			String sql = "UPDATE CALIBER_GRADE SET SCORE = " +(grade+20)+ " WHERE GRADE_ID = " + x++ ;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		}
	}

	public static void generate(String[] args) throws SQLException {
		int traineeId = 26;
		int assessmentId = 46;
		
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@caliber.cgbbs6xdwjwh.us-west-2.rds.amazonaws.com:1521:orcl",
				"caliber", "DanP!ckles");
		System.out.println(conn);
		trainee: for(; traineeId<=30; traineeId++){
			assessment: for(; assessmentId<=60; assessmentId++){
				String sql = "INSERT INTO CALIBER_GRADE VALUES"
						+ " (GRADE_ID_SEQUENCE.NEXTVAL,"
						+ " '21-DEC-16', "+(Math.random()*100)
						+", "+traineeId+", "+assessmentId+")";
				System.out.println(sql+";");
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}
			assessmentId = 46;
		}
		
		
		conn.close();
		
	}
	
}
