package [PACKAGE].service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.service.impl.ExtsAbstractServiceImpl;
import [PACKAGE].service.[File_Name]Service;
import [PACKAGE].vo.[File_Name]VO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
//import egovframework.rte.fdl.idgnr.EgovIdGnrService;		//IDGEN USE

/**
 * @Class Name : [File_Name]ServiceImpl.java
 * @Description : [Description] ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since [TODAY]
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("[file_Name]Service")
public class [File_Name]ServiceImpl extends ExtsAbstractServiceImpl implements [File_Name]Service {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "[file_Name]Dao")
	private [File_Name]Dao [file_Name]Dao;
	
	/** ID Generation */
    //@Resource(name="[IDGEN_ID]IdGnrService")		//IDGEN USE
    //private EgovIdGnrService egovIdGnrService;	//IDGEN USE
    
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<[File_Name]VO> select[File_Name]List([File_Name]VO searchVO) {
		List<[File_Name]VO> list = (List<[File_Name]VO>)[file_Name]Dao.select[File_Name]List(searchVO);
//		if(list != null){
//			for([File_Name]VO result:list){
//				
//			}
//		}
		return list;
	}

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public Integer select[File_Name]Tot([File_Name]VO searchVO) {
		return [file_Name]Dao.select[File_Name]Tot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public [File_Name]VO select[File_Name]([File_Name]VO searchVO) {
		[File_Name]VO result = [file_Name]Dao.select[File_Name](searchVO);
		return result;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void write[File_Name]([File_Name]VO searchVO) throws Exception {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		String id = NullUtil.nullString(searchVO.get[FirstVo]());
		
		if("".equals(id)){
			//id = egovIdGnrService.getNextStringId();	//IDGEN USE
			//searchVO.set[FirstVo](id);				//IDGEN USE
			if(!isStreAuth())throwBizException("com.error.noauth.write");
			
			[file_Name]Dao.insert[File_Name](searchVO);
			id = searchVO.get[FirstVo]();				//SEQUENCE USE
		}else{
			[File_Name]VO result = select[File_Name](searchVO);
			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
			id = result.get[FirstVo]();
			
			[file_Name]Dao.update[File_Name](searchVO);
		}

	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void delete[File_Name]([File_Name]VO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		[File_Name]VO result = select[File_Name](searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		[file_Name]Dao.delete[File_Name](searchVO);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable([File_Name]VO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable([File_Name]VO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable([File_Name]VO searchVO){
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
	private void validate([File_Name]VO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
