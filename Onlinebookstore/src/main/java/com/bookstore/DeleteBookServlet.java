// GEDION MEKBEB AFEWORK 
// UGR/2160/14
package com.bookstore;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/deleteTask")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = -9190209876419201320L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            return;
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookstoreDB", "root", "gedion")) {
            String query = "DELETE FROM Books WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, taskId);
            stmt.executeUpdate();

            PrintWriter out = response.getWriter();
            out.println("Book deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
