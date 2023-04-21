package exts.com.service;

import java.io.IOException;

import net.lingala.zip4j.exception.ZipException;

/**
 * @Class Name : ComSmplService.java
 * @Description : 샘플 Service
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface ComSampleSourceService extends ExtsAbstractService {
    
	/**
	 * 샘플 소스 생성 후 ZIP파일 경로 반환
	 */
	String makeTmpFile(String path, String dbmsType, String inPkg, String inFileName, String inDescription, String tableName, String[] dbColumnArr, String[] dbColumnTypeArr, String[] dbColumnSizeArr, String[] dbColumnIsNotNullArr, String[] dbCommentArr) throws IOException, ZipException;
}
