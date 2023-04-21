package exts.com.vo;

import java.io.Serializable;

import jnit.cms.mbr.JnitcmsmbrVO;
import lombok.Getter;
import lombok.Setter;

/**
 * @Class Name : ComMbrVO.java
 * @Description : 회원 VO
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Getter
@Setter
public class ComMbrVO extends JnitcmsmbrVO implements Serializable {

	private static final long serialVersionUID = -9058522081131754631L;

	private String commMode;
	
	//회원추가정보
	private String genderCd;		//성별코드
	private String brdtYmd;		//생년월일
	private String rrno;		//주민등록번호
	private String ntkrdfUnqNo;		//북한이탈주민고유번호
	private String hanawonTh;		//하나원기수
	
	private String entcnyYmd;		//입국연월일
	private String prtdcsYmd;		//보호결정연월일
	private String hanawonFnshYmd;	//하나원수료연월일
	
	private String mbphno;		//휴대폰번호
	private String telno;		//전화번호
	private String zip;		//우편번호
	private String addr;		//주소
	private String daddr;		//상세주소
	
	private String notmbrLogin;//제외아이디
	
	private String smsAuthNumberReqYn;	//SMS 인증요청
	private String smsAuthNumber;		//sms 2차인증 번호
}