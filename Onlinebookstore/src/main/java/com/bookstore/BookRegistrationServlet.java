// GEDION MEKBEB AFEWORK 
// UGR/2160/14

package com.bookstore;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BookRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 10000000;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Title = request.getParameter("Title");
        String Author = request.getParameter("Author");
        String Price = request.getParameter("Price");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Updated driver class for modern MySQL versions
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            return;
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookstoreDB", "root", "gedion")) {
            String query = "INSERT INTO Tasks (Title, Author, Price) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, Title);
            stmt.setString(2, Author);
            stmt.setString(3, Price);
            stmt.executeUpdate();

            PrintWriter out = response.getWriter();
            out.println("Book successfully registered!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}























