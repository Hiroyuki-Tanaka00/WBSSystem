package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SystemDao;
import model.WBSBean;

/**
 * Servlet implementation class UserListCon
 */
public class UserListCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListCon() {
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
		request.setCharacterEncoding("UTF-8");

		String wbsName = request.getParameter("WBSNAME");

	    // StoreDaoオブジェクトを作成
		SystemDao dao = new SystemDao();

	    //リストを取得
	    List<WBSBean> userlist = dao.UserListDao();

	    //取得したリストをリクエストオブジェクトにセット
	    request.setAttribute("userlists", userlist);

	    request.setAttribute("WBSNAME", wbsName);

        //画面を表示する
        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/UserList_view.jsp");
        dispatcherlist.forward(request, response);
	}

}
