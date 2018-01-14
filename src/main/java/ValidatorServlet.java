import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;


public class ValidatorServlet extends HttpServlet{
	String name;
	/**
	 * 
	 */
	 Connection con;
	    @Override
	    public void init() throws ServletException {
	        try {
	            Class.forName("org.hsqldb.jdbc.JDBCDriver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace(System.out);
	        }
	        try {
//	        	connecting HSQLDB to eclipse
	            con=DriverManager.getConnection("jdbc:hsqldb:mydatabase","SA","");
//	            Creating and adding data to DB 
	            con.createStatement().executeUpdate("create table contacts (name varchar(45),password varchar(45))");
	            PreparedStatement pst=con.prepareStatement("insert into contacts values(?,?)");
	            pst.clearParameters();
	            pst.setString(1, "admin");
	            pst.setString(2, "admin");
	            
	            System.out.println(name);
	        } catch (SQLException e) {
	            e.printStackTrace(System.out);
	        }
	    }
	private static final long serialVersionUID = 4232031693263336994L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
	        throws ServletException, IOException {
		try{
		
	 response.setContentType("text/html");  
	 PrintWriter out = response.getWriter(); 
	 
	String username=request.getParameter("username").toString();
	String password=request.getParameter("password").toString();
//	validation of home page username
	Boolean verified= ValidatorServletWorker.doWork(username, 
													password, 
													getServletConfig().getInitParameter("user"),
													getServletConfig().getInitParameter("password"));
	Statement statement=con.createStatement();
    ResultSet resultSet=statement.executeQuery("SELECT name,password FROM contacts");
    resultSet.next();
    String dbName=resultSet.getString("name");
    String dbPassword=resultSet.getString("password");
//		System.out.println(dbName);
//		System.out.println(name);
//    	validation of UserName and Password
		if(dbName.equalsIgnoreCase(username)){
			if(dbPassword.equals(password)){
	if (verified){
		HttpSession session=request.getSession();
		session.setAttribute("username",username);
		RequestDispatcher rd=request.getRequestDispatcher("Home.jsp");  
		rd.include(request,response); 
	}
	else{  
		out.print(username);out.print(password);
        out.print("Improper USERNAME OR PASSWORD");  
        //RequestDispatcher rd=request.getRequestDispatcher("Error.jsp");  
        //rd.include(request,response);  
    }
			}else{out.print(password);
	        out.print("Invaild Password");
			}
		}else{
			out.print(username);
	        out.print("Invalid UserName");
		}
          
    out.close();  
	}catch (SQLException e) {
        e.printStackTrace(System.out);
    }
	} 

}
