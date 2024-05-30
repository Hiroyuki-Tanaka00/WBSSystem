package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SystemDao;

/**
 * Servlet implementation class LoginCon
 */
public class LoginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**8
     * @see HttpServlet#HttpServlet()
     */
    public LoginCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String userName = request.getParameter("userName");
		String Password = request.getParameter("Password");

		// StoreDaoクラスのインスタンス化
		SystemDao dao = new SystemDao();

		// loginCheckDaoメソッドの呼び出し
		int num = dao.loginCheckDao(userName, Password);

		if(num != 0) {
		    // セッションオブジェクトの取得
		    HttpSession session = request.getSession();
		    // セッションオブジェクトにuserNameを保存
		    session.setAttribute("userName", userName);

		    request.setAttribute("userName", userName);
		    // ログイン成功画面へフォワード

			ArrayList<String> wbsNameList = dao.WBSSearch(userName);

			if(wbsNameList != null) {
				request.setAttribute("WBSNAMELIST", wbsNameList);
			}

		    RequestDispatcher rd = request.getRequestDispatcher("/LoginSuccess.jsp");
		    rd.forward(request, response);
		}else {
            // ログイン失敗の文言を追加する
            request.setAttribute("loginFailure", "ログインに失敗しました");
		    RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
		    rd.forward(request, response);
		}
	}

}
