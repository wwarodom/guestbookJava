package com.myapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.helper.Utility;
import com.myapp.model.Guestbook;

/**
 * Servlet implementation class GuestBookServlet
 */
@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuestBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			if (action.equalsIgnoreCase("delete")) {
				deleteGuestbook(request, response, id);
				getAll(request, response);
			} else if (action.equalsIgnoreCase("edit")) {
				editGuestbook(request, response, id);
			} else if (action.equalsIgnoreCase("update")) {
				updateGuestbook(request, response, id);
				getAll(request, response);
			}
		} else {
			getAll(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action != null && action.equalsIgnoreCase("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			updateGuestbook(request, response, id);
		} else {
			insertGuestbook(request, response);
		}
		doGet(request, response);
	}

	protected void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Guestbook> guestbook = new ArrayList<Guestbook>();
		try {
			Connection conn = Utility.getDatabaseConnection();
			Statement statement = conn.createStatement();
			String sql = "SELECT * from GUESTBOOK";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				guestbook.add(new Guestbook(rs.getInt("id"), rs.getString("name"), rs.getString("message")));
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("guestbook", guestbook);
		RequestDispatcher view = request.getRequestDispatcher("guestbook.jsp");
		view.forward(request, response);
	}

	public void insertGuestbook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		try {
			Connection conn = Utility.getDatabaseConnection();
			Statement statement = conn.createStatement();
			// Should use PrepareStatement 
			String sql = "INSERT INTO guestbook (name,message) VALUES ('" + name + "','" + message + "');";
			System.out.println("SQL = " + sql);
			statement.executeUpdate(sql);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteGuestbook(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
		try {
			Connection conn = Utility.getDatabaseConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("delete from guestbook where id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editGuestbook(HttpServletRequest request, HttpServletResponse response, int id)
			throws IOException, ServletException {
		Guestbook gb = null;
		try {
			Connection conn = Utility.getDatabaseConnection();
			String sql = "SELECT * from GUESTBOOK where id = ?";
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			// System.out.println(sql);
			rs.next();
			gb = new Guestbook(rs.getInt("id"), rs.getString("name"), rs.getString("message"));
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("guestbook", gb);
		RequestDispatcher view = request.getRequestDispatcher("editGuestbook.jsp");
		view.forward(request, response);
	}

	public void updateGuestbook(HttpServletRequest request, HttpServletResponse response, int id) {
		try {
			Connection conn = Utility.getDatabaseConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("update guestbook set name=?, message=? where id=?");
			preparedStatement.setString(1, request.getParameter("name"));
			preparedStatement.setString(2, request.getParameter("message"));
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
