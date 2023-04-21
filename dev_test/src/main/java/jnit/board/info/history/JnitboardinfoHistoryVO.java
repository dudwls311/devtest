/**
 * @version 3.2.0.1
 */
package jnit.board.info.history;

import java.util.Date;

import jnit.board.info.JnitboardinfoDefaultVO;

/**
 * @Class Name : JnitboardinfoVO.java
 * @Description : Jnitboardinfo VO class
 * @Modification Information
 *
 * @author Dael @ JNIT
 * @since 2013.01.21
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class JnitboardinfoHistoryVO extends JnitboardinfoDefaultVO{
    private static final long serialVersionUID = 1L;
    
    private String boardId;
	private String boardGroup;
	private String boardTitle;
	private String mbrId;
	private String action;
	private Date actionDate;
	private String actionIp;
	
	private String mbrLogin;
	private String siteNm;
	
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardGroup() {
		return boardGroup;
	}
	public void setBoardGroup(String boardGroup) {
		this.boardGroup = boardGroup;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public String getActionIp() {
		return actionIp;
	}
	public void setActionIp(String actionIp) {
		this.actionIp = actionIp;
	}
	public String getMbrLogin() {
		return mbrLogin;
	}
	public void setMbrLogin(String mbrLogin) {
		this.mbrLogin = mbrLogin;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
}
