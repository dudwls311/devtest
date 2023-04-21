package exts.com.service;

import exts.com.vo.ExtsAbstractVO;

public abstract interface ExtsAbstractService{

	/**
	 * 저장권한
	 * @return
	 */
	public boolean isStreAuth();
	/**
	 * 읽기권한
	 * @return
	 */
	public boolean isRedngAuth();
	/**
	 * 삭제권한
	 * @return
	 */
	public boolean isDelAuth();



	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ExtsAbstractVO searchVO);
	
	/**
	 * 기본 수정권한
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ExtsAbstractVO searchVO);
	
	/**
	 * 기본 삭제권한
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(ExtsAbstractVO searchVO);
}