package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SystemDao {

	//データベース接続用変数
	Connection conn = null;

	//ステートメント実行用変数
	PreparedStatement stmt = null;

	//ユーザ名重複チェック
	public int userNameCheck(WBSBean usernameBean) {
		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		int num = 0;

		try {
			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");


			String sql = "SELECT COUNT(*) FROM user_info WHERE user_name = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, usernameBean.getUserName());

			//SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();
			num = rs.getInt(1);

			//リザルトセットを閉じる
			rs.close();

			// ステートメントをクローズ
			stmt.close();

		} catch (SQLException | ClassNotFoundException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		return num;
	}

	//新規ユーザ登録
	public int userCreateDao(WBSBean usercreateBean) {

		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		int num = 0;


		try {

			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			//自動コミットを無効化
			conn.setAutoCommit(false);

			String sql = "INSERT INTO USER_INFO (USER_ID, USER_NAME, USER_PASSWORD) VALUES (USER_SEQUENCE.nextval, ?, ?)";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, usercreateBean.getUserName());
			stmt.setString(2, usercreateBean.getPassword());

            // INSERT文の結果をnumに代入する
            num = stmt.executeUpdate();

			//ステートメントを閉じる
			stmt.close();

			//コミット
			conn.commit();

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}


		return num;

	}

	//WBS名重複チェック
	public int WbsNameCheck(WBSBean wbsnameBean) {
		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		//return用変数
		int num = 0;

		try {
			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			String sql = "SELECT COUNT(*) FROM wbs_info WHERE wbs_name = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, wbsnameBean.getWbsName());

			//SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();
			num = rs.getInt(1);

			//リザルトセットを閉じる
			rs.close();


		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		return num;
	}

	//タスク名重複チェック
	public int TaskNameCheck(WBSBean tasknameBean) {
		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		//return用変数
		int num = 0;

		try {
			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			String sql = "SELECT WBS_ID FROM WBS_INFO WHERE WBS_NAME = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, tasknameBean.getWbsName());

			//SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();
			int wbsId = rs.getInt(1);

			//プレースホルダーでSQL作成
			sql = "SELECT COUNT(*) FROM plan_info WHERE plan_name = ? AND WBS_ID = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, tasknameBean.getPlanName());
			stmt.setInt(2, wbsId);

			//SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();
			num = rs.getInt(1);

			//リザルトセットを閉じる
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		return num;

	}

	//WBS新規作成メソッド
	public int WBSCreateDao(WBSBean WBScreareBean) {

		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		int num = 0;

		try {

			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			//自動コミットを無効化
			conn.setAutoCommit(false);

			String sql = "INSERT INTO WBS_INFO (WBS_ID, WBS_NAME) VALUES (WBS_SEQUENCE.nextval, ?)";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, WBScreareBean.getWbsName());

            // INSERT文の結果をnumに代入する
            num = stmt.executeUpdate();

			//ステートメントを閉じる
			stmt.close();

			//コミット
			conn.commit();


		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		return num;
	}

	//ユーザ一覧取得メソッド
	public ArrayList<WBSBean> UserListDao(){
		//インスタンスを生成
		ArrayList<WBSBean> userlistbean = new ArrayList<WBSBean>();

		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		try {
			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			String sql = "SELECT USER_ID, USER_NAME FROM USER_INFO ORDER BY USER_ID";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//SQLの実行
			rs = stmt.executeQuery();



			//書き込み
			while(rs.next()) {
				WBSBean userlist = new WBSBean();
				userlist.setUserId(rs.getInt("USER_ID"));
				userlist.setUserName(rs.getString("USER_NAME"));
				userlistbean.add(userlist);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		return userlistbean;

	}

	//タスク新規作成メソッド
	public int CreateDao(WBSBean creareBean) {

		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		int num = 0;


		try {
			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			String sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, creareBean.getUserName());

			//SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();
			int userID = rs.getInt(1);

			sql = "SELECT WBS_ID FROM WBS_INFO WHERE WBS_NAME = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, creareBean.getWbsName());

			//SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();
			int wbsId = rs.getInt(1);

			//自動コミットを無効化
			conn.setAutoCommit(false);

			sql = "INSERT INTO PLAN_INFO (PLAN_ID, WBS_ID, USER_ID, PLAN_NAME, START_PLAN_DAY, END_PLAN_DAY, WORK_PLAN_LOAD) VALUES (PLAN_SEQUENCE.nextval, ?, ?, ?, ?, ?, ?)";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setInt(1, wbsId);
			stmt.setInt(2, userID);
			stmt.setString(3, creareBean.getPlanName());
			stmt.setTimestamp(4, creareBean.getStartPlanDay());
			stmt.setTimestamp(5, creareBean.getEndPlanDay());
			stmt.setInt(6, creareBean.getWorkPlanload());

            // INSERT文の結果をnumに代入する
            num = stmt.executeUpdate();

			//ステートメントを閉じる
			stmt.close();

			//コミット
			conn.commit();


		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}





		return num;
	}


	//タスク更新メソッド
	public int updateDao(WBSBean updateBean) {

		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		int num = 0;

		int planId = updateBean.getPlanId();


		try {

			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");


			String sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, updateBean.getUserName());

			//SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();
			int userID = rs.getInt(1);

			// プレースホルダでSQL作成
			sql = "UPDATE PLAN_INFO SET USER_ID = ?,  PLAN_NAME = ?, START_PLAN_DAY = ?, END_PLAN_DAY = ? WHERE PLAN_ID = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setInt(1, userID);
			stmt.setString(2, updateBean.getPlanName());
			stmt.setTimestamp(3, updateBean.getStartPlanDay());
			stmt.setTimestamp(4, updateBean.getEndPlanDay());
			stmt.setInt(5, planId);

			// SQLの実行
			num = stmt.executeUpdate();

			//StartDayとEndDayに値が入っていれば
			if(updateBean.getStartDay() != null && updateBean.getEndDay() != null) {
				//自動コミットを無効化
				conn.setAutoCommit(false);

				//取得したworkloadが0じゃなければ
				if(updateBean.getWorkload() != 0) {
					sql = "MERGE INTO ACHIEVEMENT_INFO AI USING (SELECT PLAN_ID FROM PLAN_INFO WHERE PLAN_ID = ?) PI ON (AI.PLAN_ID = PI.PLAN_ID) WHEN MATCHED THEN UPDATE SET AI.START_DAY = ?, AI.END_DAY = ?, AI.WORK_LOAD = ? WHEN NOT MATCHED THEN INSERT (ACHIEVEMENT_ID, PLAN_ID, START_DAY, END_DAY, WORK_LOAD) VALUES (?, ?, ?, ?, ?)";

					//SQLをプリコンパイル
					stmt = conn.prepareStatement(sql);

					//パラメータセット
					stmt.setInt(1, planId);
					stmt.setTimestamp(2, updateBean.getStartDay());
					stmt.setTimestamp(3, updateBean.getEndDay());
					stmt.setInt(4, updateBean.getWorkload());
					stmt.setInt(5, planId);
					stmt.setInt(6, planId);
					stmt.setTimestamp(7, updateBean.getStartDay());
					stmt.setTimestamp(8, updateBean.getEndDay());
					stmt.setInt(9, updateBean.getWorkload());

					// SQLの実行
					num = stmt.executeUpdate();

					//コミット
					conn.commit();
				}else {
					//自動コミットを無効化
					conn.setAutoCommit(false);

					sql = "MERGE INTO ACHIEVEMENT_INFO AI USING (SELECT PLAN_ID FROM PLAN_INFO WHERE PLAN_ID = ?) PI ON (AI.PLAN_ID = PI.PLAN_ID) WHEN MATCHED THEN UPDATE SET AI.START_DAY = ?, AI.END_DAY = ? WHEN NOT MATCHED THEN INSERT (ACHIEVEMENT_ID, PLAN_ID, START_DAY, END_DAY) VALUES (?, ?, ?, ?)";

					//SQLをプリコンパイル
					stmt = conn.prepareStatement(sql);

					//パラメータセット
					stmt.setInt(1, planId);
					stmt.setTimestamp(2, updateBean.getStartDay());
					stmt.setTimestamp(3, updateBean.getEndDay());
					stmt.setInt(4, planId);
					stmt.setInt(5, planId);
					stmt.setTimestamp(6, updateBean.getStartDay());
					stmt.setTimestamp(7, updateBean.getEndDay());

					// SQLの実行
					num = stmt.executeUpdate();

					//コミット
					conn.commit();
				}



			//EndDayに値が入っていなければ
			}else if(updateBean.getEndDay() == null && updateBean.getStartDay() == null){

				//取得したworkloadが0じゃなければ
				if(updateBean.getWorkload() != 0) {

					//自動コミットを無効化
					conn.setAutoCommit(false);

					sql = "MERGE INTO ACHIEVEMENT_INFO AI USING (SELECT PLAN_ID FROM PLAN_INFO WHERE PLAN_ID = ?) PI ON (AI.PLAN_ID = PI.PLAN_ID) WHEN MATCHED THEN UPDATE SET AI.WORK_LOAD = ? WHEN NOT MATCHED THEN INSERT (ACHIEVEMENT_ID, PLAN_ID, WORK_LOAD) VALUES (?, ?, ?)";

					//SQLをプリコンパイル
					stmt = conn.prepareStatement(sql);

					//パラメータセット
					stmt.setInt(1, planId);;
					stmt.setInt(2, updateBean.getWorkload());
					stmt.setInt(3, planId);
					stmt.setInt(4, planId);
					stmt.setInt(5, updateBean.getWorkload());

					// SQLの実行
					num = stmt.executeUpdate();

					//コミット
					conn.commit();

					return num;
				}else {
					return num;
				}
			}else if (updateBean.getEndDay() == null){
				//自動コミットを無効化
				conn.setAutoCommit(false);

				//取得したworkloadが0じゃなければ
				if(updateBean.getWorkload() != 0) {
					sql = "MERGE INTO ACHIEVEMENT_INFO AI USING (SELECT PLAN_ID FROM PLAN_INFO WHERE PLAN_ID = ?) PI ON (AI.PLAN_ID = PI.PLAN_ID) WHEN MATCHED THEN UPDATE SET AI.START_DAY = ?, AI.WORK_LOAD = ?  WHEN NOT MATCHED THEN INSERT (ACHIEVEMENT_ID, PLAN_ID, START_DAY, WORK_LOAD) VALUES (?, ?, ?, ?)";

					//SQLをプリコンパイル
					stmt = conn.prepareStatement(sql);

					//パラメータセット
					stmt.setInt(1, planId);
					stmt.setTimestamp(2, updateBean.getStartDay());
					stmt.setInt(3, updateBean.getWorkload());
					stmt.setInt(3, planId);
					stmt.setInt(4, planId);
					stmt.setTimestamp(5, updateBean.getStartDay());
					stmt.setInt(6, updateBean.getWorkload());

					// SQLの実行
					num = stmt.executeUpdate();

					//コミット
					conn.commit();
				}else {

					sql = "MERGE INTO ACHIEVEMENT_INFO AI USING (SELECT PLAN_ID FROM PLAN_INFO WHERE PLAN_ID = ?) PI ON (AI.PLAN_ID = PI.PLAN_ID) WHEN MATCHED THEN UPDATE SET AI.START_DAY = ? WHEN NOT MATCHED THEN INSERT (ACHIEVEMENT_ID, PLAN_ID, START_DAY) VALUES (?, ?, ?)";

					//SQLをプリコンパイル
					stmt = conn.prepareStatement(sql);

					//パラメータセット
					stmt.setInt(1, planId);
					stmt.setTimestamp(2, updateBean.getStartDay());
					stmt.setInt(3, planId);
					stmt.setInt(4, planId);
					stmt.setTimestamp(5, updateBean.getStartDay());

					// SQLの実行
					num = stmt.executeUpdate();

					//コミット
					conn.commit();
				}


			}
			// ステートメントをクローズ
			stmt.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		return num;
	}

	//ユーザ名チェックメソッド
	public int nameCheckDao(WBSBean nameBean) {
		//SELECTしたdataを格納する変数宣言
		ResultSet rs = null;

		//return用変数
		int num = 0;


		try {
			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			//プレースホルダーでSQL作成
			String sql = "SELECT COUNT(*) FROM user_info WHERE user_name = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, nameBean.getUserName());

			//SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();
			num = rs.getInt(1);

			//リザルトセットを閉じる
			rs.close();


		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}



		return num;

	}


	//タスク情報検索メソッド
	public WBSBean selectTaskDao(int planId) {
		WBSBean beforeTaskbean = new WBSBean();

		// SimpleDateFormatオブジェクトを生成し、変換するフォーマットを指定する
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// SELECTしたデータを格納する変数宣言
		ResultSet rs = null;

		int count = 0;

		try {

			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			//WBS_IDを検索
			String sql = "SELECT PLAN_ID FROM PLAN_INFO WHERE PLAN_ID = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setInt(1, planId);

			// SQLの実行
			rs = stmt.executeQuery();

			//結果を取得
			rs.next();

			//WBS_IDを検索
			sql = "SELECT WBS_ID FROM PLAN_INFO WHERE PLAN_ID = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setInt(1, planId);

			// SQLの実行
			rs = stmt.executeQuery();

			//結果を取得

			rs.next();
			int wbsId = rs.getInt(1);

			//achievement_infoテーブルからタスクIDで検索を行う
			sql = "SELECT COUNT(*) as count FROM achievement_info WHERE PLAN_ID = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

		    stmt.setInt(1, planId);
		    rs = stmt.executeQuery();

		    if (rs.next()) {
		    	count = rs.getInt("count");
		    }

	    	// achievement_infoテーブルにデータが存在する場合
		    if (count > 0) {
		    	sql = "SELECT pi.*, ui.USER_NAME, ai.START_DAY, ai.END_DAY, ai.WORK_LOAD FROM plan_info pi INNER JOIN user_info ui ON pi.USER_ID = ui.USER_ID INNER JOIN achievement_info ai ON pi.PLAN_ID = ai.PLAN_ID WHERE pi.WBS_ID = ? AND pi.PLAN_ID = ?";

				//SQLをプリコンパイル
				stmt = conn.prepareStatement(sql);

				//パラメータセット
				stmt.setInt(1, wbsId);
				stmt.setInt(2, planId);

				// SQLの実行
				rs = stmt.executeQuery();


				//結果を取得
				rs.next();

				//beforeTaskbeanに値を代入
				beforeTaskbean.setPlanId(planId);
				beforeTaskbean.setPlanName(rs.getString("PLAN_NAME"));
				beforeTaskbean.setUserName(rs.getString("USER_NAME"));
				beforeTaskbean.setStartPlanDayStr(dateFormat.format(rs.getTimestamp("START_PLAN_DAY")));
				beforeTaskbean.setEndPlanDayStr(dateFormat.format(rs.getTimestamp("END_PLAN_DAY")));
				beforeTaskbean.setStartDayStr(dateFormat.format(rs.getTimestamp("START_DAY")));
				if (rs.getTimestamp("END_DAY") != null) {
					beforeTaskbean.setEndDayStr(dateFormat.format(rs.getTimestamp("END_DAY")));
				}
				if(rs.getInt("WORK_LOAD") != 0) {
					beforeTaskbean.setWorkload(Integer.parseInt(rs.getString("WORK_LOAD")));
				}

		    }

		    else {
				//プレースホルダーでSQL作成
				sql ="SELECT pi.*, ui.USER_NAME FROM plan_info pi INNER JOIN user_info ui ON pi.USER_ID = ui.USER_ID WHERE pi.PLAN_ID = ?";

				//SQLをプリコンパイル
				stmt = conn.prepareStatement(sql);

				// パラメーターセット
				stmt.setInt(1, planId);

				// SQLの実行
				rs = stmt.executeQuery();

				//結果を取得
				rs.next();

				//beforeTaskbeanに値を代入
				beforeTaskbean.setPlanId(planId);
				beforeTaskbean.setPlanName(rs.getString("PLAN_NAME"));
				beforeTaskbean.setUserName(rs.getString("USER_NAME"));
				beforeTaskbean.setStartPlanDayStr(dateFormat.format(rs.getTimestamp("START_PLAN_DAY")));
				beforeTaskbean.setEndPlanDayStr(dateFormat.format(rs.getTimestamp("END_PLAN_DAY")));
		    }


		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}


		return beforeTaskbean;
	}

	//WBS詳細メソッド
	public ArrayList<WBSBean> WBSDetail(String wbsName) {
		//インスタンスを生成
		ArrayList<WBSBean> detailbean = new ArrayList<WBSBean>();

		int wbsId;

		// SELECTしたデータを格納する変数宣言
		ResultSet rs = null;

		try {

			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");


			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			//WBS_IDを検索
			String sql = "SELECT WBS_ID FROM WBS_INFO WHERE WBS_NAME = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, wbsName);

			// SQLの実行
			rs = stmt.executeQuery();

			//結果を取得

			rs.next();
			wbsId = rs.getInt(1);

			//WBS_IDからテーブルDATAを取得
			sql = "SELECT pi.*, ui.USER_NAME, ai.START_DAY, ai.END_DAY, ai.WORK_LOAD FROM plan_info pi INNER JOIN user_info ui ON pi.USER_ID = ui.USER_ID LEFT OUTER JOIN achievement_info ai ON pi.PLAN_ID = ai.PLAN_ID WHERE pi.WBS_ID = ? ORDER BY pi.START_PLAN_DAY";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setInt(1, wbsId);

			// SQLの実行
			rs = stmt.executeQuery();

			//書き込み
			while(rs.next()) {
				WBSBean detail = new WBSBean();
				detail.setWbsId(rs.getInt("WBS_ID"));
				detail.setPlanId(rs.getInt("PLAN_ID"));
				detail.setUserId(rs.getInt("USER_ID"));
				detail.setStartPlanDay(rs.getTimestamp("START_PLAN_DAY"));
				if (rs.getTimestamp("START_DAY") != null) {
					detail.setStartDay(rs.getTimestamp("START_DAY"));
				}
				detail.setEndPlanDay(rs.getTimestamp("END_PLAN_DAY"));
				if (rs.getTimestamp("END_DAY") != null) {
					detail.setEndDay(rs.getTimestamp("END_DAY"));
				}
				detail.setWorkPlanload(rs.getInt("WORK_PLAN_LOAD"));
				if(rs.getInt("WORK_LOAD") != 0) {
					detail.setWorkload(rs.getInt("WORK_LOAD"));
				}
				detail.setPlanName(rs.getString("PLAN_NAME"));
				detail.setUserName(rs.getString("USER_NAME"));
				detailbean.add(detail);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		return detailbean;

	}

	//WBS名検索メソッド
	public ArrayList<String> WBSSearch(String userName) {
		int userId;
		ArrayList<Integer> wbsIdlist = new ArrayList<>();
		ArrayList<String> wbsNamelist = new ArrayList<>();
		String wbsName = null;

		// SELECTしたデータを格納する変数宣言
		ResultSet rs = null;


		try {
			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			//USER_INFOテーブルからuser_idを取得
			String sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, userName);

			// SQLの実行
			rs = stmt.executeQuery();

			//結果を取得

			rs.next();
			userId = rs.getInt(1);

			if(userName.equals("admin")) {
				sql = "SELECT WBS_ID FROM wbs_info";

				//SQLをプリコンパイル
				stmt = conn.prepareStatement(sql);

				// SQLの実行
				rs = stmt.executeQuery();

				while (rs.next()) {
					wbsIdlist.add(rs.getInt(1));
				}

				for (int wbsId : wbsIdlist) {
				    // WBS_INFOからWBS_NAMEを取得する
				    sql = "SELECT WBS_NAME FROM WBS_INFO WHERE WBS_ID = ?";
				    //SQLをプリコンパイル
				    stmt = conn.prepareStatement(sql);
				    //パラメータセット
				    stmt.setInt(1, wbsId);
				    // SQLの実行
				    rs = stmt.executeQuery();
				    rs.next();
				    wbsNamelist.add(rs.getString(1));
				}

			}else {
	            // PLAN_INFOテーブルからWBS_IDを取得する
				sql = "SELECT DISTINCT WBS_ID FROM PLAN_INFO WHERE USER_ID = ?";

				//SQLをプリコンパイル
				stmt = conn.prepareStatement(sql);

				//パラメータセット
				stmt.setInt(1, userId);

				// SQLの実行
				rs = stmt.executeQuery();

				while (rs.next()) {
					wbsIdlist.add(rs.getInt(1));
				}


				for (int wbsId : wbsIdlist) {
				    // WBS_INFOからWBS_NAMEを取得する
				    sql = "SELECT WBS_NAME FROM WBS_INFO WHERE WBS_ID = ?";
				    //SQLをプリコンパイル
				    stmt = conn.prepareStatement(sql);
				    //パラメータセット
				    stmt.setInt(1, wbsId);
				    // SQLの実行
				    rs = stmt.executeQuery();
				    rs.next();
				    wbsNamelist.add(rs.getString(1));
				}
			}



		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		return wbsNamelist;
	}

	//ログインチェックメソッド
	public int loginCheckDao(String userName , String Password) {

		//returnする変数を宣言 データベースになければ0で返す
		int num = 0;

		// SELECTしたデータを格納する変数宣言
		ResultSet rs = null;

		try {

			//OracleJDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");


			//DBに接続(URL,USER_ID,PASSWARD
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "WBS","hiroyuki990809");

			// プレースホルダでSQL文を作成
			String sql = "SELECT * FROM USER_INFO WHERE USER_NAME = ? AND USER_PASSWORD = ?";

			//SQLをプリコンパイル
			stmt = conn.prepareStatement(sql);

			//パラメータセット
			stmt.setString(1, userName);
			stmt.setString(2, Password);

			// SQLの実行
			rs = stmt.executeQuery();

			// 結果を取得
			rs.next();
			num = rs.getInt(1);

		} catch (SQLException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		} catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}finally {
		}try {
			//ステートメント実行を行ってた場合
			if(stmt != null) {
				//ステートメントを閉じる
				stmt.close();
			}if (conn != null) {
				//データベース接続を閉じる
				conn.close();
			}
		}catch (SQLException e) {
			//例外が発生した場合はエラーメッセージを出力
			e.printStackTrace();
		}

		// 実行結果をリターン
		return num;
	}

}
