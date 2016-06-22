package com.myapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.hibernate.cfg.Configuration;

import com.myapp.helper.HibernateUtil;
import com.myapp.model.Guestbook;

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

//	@SuppressWarnings("unchecked")
	protected void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("FROM Guestbook");
		List<Guestbook> guestbook = query.list();		
//		for(Guestbook item : guestbook ) {
//			System.out.println( item);
//		}		
		tx.commit();
		session.close();
		request.setAttribute("guestbook", guestbook);
		RequestDispatcher view = request.getRequestDispatcher("guestbook.jsp");
		view.forward(request, response);
	}

	public void insertGuestbook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();		
		Transaction tx = session.beginTransaction();
		Guestbook guestbook  = new Guestbook(name, message);
	    Integer guestbookId = (Integer) session.save(guestbook);
	    System.out.println("guestbookID = " + guestbookId );
	    tx.commit();
	    session.close();
	}

	public void deleteGuestbook(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
	    Guestbook guestbook = (Guestbook)session.get(Guestbook.class, id);
        session.delete(guestbook);
        tx.commit();
        session.close();
	}

	public void editGuestbook(HttpServletRequest request, HttpServletResponse response, int id)
			throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Guestbook where id = :guestbook_id");
		query.setParameter("guestbook_id", id);		
		Guestbook gb =  (Guestbook)query.uniqueResult();
        tx.commit();
        session.close();
		request.setAttribute("guestbook", gb);		
		RequestDispatcher view = request.getRequestDispatcher("editGuestbook.jsp");
		view.forward(request, response);
	}

	public void updateGuestbook(HttpServletRequest request, HttpServletResponse response, int id) {
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Guestbook gb = (Guestbook) session.get(Guestbook.class, id );
		gb.setName(name);
		gb.setMessage(message);
		session.update(gb);		
        tx.commit();
        session.close();
	}
}
