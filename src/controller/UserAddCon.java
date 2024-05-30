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
 * Servlet implementation class UserAddCon
 */
public class UserAddCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddCon() {
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

		int num = 0;

	    // StoreDaoオブジェクトを作成
		SystemDao dao = new SystemDao();

		//値の取得
		String userName = request.getParameter("userNameInput");
		String password = request.getParameter("passwordInput");
		String password1 = request.getParameter("passwordInput1");

		if(password1.equals(password)) {
			WBSBean userAddBean = new WBSBean();
			userAddBean.setUserName(userName);
			userAddBean.setPassword(password);

			if(password.contains(" ") || password.contains("　")) {
				request.setAttribute("useraddFailure", "ユーザ作成に失敗しました。半角、または全角スペースが含まれています。");

		        //画面を表示する
		        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/UserAdd.jsp");
		        dispatcherlist.forward(request, response);
			}else {
				int namecheck = dao.userNameCheck(userAddBean);

				if(namecheck == 1) {
					request.setAttribute("useraddFailure", "ユーザ作成に失敗しました。同じユーザ名が存在しています。");

			        //画面を表示する
			        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/UserAdd.jsp");
			        dispatcherlist.forward(request, response);
				}else {
					num = dao.userCreateDao(userAddBean);

					request.setAttribute("useraddSuccess", "ユーザの新規作成に成功しました。");

			        // 結果をリクエストオブジェクトにセット
			        request.setAttribute("result", num);


				    //リストを取得
				    List<WBSBean> userlist = dao.UserListDao();

				    //取得したリストをリクエストオブジェクトにセット
				    request.setAttribute("userlists", userlist);


			        //画面を表示する
			        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/UserList_view.jsp");
			        dispatcherlist.forward(request, response);
				}


			}

		}else {
			request.setAttribute("useraddFailure", "ユーザ作成に失敗しました。パスワードが違います。");

	        //画面を表示する
	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/UserAdd.jsp");
	        dispatcherlist.forward(request, response);
		}




	}

}
