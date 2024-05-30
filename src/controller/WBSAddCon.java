package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SystemDao;
import model.WBSBean;

/**
 * Servlet implementation class WBSAddCon
 */
public class WBSAddCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WBSAddCon() {
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
		String wbsName = request.getParameter("wbsNameInput");

		String userName = (String)request.getSession().getAttribute("userName");

		//インスタンス生成
		WBSBean AddBean = new WBSBean();
		AddBean.setWbsName(wbsName);

		int wbsnameCheck = dao.WbsNameCheck(AddBean);

		if(wbsnameCheck == 1) {
            // ログイン失敗の文言を追加する
            request.setAttribute("wbsaddFailure", "新規作成に失敗しました。プロジェクト名が重複しています");

	        //画面を表示する
	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/WBSAdd.jsp");
	        dispatcherlist.forward(request, response);

		}else {
			num = dao.WBSCreateDao(AddBean);
			request.setAttribute("wbsaddSuccess", "新規作成に成功しました");

	        // 結果をリクエストオブジェクトにセット
	        request.setAttribute("result", num);

			ArrayList<String> wbsNameList = dao.WBSSearch(userName);

			if(wbsNameList != null) {
				request.setAttribute("WBSNAMELIST", wbsNameList);
			}

		    RequestDispatcher rd = request.getRequestDispatcher("/LoginSuccess.jsp");
		    rd.forward(request, response);
		}





	}

}
