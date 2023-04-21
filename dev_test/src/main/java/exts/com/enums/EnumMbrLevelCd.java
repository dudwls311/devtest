package exts.com.enums;

/**
 * 회원 레벨코드
 * @author KMK
 *
 */
public enum EnumMbrLevelCd {
	
	LV_A("A", "A레벨"),
	LV_B("B", "B레벨"),
	LV_C("C", "C레벨"),
	LV_D("D", "D레벨"),
	LV_Z("Z", "Z레벨"),
	;
	
	private String mbrLevelCode;
	private String mbrLevelName;
	
	private EnumMbrLevelCd(String mbrLevelCode, String mbrLevelName){
		this.mbrLevelCode = mbrLevelCode;
		this.mbrLevelName = mbrLevelName;
	}
	
	public String getCode(){
		return this.mbrLevelCode;
	}
	
	public String getName(){
		return this.mbrLevelName;
	}
}
