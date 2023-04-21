/**
 * @version 3.2.0.1
 */
package egovframework.com.cmm.service;

/**
 *  Class Name : Globals.java
 *  Description : 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.01.19    박지욱          최초 생성
 *
 *  @author 공통 서비스 개발팀 박지욱
 *  @since 2009. 01. 19
 *  @version 1.0
 *  @see
 *
 */

public class Globals {
	//OS 유형
    public static final String OS_TYPE = EgovProperties.getProperty("Globals.OsType");
    //DB 유형
    public static final String DB_TYPE = EgovProperties.getProperty("Globals.DbType");
    //메인 페이지
    public static final String MAIN_PAGE = EgovProperties.getProperty("Globals.MainPage");
    //ShellFile 경로
    public static final String SHELL_FILE_PATH = EgovProperties.getPathProperty("Globals.ShellFilePath");
    //컨텍스트 경로
    public static final String CONTEXT_PATH = EgovProperties.getProperty("Globals.contextPath");
    
    //퍼로퍼티 파일 위치
    public static final String CONF_PATH = EgovProperties.getPathProperty("Globals.ConfPath");
    //Server정보 프로퍼티 위치
    public static final String SERVER_CONF_PATH = EgovProperties.getPathProperty("Globals.ServerConfPath");
    //Client정보 프로퍼티 위치
    public static final String CLIENT_CONF_PATH = EgovProperties.getPathProperty("Globals.ClientConfPath");
    //파일포맷 정보 프로퍼티 위치
    public static final String FILE_FORMAT_PATH = EgovProperties.getPathProperty("Globals.FileFormatPath");

    //파일 업로드 원 파일명
	public static final String ORIGIN_FILE_NM = "originalFileName";
	//파일 확장자
	public static final String FILE_EXT = "fileExtension";
	//파일크기
	public static final String FILE_SIZE = "fileSize";
	//업로드된 파일명
	public static final String UPLOAD_FILE_NM = "uploadFileName";
	//파일경로
	public static final String FILE_PATH = "filePath";

	//메일발송요청 XML파일경로
	public static final String MAIL_REQUEST_PATH = EgovProperties.getPathProperty("Globals.MailRequestPath");
	//메일발송응답 XML파일경로
	public static final String MAIL_RESPONSE_PATH = EgovProperties.getPathProperty("Globals.MailRResponsePath");

	//CMS 로그인 URL
	public static final String CMS_LOGIN_URL = EgovProperties.getProperty("Globals.CMS.LOGIN_URL");
	
	// G4C 연결용 IP (localhost)
	public static final String LOCAL_IP = EgovProperties.getProperty("Globals.LocalIp");

	//파일 업로드 경로
	public static final String UPLOAD_PATH = EgovProperties.getProperty("Globals.fileStorePath");			//업로드 경로
    
    //서블릿명
    public static final String SERVLET_NAME = EgovProperties.getProperty("GLobals.servletName");
    
    public static final String ACCESS_FILE_EXT = EgovProperties.getProperty("Globals.FILE.EXT");
    public static final String EXT_FILE_THUMBIMG = EgovProperties.getProperty("Globals.FILE.EXT.THUMBIMG");
    
    //CRON 관련
    public static final String CRON_EXCUTE_SERVER_IP = EgovProperties.getProperty("Globals.CRON.EXCUTE_SERVER_IP");
    public static final String CRON_ALIMI_URL = EgovProperties.getProperty("Globals.CRON.ALIMI_URL");
    public static final String CRON_DISSEMINATE_URL = EgovProperties.getProperty("Globals.CRON.DISSEMINATE_URL");

    //파일고 관련
    public static final String FILEGO_ACTIVE = EgovProperties.getProperty("Globals.FILEGO.ACTIVE");								//파일고 활성화 여부 (on, off)
    public static final String FILEGO_SEND_FILE_URL = EgovProperties.getProperty("Globals.FILEGO.SEND_FILE_URL");				//파일고 파일 전송 URL
    public static final String FILEGO_SEND_DELETE_FILE_URL = EgovProperties.getProperty("Globals.FILEGO.SEND_DELETE_FILE_URL");	//파일고 파일 삭제 URL
    public static final String FILEGO_SEND_RENAME_FILE_URL = EgovProperties.getProperty("Globals.FILEGO.SEND_RENAME_FILE_URL");	//파일고 파일 리네임 RUL
    public static final String FILEGO_CONTENT_SENDYN = EgovProperties.getProperty("Globals.FILEGO.TYPE.CONTENT.SENDYN");		//파일고 콘텐츠파일 유형 파일전송 여부
    public static final String FILEGO_UPLOAD_SENDYN = EgovProperties.getProperty("Globals.FILEGO.TYPE.UPLOAD.SENDYN");			//파일고 업로드파일 유형 파일전송 여부
    public static final String FILEGO_CONFIG_SENDYN = EgovProperties.getProperty("Globals.FILEGO.TYPE.CONFIG.SENDYN");			//파일고 설정파일 유형 파일전송 여부
    public static final String FILEGO_BOARD_SENDYN = EgovProperties.getProperty("Globals.FILEGO.TYPE.BOARD.SENDYN");			//파일고 게시판파일 유형 파일전송 여부
    public static final String FILEGO_CRON_SENDYN = EgovProperties.getProperty("Globals.FILEGO.TYPE.CRON.SENDYN");				//파일고 크론파일 유형 파일전송 여부

    //로그인 관련
    public static final String CRON_LOGIN_UNCLOCK_ACTIVE = EgovProperties.getProperty("Globals.CRON.LOGIN.UNCLOCK.ACTIVE");		//로그인 틀린횟수 CRON 사용여부
    public static final String CRON_LOGIN_UNCLOCK_TIMER = EgovProperties.getProperty("Globals.CRON.LOGIN.UNCLOCK.TIMER");		//로그인 틀린횟수 CRON 규칙
    public static final String LOGIN_UNCLOCK_TIME = EgovProperties.getProperty("Globals.LOGIN.UNCLOCK.TIME");					//로그인 틀린횟수 초기화 시간
    public static final String LOGIN_PW_MISS_MAX_COUNT = EgovProperties.getProperty("Globals.LOGIN.PW.MISS.MAX.COUNT");			//로그인 틀린횟수 최대치
    
    //static 데이터 동기화
    public static final String SYNC_PROTOCOL_SERVER_PORT = EgovProperties.getProperty("Globals.SYNC.PROTOCOL_SERVER_PORT");		//동기화 서버(콤마 구분)
    public static final String SYNC_URI = EgovProperties.getProperty("Globals.SYNC.URI");								//동기화 URI
    public static final String SYNC_CONN_TIME = EgovProperties.getProperty("Globals.SYNC.CONN_TIME");					//1000=1초
    public static final String SYNC_READ_TIME = EgovProperties.getProperty("Globals.SYNC.READ_TIME");					//1000=1초
    public static final String SYNC_MAX_CONN_TOTAL = EgovProperties.getProperty("Globals.SYNC.MAX_CONN_TOTAL");			//maxConnTotal은 연결을 유지할 최대 숫자
    public static final String SYNC_MAX_CONN_PER_ROUTE = EgovProperties.getProperty("Globals.SYNC.MAX_CONN_PER_ROUTE");	//maxConnPerRoute는 특정 경로당 최대 숫자
    
}
