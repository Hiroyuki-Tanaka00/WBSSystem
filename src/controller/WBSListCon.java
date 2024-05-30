package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SystemDao;

/**
 * Servlet implementation class WBSListCon
 */
public class WBSListCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WBSListCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    // StoreDaoオブジェクトを作成
		SystemDao dao = new SystemDao();

		String userName = (String)request.getSession().getAttribute("userName");

		ArrayList<String> wbsNameList = dao.WBSSearch(userName);

		if(wbsNameList != null) {
			request.setAttribute("WBSNAMELIST", wbsNameList);
		}

	    RequestDispatcher rd = request.getRequestDispatcher("/LoginSuccess.jsp");
	    rd.forward(request, response);
	}

}
