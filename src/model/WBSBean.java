package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class WBSBean  implements Serializable {

	private static final long serialVersionUID = 1L ;

	//フィールドの宣言
	private int wbsId;
	private String wbsName;
	private int planId;
	private String planName;
	private int userId;
	private String userName;
	private String password;
	private Timestamp startPlanDay;
	private String startPlanDayStr;
	private Timestamp endPlanDay;
	private String endPlanDayStr;
	private int workPlanload;
	private int achievementId;
	private Timestamp startDay;
	private String startDayStr;
	private Timestamp endDay;
	private String endDayStr;
	private int workload;

	//WBSID
	public int getWbsId() {
		return wbsId;
	}
	public void setWbsId(int wbsId) {
		this.wbsId = wbsId;
	}

	public String getWbsName() {
		return wbsName;
	}
	public void setWbsName(String wbsName) {
		this.wbsName = wbsName;
	}
	//PLANID
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}

	//PlanName
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	//USER_ID
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	//USER_NAME
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	//STARTPlanDAY
	public Timestamp getStartPlanDay() {
		return startPlanDay;
	}
	public void setStartPlanDay(Timestamp startPlanDay) {
		this.startPlanDay = startPlanDay;
	}

	public String getStartPlanDayStr() {
		return startPlanDayStr;
	}
	public void setStartPlanDayStr(String startPlanDayStr) {
		this.startPlanDayStr = startPlanDayStr;
	}

	//ENDPlanDAY
	public Timestamp getEndPlanDay() {
		return endPlanDay;
	}
	public void setEndPlanDay(Timestamp endPlanDay) {
		this.endPlanDay = endPlanDay;
	}

	public String getEndPlanDayStr() {
		return endPlanDayStr;
	}
	public void setEndPlanDayStr(String endPlanDayStr) {
		this.endPlanDayStr = endPlanDayStr;
	}
	//WorkPlanload
	public int getWorkPlanload() {
		return workPlanload;
	}
	public void setWorkPlanload(int workPlanload) {
		this.workPlanload = workPlanload;
	}

	//ACHIEVEMENTID
	public int getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}

	//StartDay
	public Timestamp getStartDay() {
		return startDay;
	}
	public void setStartDay(Timestamp startDay) {
		this.startDay = startDay;
	}

	public String getStartDayStr() {
		return startDayStr;
	}
	public void setStartDayStr(String startDayStr) {
		this.startDayStr = startDayStr;
	}

	//EndDay
	public Timestamp getEndDay() {
		return endDay;
	}
	public void setEndDay(Timestamp endDay) {
		this.endDay = endDay;
	}

	public String getEndDayStr() {
		return endDayStr;
	}
	public void setEndDayStr(String endDayStr) {
		this.endDayStr = endDayStr;
	}
	//WorkLoad
	public int getWorkload() {
		return workload;
	}
	public void setWorkload(int workload) {
		this.workload = workload;
	}

}
