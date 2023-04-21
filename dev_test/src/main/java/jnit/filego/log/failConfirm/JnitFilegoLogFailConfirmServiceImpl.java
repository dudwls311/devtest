package jnit.filego.log.failConfirm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("jnitFilegoLogFailConfirmService")
public class JnitFilegoLogFailConfirmServiceImpl implements JnitFilegoLogFailConfirmService {

	@Resource(name="jnitFilegoLogFailConfirmDAO")
    private JnitFilegoLogFailConfirmDAO jnitFilegoLogFailConfirmDAO;
	
	/**
	 * 파일고 배포 실패확인목록 가져오기
	 * @searchVO
	 */
	public List selectListJnitFilegoLogFailConfirm(JnitFilegoLogFailConfirmDefaultVO searchVO) throws Exception{
		return jnitFilegoLogFailConfirmDAO.selectListJnitFilegoLogFailConfirm(searchVO);
	}
	
	/**
	 * 파일고 배포 실패확인상세 가져오기
	 * @searchVO
	 */
	public JnitFilegoLogFailConfirmVO selectJnitFilegoLogFailConfirm(JnitFilegoLogFailConfirmVO vo) throws Exception{
		return jnitFilegoLogFailConfirmDAO.selectJnitFilegoLogFailConfirm(vo);
	}
	
	/**
	 * 파일고 배포 실패확인상세 추가하기
	 * @searchVO
	 */
	public void insertJnitFilegoLogFailConfirm(JnitFilegoLogFailConfirmVO vo) throws Exception{
		jnitFilegoLogFailConfirmDAO.insertJnitFilegoLogFailConfirm(vo);
	}
	
	/**
	 * 파일고 배포 실패확인갯수 가져오기
	 * @searchVO
	 */
	public int selectListTotJnitFilegoLogFailConfirm(JnitFilegoLogFailConfirmDefaultVO searchVO) throws Exception{
		return jnitFilegoLogFailConfirmDAO.selectListTotJnitFilegoLogFailConfirm(searchVO);
	}
}
