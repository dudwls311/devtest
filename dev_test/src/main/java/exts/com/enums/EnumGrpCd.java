package exts.com.enums;

/**
 * 공통그룹코드
 * @author KMK
 *
 */
public enum EnumGrpCd {
	
	GENDER_CD("100001"),			//성별
	APLCNT_REL_CD("100002"),		//신청자관계
	BRPLC_CD("100003"),				//출생지
	EXIST_BNF_CD("100004"),			//기존수혜여부
	APLCNT_LAST_ACBG_CD("100005"),	//최종학력
	OCPT_INST_TYPE_CD("100006"),	//종사기관유형
	EDU_SPRT_TRPR_REL_CD("100007"),	//교육지원대상자와의관계
	EMPM_STTS_CD("100008"),			//졸업구분
	//CTG("100009"),				//지원범주
	CTGRY_FRST_CD("100010"),		//모집공고최초범주코드
	BIZ_SE_CD("100011"),			//모집공고 - 사업코드
	PBANCRC_STTS_CD("100012"),		//공고상태
	DSS_SE_CD("100013"),			//질환구분
	SPRT_SE_CD("100014"),			//지원방식
	SPRT_STTS_CD("100016"),			//지원상태코드
	AGE_CD("100017"),				//연령대
	ECNMAT_CD("100018"),			//경제활동
	CR_CD("100019"),				//직업군
	PRTPRD_EXTSN_CD("100020"),		//보호기간연장
	USDUSG_CD("100021"),			//사용용도
	CNCLTN_RSN_CD("100022"),		//해지사유
	BZSTAT_CD("100023"),			//업태
	CARMDL_CD("100024"),			//차종
	RCMT_FLD_CD("100025"),			//모집분야
	BANK_CD("100026"),				//은행
	SPB_CD("100027"),				//지원사업검색용코드
	SPB_STNG_CD("100028"),			//지원사업설정
	SPB_DTL_CD("100029"),			//지원사업설정상세
	SLCTN_MTHD_CD("100030"),		//선발방법
	SLCTN_MMT_CD("100031"),			//선발시기
	FNCRSC_CD("100032"),			//재원
	TEST_RSLT_CD("100033"),			//시험결과
	EARN_CD("100034"),				//소득
	MBOHH_CNT_CD("100035"),			//가구원수
	DWNG_SHAPE_CD("100036"),		//주거형태
	UTBL_NPMNT_CD("100037"),		//공과금체납
	CRSS_CD("100038"),				//위기
	APLCNT_QLFC_CD("100039"),		//신청자격
	SBJCT_CD("100040"),				//신청과목
	EDNST_CD("100041"),				//교육기관
	EXCV_MTHD_CD("100042"),			//발굴방법
	CTPV_CD("100043"),			//시도
	SGG_CD("100044"),			//시군구
	;
	
	private String code;//DB의 grpCd값
	private EnumGrpCd(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
