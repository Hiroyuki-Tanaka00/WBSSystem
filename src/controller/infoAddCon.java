package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SystemDao;
import model.WBSBean;

/**
 * Servlet implementation class infoAddCon
 */
public class infoAddCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public infoAddCon() {
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
		String wbsName = request.getParameter("WBSNAME");
		String planName = request.getParameter("planName");
		String userName = request.getParameter("userName");
		String startPlanDayStr = request.getParameter("startPlanDay");
		String endPlanDayStr = request.getParameter("endPlanDay");
		String workPlanLoadStr = request.getParameter("workPlanLoad");

		//値の型が変更必要な物は変換
		try {
			Timestamp startPlanDay = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(startPlanDayStr).getTime());
			Timestamp endPlanDay = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(endPlanDayStr).getTime());
			int workPlanLoad = Integer.parseInt(workPlanLoadStr);

			//インスタンス生成
			WBSBean AddBean = new WBSBean();
			AddBean.setWbsName(wbsName);
			AddBean.setPlanName(planName);
			AddBean.setUserName(userName);
			AddBean.setStartPlanDay(startPlanDay);
			AddBean.setEndPlanDay(endPlanDay);
			AddBean.setWorkPlanload(workPlanLoad);

			int nameCheck = dao.nameCheckDao(AddBean);

			if(nameCheck == 1) {
				if(endPlanDay.before(startPlanDay)) {
	                // ログイン失敗の文言を追加する
	                request.setAttribute("addFailure", "新規作成に失敗しました。終了日予定日が開始予定日よりも前です。");

	    	        //画面を表示する
	    	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/TaskAdd.jsp");
	    	        dispatcherlist.forward(request, response);
				}else {
					num = dao.CreateDao(AddBean);
	    			request.setAttribute("setSuccess", "新規作成に成功しました。");

	    	        // 結果をリクエストオブジェクトにセット
	    	        request.setAttribute("result", num);

	    		    //リストを取得
	    		    List<WBSBean> detaillist = dao.WBSDetail(wbsName);

	    		    //取得したリストをリクエストオブジェクトにセット
	    		    request.setAttribute("detaillists", detaillist);

	    		    request.setAttribute("WBSNAME", wbsName);

	    	        //画面を表示する
	    	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/WBS_view.jsp");
	    	        dispatcherlist.forward(request, response);
				}
			}else {
                // ログイン失敗の文言を追加する
                request.setAttribute("addFailure", "新規作成に失敗しました。該当するユーザが見つかりませんでした。");

    	        //画面を表示する
    	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/TaskAdd.jsp");
    	        dispatcherlist.forward(request, response);
			}





		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//int specialItem = Integer.parseInt(specialItemStr);

		// ItemBeanのインスタンス化と値の設定
		//ItemBean regiBean = new ItemBean();
		//regiBean.setItemName(itemName);
		//regiBean.setItemPrice(itemPrice);
		//regiBean.setStockCount(stockCount);
		//regiBean.setItemImage(itemImage);
		//regiBean.setSpecialItem(specialItem);

		//System.out.println(itemName);
		//System.out.println(itemPrice);
		//System.out.println(stockCount);
		//System.out.println(itemImage);
		//System.out.println(specialItem);



	}

}
