/**
 * @version 3.2.0.1
 */
package jnit.board.info.statistic;

import java.util.List;

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
public class JnitboardinfoStatisticVO extends JnitboardinfoDefaultVO{
    private static final long serialVersionUID = 1L;
    
    private String boardId;
    private String boardTitle;
    private String boardGroup;
    private String totalCount;
    private String totalView;
    private List<String> boardIds;
    
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardGroup() {
		return boardGroup;
	}
	public void setBoardGroup(String boardGroup) {
		this.boardGroup = boardGroup;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getTotalView() {
		return totalView;
	}
	public void setTotalView(String totalView) {
		this.totalView = totalView;
	}
	public List<String> getBoardIds() {
		return boardIds;
	}
	public void setBoardIds(List<String> boardIds) {
		this.boardIds = boardIds;
	}
}
