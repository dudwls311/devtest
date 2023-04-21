package exts.com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import egovframework.com.utl.fcc.service.NullUtil;
import jnit.util.FileListUtil;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class SampleSourceUtil {

	private static final String UNDER_BAR = "_";
	private static final String NEW_LINE = "\r\n";
	private static final String TAB = "\t";
	private static final String SEPARATOR = File.separator;
	
	private static final String[] ACCESS_SAMPLE_FOLDER = {"java"/*,"js"*/,"jsp","mapper","message","ddl"/*,"idgen"*/};
	private static final int IDGEN_CIPERS_LENGTH = 20;
	
	private static final String VO_VARIABLE = "voVariable";
	private static final String MAPPER_VARIABLE = "mapperVariable";
	private static final String COLUMN_TYPE_VARIABLE = "columnType";
	private static final String COLUMN_SIZE_VARIABLE = "columnSize";
	private static final String COLUMN_IS_NOT_NULL_VARIABLE = "columnIsNotNull";
	private static final String COMMENT_VARIABLE = "comments";
	
	public static final String SAMPLE_SOURCE_DBMS_TYPE = "cubrid";	//샘플소스 생성시 기본값(DBMS)
	public static final String SAMPLE_SOURCE_COLUMN_TYPE = "VARCHAR";	//샘플소스 생성시 기본값(유형)
    public static final String SAMPLE_SOURCE_COLUMN_SIZE = "20";		//샘플소스 생성시 기본값(사이즈)
    
	/**
	 * 샘플소스파일 생성
	 * @param path
	 * @param inPkg
	 * @param inFileName
	 * @param inDescription
	 * @param tableName
	 * @param dbColumnArr
	 * @param dbColumnTypeArr
	 * @param dbColumnSizeArr
	 * @param dbColumnIsNotNullArr
	 * @param dbCommentArr
	 * @return
	 * @throws IOException
	 * @throws ZipException
	 */
	public static String makeTmpFile(String path, String dbmsType, String inPkg, String inFileName, String inDescription, String tableName, String[] dbColumnArr, String[] dbColumnTypeArr, String[] dbColumnSizeArr, String[] dbColumnIsNotNullArr, String[] dbCommentArr) throws IOException, ZipException {
		String zipPath = "";
		List<String> fileList = FileListUtil.DirectoryList(path);
		DateTime dateTime = new DateTime();
		String today = dateTime.toString("yyyyMMddHHmmss");
		File sampleFolder;		//샘플 파일이 담겨있는 경로(java,js,jsp,mapper,message)
		
		File tmpFolder = new File(path+SEPARATOR+"tmp");
		File tmpDateFolder = new File(tmpFolder+SEPARATOR+today);
		
		for(String sampleFolderStr : fileList) {
			
			sampleFolder = new File(path+SEPARATOR+sampleFolderStr);
			List<String> sampleFileList = FileListUtil.FileList(path+SEPARATOR+sampleFolderStr);		//하위 디렉토리 가져오기
			
			//폴더 && 허용된 폴더일 경우
			if(sampleFolder.isDirectory() && Arrays.asList(ACCESS_SAMPLE_FOLDER).contains(sampleFolderStr)) {
				String source;
				File sampleFile;
				
				StringBuffer sb = new StringBuffer();
				String firstPackage = "";		//첫번째 패키지명
				String secondPackage = "";		//두번째 패키지명
				String filePath = "";			//패키지명에 따른 경로
				String fileTailPath = "";		//패키지명에 따른 경로
				String fileSubName = "";		//폴더 경로
				String replaceSource = "";		//변환된 소스
				String fileTypeFullPath = "";	//파일 유형별 폴더경로
				for(String sampleFileStr : sampleFileList) {
					sb.setLength(0);
					
					sampleFile = new File(path+SEPARATOR+sampleFolderStr+SEPARATOR+sampleFileStr);
					File tmpFile = null;
					
					//파일일 경우
					if(sampleFile.isFile()) {
						
						//ddl, mapper의 경우
						if("ddl".equals(sampleFolderStr) || "mapper".equals(sampleFolderStr)){
							//지정된 DBMS TYPE에 포함되어 있지 않으면 넘김
							if(sampleFileStr.indexOf(dbmsType) == -1) continue;
						}
						
						//샘플 소스 가져오기
						source = "";
						BufferedReader bufferedReader = new BufferedReader(new FileReader(sampleFile));
						String readLine = "";
						while((readLine = bufferedReader.readLine()) != null) {
							source += readLine+NEW_LINE;
						};
						
						//사용할 소스로 변화
						HashMap<String, String> resultMap = StrReplace(source, tableName, dbmsType, inPkg, inFileName, inDescription, dbColumnArr, dbColumnTypeArr, dbColumnSizeArr, dbColumnIsNotNullArr, dbCommentArr);
						replaceSource = resultMap.get("source");
						firstPackage = resultMap.get("firstPackage");
						secondPackage = resultMap.get("secondPackage");
						filePath = resultMap.get("filePath");
						fileTailPath = resultMap.get("fileTailPath");
						fileSubName = resultMap.get("fileSubName");
						
						//변환된 소스 파일 생성
						sampleFileStr = sampleFileStr.replace("SAMPLE_SOURCE_", inFileName).replace("sample_source_", fileSubName).replace("DBMSTYPE", dbmsType);
						
						if("java".equals(sampleFolderStr)){
							fileTypeFullPath = sb.append(SEPARATOR).append("src").append(SEPARATOR).append("main").append(SEPARATOR).append("java")
											     .append(SEPARATOR).append(filePath).append(SEPARATOR).toString();
							
							if(sampleFileStr.indexOf("Controller.java") != -1) {
								tmpFile = new File(tmpDateFolder+fileTypeFullPath+"web"+SEPARATOR+sampleFileStr);
							}else if(sampleFileStr.indexOf("VO.java") != -1) {
								tmpFile = new File(tmpDateFolder+fileTypeFullPath+"vo"+SEPARATOR+sampleFileStr);
							}else if(sampleFileStr.indexOf("Validator.java") != -1) {
								tmpFile = new File(tmpDateFolder+fileTypeFullPath+"validator"+SEPARATOR+sampleFileStr);
							}else if(sampleFileStr.indexOf("Service.java") != -1) {
								tmpFile = new File(tmpDateFolder+fileTypeFullPath+"service"+SEPARATOR+sampleFileStr);
							}else if(sampleFileStr.indexOf("Dao.java") != -1 || sampleFileStr.indexOf("ServiceImpl.java") != -1) {
								tmpFile = new File(tmpDateFolder+fileTypeFullPath+"service"+SEPARATOR+"impl"+SEPARATOR+sampleFileStr);
							}
							
						}else if("jsp".equals(sampleFolderStr)) {
							fileTypeFullPath = sb.append(SEPARATOR).append("src").append(SEPARATOR).append("main").append(SEPARATOR).append("webapp").append(SEPARATOR).append("WEB-INF").append(SEPARATOR).append("jsp").append(SEPARATOR)
								                 .append(SEPARATOR).append(filePath).append(SEPARATOR)/*.append(fileSubName).append(SEPARATOR)*/.toString();
							
							tmpFile = new File(tmpDateFolder+fileTypeFullPath+sampleFileStr);
						}else if("js".equals(sampleFolderStr)) {
							fileTypeFullPath = sb.append(SEPARATOR).append("src").append(SEPARATOR).append("main").append(SEPARATOR).append("webapp").append(SEPARATOR).append("resources").append(SEPARATOR).append("js").append(SEPARATOR)
											     .append(SEPARATOR).append(filePath).append(SEPARATOR)/*.append(fileSubName).append(SEPARATOR)*/.toString();
							
							tmpFile = new File(tmpDateFolder+fileTypeFullPath+sampleFileStr);
						}else if("idgen".equals(sampleFolderStr)) {
							fileTypeFullPath = sb.append(SEPARATOR).append("src").append(SEPARATOR).append("main").append(SEPARATOR).append("resources").append(SEPARATOR)
												 .append(SEPARATOR).append(firstPackage).append(SEPARATOR).append("spring").append(SEPARATOR).append(secondPackage).append(SEPARATOR).toString();
							
							sampleFileStr = sampleFileStr.replace(inFileName, secondPackage+"_"+inFileName);
							tmpFile = new File(tmpDateFolder+fileTypeFullPath+sampleFileStr);
						}else if("message".equals(sampleFolderStr)) {
							fileTypeFullPath = sb.append(SEPARATOR).append("src").append(SEPARATOR).append("main").append(SEPARATOR).append("resources").append(SEPARATOR)
												 .append(SEPARATOR).append(firstPackage).append(SEPARATOR).append("message").append(SEPARATOR).append(secondPackage).append(SEPARATOR).toString();
							
							sampleFileStr = sampleFileStr.replace(inFileName, secondPackage+"_"+inFileName);
							tmpFile = new File(tmpDateFolder+fileTypeFullPath+sampleFileStr);
						}else if("mapper".equals(sampleFolderStr)) {
							fileTypeFullPath = sb.append(SEPARATOR).append("src").append(SEPARATOR).append("main").append(SEPARATOR).append("resources").append(SEPARATOR)
								                 .append(SEPARATOR).append(firstPackage).append(SEPARATOR).append("mapper").append(SEPARATOR).append(fileTailPath).append(SEPARATOR).toString();
							
							tmpFile = new File(tmpDateFolder+fileTypeFullPath+sampleFileStr);
						}else if("ddl".equals(sampleFolderStr)) {
							fileTypeFullPath = sb.append(SEPARATOR).append("src").append(SEPARATOR).append("main").append(SEPARATOR).append("resources").append(SEPARATOR)
								                 .append(SEPARATOR).append(firstPackage).append(SEPARATOR).append("ddl").append(SEPARATOR).append(secondPackage).append(SEPARATOR).toString();
							
							tmpFile = new File(tmpDateFolder+fileTypeFullPath+sampleFileStr);
						}
						
						if(!tmpFile.getParentFile().exists()) tmpFile.getParentFile().mkdirs();
						FileWriter fileWriter = new FileWriter(tmpFile);
						fileWriter.write(replaceSource);
						fileWriter.flush();
						fileWriter.close();
					}
				}
			}
		}
		
		//압축파일 저장
		zipPath = tmpFolder+SEPARATOR+"sample_source_"+inFileName+"_"+today+".zip";
		ZipFile zipFile = new ZipFile(zipPath);
		
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		List<String> sourceZipFolder = FileListUtil.DirectoryList(tmpDateFolder);
		for(String zipFilePath : sourceZipFolder) {
			zipFile.addFolder(tmpDateFolder+SEPARATOR+zipFilePath, parameters);
		}
		
		//변환된 파일제거
		FileListUtil.deleteAllFiles(tmpDateFolder.getPath());
		tmpDateFolder.delete();
		
		return zipPath;
	}
	
	/**
	 * 소스 replace
	 * @param source
	 * @param inPkg
	 * @param inFileName
	 * @param inDescription
	 * @param inColumn
	 * @param inComment
	 * @return
	 */
	private static HashMap<String, String> StrReplace(String source, String tableName, String dbmsType, String inPkg, String inFileName, String inDescription, String[] dbColumnArr, String[] dbColumnTypeArr, String[] dbColumnSizeArr, String[] dbColumnIsNotNullArr, String[] dbCommentArr) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		Map<String, String[]> columnMap = getCoulmnSet(dbColumnArr, dbColumnTypeArr, dbColumnSizeArr, dbColumnIsNotNullArr, dbCommentArr);
		String[] voVariables = columnMap.get(VO_VARIABLE);
		String[] mapperVariables = columnMap.get(MAPPER_VARIABLE);
		String[] comments = columnMap.get(COMMENT_VARIABLE);
		
		String today = new DateTime().toString("yyyy.MM.dd");
		String pkg = inPkg.toLowerCase();
		String FileName = inFileName;
		String fileName = firstUpperToLowerString(inFileName);
		String fileSubName = getUpperSplitter(fileName, 1);
		String Description = inDescription;
		
		String FirstColumn = mapperVariables[0];
		String SecondColumn = mapperVariables[1];
		String firstVo = voVariables[0];
		String FirstVo = firstUpper2(firstVo);
		
		String modename = getUpperString(FileName);
		String Modename = firstUpper(modename);
		String controllerMenuCode = getUpperToPrefix(fileName, UNDER_BAR).toUpperCase();
		
		String firstPackage = getSplitPackageName(pkg, 0);
		String secondPackage = getSplitPackageName(pkg, 1);
		String tailPackage = getTailPackageName(pkg);
		String filePath = getUpperToPrefix(getPrefixToUpper(pkg, "."), "/");
		String fileTailPath = getUpperToPrefix(getPrefixToUpper(tailPackage, "."), "/");
		
		String idgenId = getPrefixToUpper(pkg, ".")+inFileName;
		String idgenTableName = firstPackage.toUpperCase()+UNDER_BAR+controllerMenuCode;
		String idgenTablePrefix = (getUpperString(firstUpper(firstPackage))+modename).toUpperCase()+UNDER_BAR;
		String idgenCipers = (IDGEN_CIPERS_LENGTH-idgenTablePrefix.length())+"";
		
		String voColumn = "";
		String validatorColumn = "";
		String validatorReject = "";
		String message = "";
		
		String jspListTh = "";
		String jspListTd = "";
		String jspViewTr = "";
		String jspWriteTr = "";
		
		String mapperIfWhereSearch = "";
		String mapperColumn = "";
		String mapperFirstColumnSelect = "";
		String mapperInsertColumn = "";
		String mapperInsertAlias = "";
		String mapperUpdateColumn = "";
		
		String ddlColumn = "";
		String ddlComment = "";
		
		String voName;
		String mapperName;
		String comment;
		
		String columnType;
		String columnSize;
		String columnIsNotNull;
		
		for(int i=0; i<voVariables.length; i++) {
			voName = NullUtil.nullString(voVariables[i]);
			mapperName = NullUtil.nullString(mapperVariables[i]).toUpperCase();
			comment = NullUtil.nullString((i < comments.length?comments[i]:""));
			
			columnType = NullUtil.nullString((i < dbColumnTypeArr.length?dbColumnTypeArr[i]:""));
			if("".equals(columnType)) columnType = SAMPLE_SOURCE_COLUMN_TYPE;
			columnType = columnType.toUpperCase();
			
			columnSize = NullUtil.nullString((i < dbColumnSizeArr.length?dbColumnSizeArr[i]:""));
			if("".equals(columnSize)) columnSize = SAMPLE_SOURCE_COLUMN_SIZE;
			
			columnIsNotNull = NullUtil.nullString((i < dbColumnIsNotNullArr.length?dbColumnIsNotNullArr[i]:""));
			if("Y".equals(columnIsNotNull)) {
				columnIsNotNull = "NOT NULL";
			}else {
				columnIsNotNull = "NULL";
			}
			
			if("".equals(voName)) continue;
			
			//VO
			voColumn += "private String "+voName+";"+TAB+TAB+"//"+comment+NEW_LINE+TAB;
			
			//validator
			validatorColumn += "private static final String "+voName+" = \""+voName+"\";"+TAB+TAB+"//"+comment+NEW_LINE+TAB;
			if(i == 0) {
				validatorReject += "//ValidationUtils.rejectIfEmptyOrWhitespace(errors,"+voName+", REQUIRED_FIELD, makeMessage("+voName+", REQUIRED_FIELD));"+TAB+"//"+comment+NEW_LINE+TAB+TAB;
			}else {
				validatorReject += "ValidationUtils.rejectIfEmptyOrWhitespace(errors,"+voName+", REQUIRED_FIELD, makeMessage("+voName+", REQUIRED_FIELD));"+TAB+TAB+"//"+comment+NEW_LINE+TAB+TAB;
			}
			
			//message
			if(i == 0) message += firstPackage+".item."+tailPackage+" = "+Description+NEW_LINE;
			message += firstPackage+".item."+tailPackage+"."+voName+" = "+comment+NEW_LINE;
			
			//JSP
			jspListTh += "<th scope=\"col\">"+comment+"</th>"+NEW_LINE+TAB+TAB+TAB+TAB+TAB+TAB+TAB;
			
			if(i == 0) {
				jspListTd += "<td><a href=\"#\" onclick=\"javascript:fnView('<c:out value=\"${result."+voName+"}\" />');return false;\"><c:out value=\"${result."+voName+"}\" /></a></td>"+NEW_LINE+TAB+TAB+TAB+TAB+TAB+TAB+TAB;
			}else {
				jspListTd += "<td><c:out value=\"${result."+voName+"}\" /></td>"+NEW_LINE+TAB+TAB+TAB+TAB+TAB+TAB+TAB;
			}
			
			jspViewTr += TAB+TAB+TAB+TAB+TAB+TAB+TAB+"<th scope=\"row\">"+comment+"</th>"+NEW_LINE
						+TAB+TAB+TAB+TAB+TAB+TAB+TAB+"<td>"+NEW_LINE
						+TAB+TAB+TAB+TAB+TAB+TAB+TAB+TAB+"<c:out value=\"${result."+voName+"}\" />"+NEW_LINE
						+TAB+TAB+TAB+TAB+TAB+TAB+TAB+"</td>"+NEW_LINE;
			
			if(i > 0) {
				jspWriteTr += TAB+TAB+TAB+TAB+TAB+TAB+TAB+"<tr>"+NEW_LINE
							+TAB+TAB+TAB+TAB+TAB+TAB+TAB+TAB+"<th scope=\"row\">"+comment+"</th>"+NEW_LINE
							+TAB+TAB+TAB+TAB+TAB+TAB+TAB+TAB+"<td>"+NEW_LINE
							+TAB+TAB+TAB+TAB+TAB+TAB+TAB+TAB+TAB+"<input type=\"text\" class=\"text\" style=\"width:180px;\" id=\""+voName+"\" name=\""+voName+"\" value=\"<c:out value=\"${result."+voName+"}\" />\" />"+NEW_LINE
							+TAB+TAB+TAB+TAB+TAB+TAB+TAB+TAB+"</td>"+NEW_LINE
							+TAB+TAB+TAB+TAB+TAB+TAB+TAB+"</tr>"+NEW_LINE;
			}
			
			//MAPPER
			if(i > 0) {
				mapperIfWhereSearch += TAB+TAB+"<if test='"+voName+" != null and !"+voName+".equals(\"\")'>"+NEW_LINE
									   +TAB+TAB+TAB+"AND "+mapperName+" = #{"+voName+"}"+NEW_LINE
									   +TAB+TAB+"</if>"+NEW_LINE;
			}
			
			mapperColumn += TAB+TAB+mapperName+TAB+TAB+voName+TAB+TAB+","+NEW_LINE;
			
			
			
			if(i == 0) mapperFirstColumnSelect += "AND "+FirstColumn+" = #{"+firstVo+"}";
			
			mapperInsertColumn += TAB+TAB+TAB+mapperName+TAB+TAB+","+NEW_LINE;
			mapperInsertAlias += TAB+TAB+TAB+"#{"+voName+"}"+TAB+TAB+","+NEW_LINE;
			if(i > 0) mapperUpdateColumn += TAB+TAB+TAB+mapperName+TAB+"="+TAB+"#{"+voName+"}"+TAB+TAB+","+NEW_LINE;
			
			//DDL
			if("mysql".equals(dbmsType)) {
				ddlColumn += TAB+mapperName+TAB+TAB+""+columnType+"("+columnSize+")"+TAB+TAB+columnIsNotNull+TAB+TAB+"COMMENT '"+comment+"'"+TAB+TAB+","+NEW_LINE;
			}else if("cubrid".equals(dbmsType)) {
				ddlColumn += TAB+mapperName+TAB+TAB+""+columnType+"("+columnSize+")"+TAB+TAB+columnIsNotNull+TAB+TAB+","+NEW_LINE;
			}else if("oracle".equals(dbmsType) || "tibero".equals(dbmsType)) {
				ddlColumn += TAB+mapperName+TAB+TAB+""+columnType+"("+columnSize+")"+TAB+TAB+columnIsNotNull+TAB+TAB+","+NEW_LINE;
				ddlComment += "COMMENT ON COLUMN "+tableName+"."+mapperName+" IS '"+comment+"';"+NEW_LINE;
			}
		}
		
		source = source.replace("[PACKAGE]", pkg)
				.replace("[File_Name]", FileName)
				.replace("[file_Name]", fileName)
				.replace("[file_Sub_Name]", fileSubName)
				.replace("[Description]", Description)
				.replace("[TODAY]", today)
				.replace("[firstVo]", firstVo)
				.replace("[FirstVo]", FirstVo)
				.replace("[FirstColumn]", FirstColumn)
				.replace("[SecondColumn]", SecondColumn)
				.replace("[Mode_name]", Modename)
				.replace("[mode_name]", modename)
				.replace("[file_path]", filePath)
				.replace("[first_package]", firstPackage)
				.replace("[second_package]", secondPackage)
				.replace("[tail_package]", tailPackage)
				
				//CONTROLLER
				.replace("[CONTROLLER_MENU_CODE]", controllerMenuCode)
				
				//VO
				.replace("[VO_COLUMN]", voColumn)
				
				//VALIDATOR
				.replace("[VALIDATOR_COLUMN]", validatorColumn)
				.replace("[VALIDATOR_REJECT]", validatorReject)
				
				//MESSAGE
				.replace("[MESSAGE]", message)
				
				//JSP
				.replace("[JSP_LIST_TH]", jspListTh)
				.replace("[JSP_LIST_TD]", jspListTd)
				.replace("[JSP_VIEW_TR]", jspViewTr)
				.replace("[JSP_WRITE_TR]", jspWriteTr)
				
				//MAPPER
				.replace("[TABLE_NAME]", tableName)
				.replace("[MAPPER_IF_WHERE_SEARCH]", mapperIfWhereSearch)
				.replace("[MAPPER_COLUMN]", mapperColumn)
				.replace("[MAPPER_FIRST_COLUMN_SELECT]", mapperFirstColumnSelect)
				.replace("[MAPPER_INSERT_COLUMN]", mapperInsertColumn)
				.replace("[MAPPER_INSERT_ALIAS]", mapperInsertAlias)
				.replace("[MAPPER_UPDATE_COLUMN]", mapperUpdateColumn)
				
				//DDL
				.replace("[DDL_COLUMN]", ddlColumn)
				.replace("[DDL_COMMENT]", ddlComment)
				
				//IDGEN
				.replace("[IDGEN_ID]", idgenId)
				.replace("[IDGEN_TABLE_NAME]", idgenTableName)
				.replace("[IDGEN_TABLE_PREFIX]", idgenTablePrefix)
				.replace("[IDGEN_CIPERS]", idgenCipers)
				.replace("[sample_source]", fileSubName)
				;
			  
		//resultMap
		resultMap.put("firstPackage", firstPackage);		//폴더 생성시 사용
		resultMap.put("secondPackage", secondPackage);		//폴더 생성시 사용
		resultMap.put("filePath", filePath);				//폴더 생성시 사용
		resultMap.put("fileTailPath", fileTailPath);		//폴더 생성시 사용
		resultMap.put("fileSubName", fileSubName);			//폴더 생성시 사용
		resultMap.put("source", source);					//변환된 소스
		
		
		return resultMap;
	}
	
	/**
	 * 첫번째 대문자를 소문자로 변경
	 * @param str
	 * @return
	 */
	private static String firstUpperToLowerString(String str) {
		int upToLowCnt = 1;
		String ret = "";
		String[] ap = str.split("");
		for(String s : ap) {
			if(upToLowCnt > 0 &&Character.isUpperCase(s.charAt(0))) {
				s = s.toLowerCase();
				upToLowCnt--;
			}
			ret += s;
		}
		return ret;
	}
	
	/**
	 * 첫번째만 대문자로 변경
	 * @param str
	 * @return
	 */
	private static String firstUpper(String str) {
		int upToLowCnt = 1;
		String ret = "";
		String[] ap = str.split("");
		for(String s : ap) {
			if(upToLowCnt > 0) {
				s = s.toUpperCase();
				upToLowCnt--;
			}else {
				s = s.toLowerCase();
			}
			ret += s;
		}
		return ret;
	}
	
	/**
	 * 첫번째만 대문자로 변경 후 나머지는 그대로
	 * @param str
	 * @return
	 */
	private static String firstUpper2(String str) {
		int upToLowCnt = 1;
		String ret = "";
		String[] ap = str.split("");
		for(String s : ap) {
			if(upToLowCnt > 0) {
				s = s.toUpperCase();
				upToLowCnt--;
			}
			ret += s;
		}
		return ret;
	}
	
	/**
	 * upperCnt 값에 따른 대문자부터 문자열을 가져온다
	 * @param str
	 * @param upperCnt
	 * @return
	 */
	private static String getUpperSplitter(String str, int upperCnt) {
		List<Integer> upperStringList = new ArrayList<Integer>();
		String[] stringArr = str.split("");
		for(int i=0; i<stringArr.length; i++) {
			if(Character.isUpperCase(stringArr[i].charAt(0))) upperStringList.add(i);
		}
		return firstUpperToLowerString(str.substring(upperStringList.get(upperCnt-1)));
	}
	
	/**
	 * 대문자의 값만 소문자로 변환해서 가져온다
	 * @param str
	 * @param upperCnt
	 * @return
	 */
	private static String getUpperString(String str) {
		String ret = "";
		String[] stringArr = str.split("");
		for(int i=0; i<stringArr.length; i++) {
			if(Character.isUpperCase(stringArr[i].charAt(0))) ret += stringArr[i].toLowerCase();
		}
		return ret;
	}
	
	/**
	 * 특정 문자의 다음 문자열을 대문자로 변경
	 * @param str
	 * @param prefix
	 * @return
	 */
	private static String textToUpperString(String str, String prefix) {
		String[] stringArr = str.split("");
		
		String ret = "";
		
		for(int i=0; i<stringArr.length; i++) {
			if(prefix.equals(stringArr[i])) {
				if(stringArr.length-1 > i) ret += stringArr[i+1].toUpperCase();
				i++;
			}else {
				ret += stringArr[i].toLowerCase();
			}
		}
		return ret;
	}
	
	/**
	 * prefix를 지우고 뒷자리를 대문자로 변경
	 * @param str
	 * @param prefix
	 * @return
	 */
	private static String getUpperToPrefix(String str, String prefix) {
		return getUpperToPrefix(str, prefix, 9999);
	}
	
	/**
	 * prefix를 지우고 뒷자리를 대문자로 변경
	 * @param str
	 * @param prefix
	 * @param uCnt
	 * @return
	 */
	private static String getUpperToPrefix(String str, String prefix, int uCnt) {
		String ret = "";
		String[] stringArr = str.split("");
		int upperCnt = 0;
		for(int i=0; i<stringArr.length; i++) {
			if(Character.isUpperCase(stringArr[i].charAt(0)) && uCnt > upperCnt) {
				ret += prefix + stringArr[i].toLowerCase();
				upperCnt++;
			}else {
				ret += stringArr[i];
			}
		}
		return ret;
	}
	
	/**
	 * prefix뒷자리를 대문자로 변경
	 * @param str
	 * @param prefix
	 * @return
	 */
	private static String getPrefixToUpper(String str, String prefix) {
		String ret = "";
		String[] stringArr = str.split("");
		boolean isUpper = false;
		for(int i=0; i<stringArr.length; i++) {
			if(prefix.equals(stringArr[i])) isUpper = true;
			
			if(isUpper) {
				ret += stringArr[i+1].toUpperCase();
				i++;
			}else {
				ret += stringArr[i];
			}
			isUpper = false;
		}
		
		return ret;
	}
	
	/**
	 * 배열 데이터 Map 셋팅
	 * @param column
	 * @param comment
	 * @return
	 */
	private static Map<String, String[]> getCoulmnSet(String[] dbColumnArr, String[] dbColumnTypeArr, String[] dbColumnSizeArr, String[] dbColumnIsNotNullArr, String[] dbCommentArr) {
		if(dbColumnArr == null) return null;
		
		String[] voArr = new String[dbColumnArr.length];
		String[] mapperArr = new String[dbColumnArr.length];
		String[] columnTypeArr = new String[dbCommentArr.length];
		String[] columnSizeArr = new String[dbCommentArr.length];
		String[] columnIsNotNullArr = new String[dbCommentArr.length];
		String[] commentArr = new String[dbCommentArr.length];
		
		Map<String, String[]> retMap = new HashMap<String, String[]>();
		for(int i=0; i<dbColumnArr.length; i++) {
			voArr[i] = textToUpperString(dbColumnArr[i], UNDER_BAR);
			mapperArr[i] = dbColumnArr[i];
			if(i < dbColumnTypeArr.length ) columnTypeArr[i] = dbColumnTypeArr[i];
			if(i < dbColumnSizeArr.length ) columnSizeArr[i] = dbColumnSizeArr[i];
			if(i < dbColumnIsNotNullArr.length ) columnIsNotNullArr[i] = dbColumnIsNotNullArr[i];
			if(i < dbCommentArr.length ) commentArr[i] = dbCommentArr[i];
		}
		retMap.put(VO_VARIABLE, voArr);
		retMap.put(MAPPER_VARIABLE, mapperArr);
		retMap.put(COLUMN_TYPE_VARIABLE, columnTypeArr);
		retMap.put(COLUMN_SIZE_VARIABLE, columnSizeArr);
		retMap.put(COLUMN_IS_NOT_NULL_VARIABLE, columnIsNotNullArr);
		retMap.put(COMMENT_VARIABLE, commentArr);
		
		return retMap;
	}
	
	//첫번째 패키지명 가져오기
	private static String getSplitPackageName(String pkg, int length) {
		String[] pkgArr = pkg.split("\\.");
		return pkgArr[length];
	}
	
	/**
	 * 첫번째를 제외한 패키지명
	 * @param pkg
	 * @return
	 */
	private static String getTailPackageName(String pkg) {
		String[] pkgArr = pkg.split("\\.");
		String ret = "";
		for(int i=0; i<pkgArr.length; i++) {
			if(i == 0) continue;
			ret += (!"".equals(ret) ? "." : "")+pkgArr[i];
		}
		return ret;
	}
}
