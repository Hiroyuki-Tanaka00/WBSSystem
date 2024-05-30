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
 * Servlet implementation class infoUpdateCon
 */
public class infoUpdateCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public infoUpdateCon() {
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
		request.setCharacterEncoding("UTF-8");


		//インスタンス生成
		SystemDao dao = new SystemDao();

		String wbsName = request.getParameter("WBSNAME");


		Timestamp startDay = null;

		Timestamp endDay = null;

		int num = 0;

		//更新ボタンをクリックしたときの処理
		if (request.getParameter("planName") != null) {

			//値の取得
			String planIdstr = request.getParameter("planId");
			String planName = request.getParameter("planName");
			String userName = request.getParameter("userName");
			String startPlanDayStr = request.getParameter("startPlanDay");
			String endPlanDayStr = request.getParameter("endPlanDay");
			String startDayStr = request.getParameter("startDay");
			String endDayStr = request.getParameter("endDay");
			String workLoadStr = request.getParameter("workLoad");


			try {
				int planId = Integer.parseInt(planIdstr);
				Timestamp startPlanDay = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(startPlanDayStr).getTime());
				Timestamp endPlanDay = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(endPlanDayStr).getTime());
				if(startDayStr != null  && !startDayStr.isEmpty()) {
					startDay = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(startDayStr).getTime());
				}
				if (endDayStr != null && !endDayStr.isEmpty()) {
					endDay = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(endDayStr).getTime());
				}
				int workLoad = Integer.parseInt(workLoadStr);


				//インスタンス化
				WBSBean PlanBean = new WBSBean();
				PlanBean.setPlanId(planId);
				PlanBean.setPlanName(planName);
				PlanBean.setUserName(userName);
				PlanBean.setStartPlanDay(startPlanDay);
				PlanBean.setEndPlanDay(endPlanDay);
				if(startDay != null) {
					PlanBean.setStartDay(startDay);
				}
				if(endDay != null) {
					PlanBean.setEndDay(endDay);
				}
				if(workLoad != 0) {
					PlanBean.setWorkload(workLoad);
				}

				int nameCheck = dao.nameCheckDao(PlanBean);

				if(nameCheck == 1) {
					//StartDayとENDDayにDATAが入っていた場合
					if(startDay != null && endDay != null) {
						//endPlanDayがstartPlanDayよりも前だった場合
						if(endPlanDay.before(startPlanDay)) {
			                // ログイン失敗の文言を追加する
			                request.setAttribute("updateFailure", "アップデートに失敗しました。終了日予定日が開始予定日よりも前です。");

			    			wbsName = request.getParameter("WBSNAME");

			    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
			    			WBSBean beforeTaskbean = dao.selectTaskDao(planId);

			    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
			    			request.setAttribute("beforeItemBean", beforeTaskbean);

			    		    request.setAttribute("WBSNAME", wbsName);

			    	        //画面を表示する
			    	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/TaskChage.jsp");
			    	        dispatcherlist.forward(request, response);

			    	        //endDayがstartDayよりも前だった場合
							}else if(endDay.before(startDay)) {
			                // ログイン失敗の文言を追加する
			                request.setAttribute("updateFailure", "アップデートに失敗しました。終了日が開始日よりも前です。");

			    			wbsName = request.getParameter("WBSNAME");

			    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
			    			WBSBean beforeTaskbean = dao.selectTaskDao(planId);

			    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
			    			request.setAttribute("beforeItemBean", beforeTaskbean);

			    		    request.setAttribute("WBSNAME", wbsName);

			    	        //画面を表示する
			    	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/TaskChage.jsp");
			    	        dispatcherlist.forward(request, response);
						}else {
			    			num = dao.updateDao(PlanBean);
			    			request.setAttribute("setSuccess", "アップデートに成功しました。");
						}
					//Enddayがnullだった場合
					}else if(endDay == null) {
						//endPlanDayがstartPlanDayよりも前だった場合
						if(endPlanDay.before(startPlanDay)) {
			                // ログイン失敗の文言を追加する
			                request.setAttribute("updateFailure", "アップデートに失敗しました。終了日予定日が開始予定日よりも前です。");

			    			wbsName = request.getParameter("WBSNAME");

			    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
			    			WBSBean beforeTaskbean = dao.selectTaskDao(planId);

			    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
			    			request.setAttribute("beforeItemBean", beforeTaskbean);

			    		    request.setAttribute("WBSNAME", wbsName);

			    	        //画面を表示する
			    	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/TaskChage.jsp");
			    	        dispatcherlist.forward(request, response);

			    	        //endDayがstartDayよりも前だった場合
							}else {
				    			num = dao.updateDao(PlanBean);
				    			request.setAttribute("setSuccess", "アップデートに成功しました。");
							}
						//endDayがnullじゃなく、startdayがnullだった場合

					}else if(endDay != null && startDay == null) {
		    			System.out.println("更新に失敗しました");
		                // ログイン失敗の文言を追加する
		                request.setAttribute("updateFailure", "アップデートに失敗しました。開始日が入力されていません。");

		    			wbsName = request.getParameter("WBSNAME");

		    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
		    			WBSBean beforeTaskbean = dao.selectTaskDao(planId);

		    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
		    			request.setAttribute("beforeItemBean", beforeTaskbean);

		    		    request.setAttribute("WBSNAME", wbsName);

		    	        //画面を表示する
		    	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/TaskChage.jsp");
		    	        dispatcherlist.forward(request, response);
					}
				}else {
	    			System.out.println("更新に失敗しました");
	                // ログイン失敗の文言を追加する
	                request.setAttribute("updateFailure", "アップデートに失敗しました。該当するユーザが見つかりませんでした。");

	    			wbsName = request.getParameter("WBSNAME");

	    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
	    			WBSBean beforeTaskbean = dao.selectTaskDao(planId);

	    		    // 商品情報を検索して、ItemBeanオブジェクトに格納
	    			request.setAttribute("beforeItemBean", beforeTaskbean);

	    		    request.setAttribute("WBSNAME", wbsName);

	    	        //画面を表示する
	    	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/TaskChage.jsp");
	    	        dispatcherlist.forward(request, response);
				}


			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		    //リストを取得
		    List<WBSBean> detaillist = dao.WBSDetail(wbsName);

		    //取得したリストをリクエストオブジェクトにセット
		    request.setAttribute("detaillists", detaillist);

		    request.setAttribute("WBSNAME", wbsName);

	        //画面を表示する
	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/WBS_view.jsp");
	        dispatcherlist.forward(request, response);

		}else {
			String planIdStr = request.getParameter("planId");

			wbsName = request.getParameter("WBSNAME");

			//値の型が変更必要な物は変換
			int planId = Integer.parseInt(planIdStr);

		    // 商品情報を検索して、ItemBeanオブジェクトに格納
			WBSBean beforeTaskbean = dao.selectTaskDao(planId);

		    // 商品情報を検索して、ItemBeanオブジェクトに格納
			request.setAttribute("beforeItemBean", beforeTaskbean);

		    request.setAttribute("WBSNAME", wbsName);

	        //画面を表示する
	        RequestDispatcher dispatcherlist = request.getRequestDispatcher("/TaskChage.jsp");
	        dispatcherlist.forward(request, response);
		}


	}

}
