package exts.com.enums;

/**
 * 회원유형코드
 * @author KMK
 *
 */
public enum EnumMbrTypeCd {
	
	SUP("30020", "최고관리자"),
	FOU("30033", "재단직원"),
	CEN("30034", "전문상담사"),
	NTK("30035", "북한이탈주민"),
	NOR("30036", "일반사용자"),
	;
	
	private String mbrTypeCode;
	private String mbrTypeName;
	
	private EnumMbrTypeCd(String mbrTypeCode, String mbrTypeName){
		this.mbrTypeCode = mbrTypeCode;
		this.mbrTypeName = mbrTypeName;
	}
	
	public String getCode(){
		return this.mbrTypeCode;
	}
	
	public String getName(){
		return this.mbrTypeName;
	}
}
