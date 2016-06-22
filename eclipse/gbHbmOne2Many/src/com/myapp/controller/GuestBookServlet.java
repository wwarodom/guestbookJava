package com.myapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.myapp.helper.HibernateUtil;
import com.myapp.model.Message;
import com.myapp.model.User;

/**
 * Servlet implementation class GuestBookServlet
 */
@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
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
			System.out.println("test");
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

	@SuppressWarnings("unchecked")
	protected void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("FROM Message");
		List <Message> messages =   query.list();
		for(Message item : messages ) {
			System.out.println( item);
			System.out.println("User id: " + item.getUser().getId() + "\n Name: " + item.getUser().getUsername() );
		}		
		tx.commit();
		session.close();
		request.setAttribute("messages", messages);
		RequestDispatcher view = request.getRequestDispatcher("guestbook.jsp");
		view.forward(request, response);
	}

	public void insertGuestbook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		String messageIn = request.getParameter("message");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();		
		Transaction tx = session.beginTransaction();		
		User user = session.get(User.class, userid );
		Message message  = new Message(user, messageIn);		
	    Integer messageId = (Integer) session.save(message);
	    System.out.println("guestbookID = " + messageId );
	    tx.commit();
	    session.close();
	}

	public void deleteGuestbook(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
	    Message message = (Message)session.get(Message.class, id);
        session.delete(message);
        tx.commit();
        session.close();
	}

	public void editGuestbook(HttpServletRequest request, HttpServletResponse response, int id)
			throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Message message = (Message)session.get(Message.class, id);
        tx.commit();
        session.close();
		request.setAttribute("message", message);		
		RequestDispatcher view = request.getRequestDispatcher("editGuestbook.jsp");
		view.forward(request, response);
	}

	public void updateGuestbook(HttpServletRequest request, HttpServletResponse response, int id) {
//		String name = request.getParameter("name");
		String message = request.getParameter("message");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Message gb = (Message) session.get(Message.class, id );
//		gb.setName(name);
		gb.setMessage(message);
		session.update(gb);		
        tx.commit();
        session.close();
	}
}
