package exts.com.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import exts.com.service.ComSampleSourceService;
import exts.com.util.SampleSourceUtil;
import exts.com.vo.ComSmplVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import net.lingala.zip4j.exception.ZipException;

/**
 * @Class Name : ComSmplServiceImpl.java
 * @Description : 샘플 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("comSampleSourceService")
public class ComSampleSourceServiceImpl extends ExtsAbstractServiceImpl implements ComSampleSourceService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	
	/**
	 * 샘플 소스 생성 후 ZIP파일 경로 반환
	 */
	public String makeTmpFile(String path, String dbmsType, String inPkg, String inFileName, String inDescription, String tableName, String[] dbColumnArr, String[] dbColumnTypeArr, String[] dbColumnSizeArr, String[] dbColumnIsNotNullArr, String[] dbCommentArr) throws IOException, ZipException {
		return SampleSourceUtil.makeTmpFile(path, dbmsType, inPkg, inFileName, inDescription, tableName, dbColumnArr, dbColumnTypeArr, dbColumnSizeArr, dbColumnIsNotNullArr, dbCommentArr);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ComSmplVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ComSmplVO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(ComSmplVO searchVO){
		if(searchVO == null)return false;
		if(isAdmin())return true;
		
		return isDelAuth();
	}

	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역
	// ///////////////////////////////////////////////////////////////////
	/**
	 * DB에 입력하기 위한 데이터 처리.
	 * @param searchVO
	 * @throws EgovBizException
	 */
	private void validate(ComSmplVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
