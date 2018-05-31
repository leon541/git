package com.leon;

import javax.faces.event.ActionEvent;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;


public class UserBean {
    private String name;
    private String password;
    private String outCome;
    private String errMessage;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getOutCome() {
		return outCome;
	}

	public void setOutCome(String outCome) {
		this.outCome = outCome;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public void loginIn(ActionEvent e){
		System.out.println(name);
		System.out.println(password);
		if(name != null && name.equals("leon")){
			outCome = "success";
		}else{
			errMessage = "wrong name!";
			outCome = "fail";
		}
	}
	
	void connectDB() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = 	(Context)initContext.lookup("java:/comp/env");
			DataSource ds =  (DataSource)envContext.lookup("jdbc/sqlserver");
			Connection conn = ds.getConnection();

			if(conn != null) {
				System.out.println("connection OK!");
				Statement st = null;
				st = conn.createStatement();
			    ResultSet rs = st.executeQuery("SELECT TOP 1000 [esg_direction_id],[direction],[recorded_date_time] FROM [esglogdb76].[dbo].[esg_direction]");
				
			    while(rs.next())
		         {
			        int id = rs.getInt("esg_direction_id");
		            String direction = rs.getString("direction");
    	             System.out.println("ID=" + id + "; direction=" + direction);
		         }
			    
			} else {
				System.out.println("connection NOT OK!");
			}
			if(conn.isClosed()) 
				System.out.println("connection close!");
			conn.close();
			
		}
		catch(Exception exception) {
			System.out.println(exception.toString());
			exception.printStackTrace();
		}
	}
	
	
}

