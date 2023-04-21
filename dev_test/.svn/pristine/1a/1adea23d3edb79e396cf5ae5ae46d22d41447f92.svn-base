package exts.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class ValidationDateUtil {
	// field값이 있을때(rejectValue발생)//////////////////////
		/**
		 * 날짜 체크(yyyy-MM-dd형태) 및 Datetime 리턴
		 * @param errors
		 * @param field
		 * @param errorCode
		 * @param defaultMessage
		 * @return
		 */
		public static DateTime rejectValueIfDatePattern(
				Errors errors, String field, String errorCode, String defaultMessage){
			return rejectValueIfDatePattern(errors, field, errorCode, null, defaultMessage);
		}
		
		public static DateTime rejectValueIfDatePattern (
				Errors errors, String field, String errorCode, Object[] errorArgs, String defaultMessage){
			Object value = errors.getFieldValue(field);
			DateTime ret = null;
			if (value == null ||!StringUtils.hasText(value.toString())) {
				errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
			}else{
				boolean isError = true;

				String str = value.toString().replaceAll("\\.", "").replaceAll("-", "");
				try{
					DateTimeFormatter fmt = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd");
					ret = DateTime.parse(str, fmt);
					isError = false; 
				}catch(IllegalArgumentException e){
				}
				Matcher match = Pattern.compile("^\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[0-1])$").matcher(str);
				if(!match.find())isError = true;
				if(isError){
					errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
				}
			}
			return ret;
		}

		/**
		 * 오늘날짜 이전인지체크(년월일만 비교)
		 * @param date1
		 * @param errors
		 * @param field
		 * @param errorCode
		 * @param defaultMessage
		 */
		public static void rejectValueIfDateGreaterThanToday(
				DateTime date1,
				Errors errors, String field, String errorCode, String defaultMessage){
			DateTime curTime = new DateTime();
			DateTime now = new DateTime(curTime.getYear(),curTime.getMonthOfYear(), curTime.getDayOfMonth(),0,0);//년월일만 비교하도록.
			if(date1 == null || !date1.isBefore(now))errors.rejectValue(field, errorCode, defaultMessage);
		}
				
		/**
		 * 오늘날짜 이후인지체크(년월일만 비교)
		 * @param date1
		 * @param errors
		 * @param field
		 * @param errorCode
		 * @param defaultMessage
		 */
		public static void rejectValueIfDateLesserThanToday(
				DateTime date1,
				Errors errors, String field, String errorCode, String defaultMessage){
			DateTime curTime = new DateTime();
			DateTime now = new DateTime(curTime.getYear(),curTime.getMonthOfYear(), curTime.getDayOfMonth(),0,0);//년월일만 비교하도록.
			if(date1 == null || !date1.isAfter(now))errors.rejectValue(field, errorCode, defaultMessage);
		}
		/**
		 * date2이 date1보다 이상날짜 인지 체크.
		 * @param date1
		 * @param date2
		 * @param errors
		 * @param field
		 * @param errorCode
		 * @param defaultMessage
		 */
		public static void rejectValueIfDateGreaterThan(
				DateTime date1, DateTime date2,
				Errors errors, String field, String errorCode, String defaultMessage){
			rejectValueIfDateGreaterThan(date1, date2, errors, field, errorCode, null, defaultMessage);
		}
		public static void rejectValueIfDateGreaterThan(
				DateTime date1, DateTime date2,
				Errors errors, String field, String errorCode, Object[] errorArgs, String defaultMessage){
			if(date1 == null || date2 == null){
				errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
			}else{
				if(!date2.isEqual(date1) && !date2.isAfter(date1)){
					errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
				}
			}
			
		}

		

	// field값이 없을때(reject발생)//////////////////////
		
		public static DateTime rejectIfDatePattern(
				Errors errors, String field, String value, String defaultMessage){
			return rejectIfDatePattern(errors, field, value, null, defaultMessage);
		}
		
		public static DateTime rejectIfDatePattern (
				Errors errors, String field, String value, Object[] errorArgs, String defaultMessage){
			DateTime ret = null;
			if (value == null ||!StringUtils.hasText(value.toString())) {
				errors.reject(field, errorArgs, defaultMessage);
			}else{
				boolean isError = true;
				String str = value.toString().replaceAll("\\.", "").replaceAll("-", "");
				try {
					DateTimeFormatter fmt = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd");
					ret = DateTime.parse(str, fmt);
					isError = false; 
				}catch(IllegalArgumentException e){
				}
				Matcher match = Pattern.compile("^\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[0-1])$").matcher(str);
				if(!match.find())isError = true;
				if(isError){
					errors.reject(field, errorArgs, defaultMessage);
				}
			}
			return ret;
		}

		public static void rejectIfDateGreaterThanToday(
				DateTime date1,
				Errors errors, String field, String defaultMessage){
			DateTime curTime = new DateTime();
			DateTime now = new DateTime(curTime.getYear(),curTime.getMonthOfYear(), curTime.getDayOfMonth(),0,0);//년월일만 비교하도록.
			if(date1 == null || !date1.isBefore(now))errors.reject(field, defaultMessage);
		}
		public static void rejectIfDateLesserThanToday(
				DateTime date1,
				Errors errors, String field, String defaultMessage){
			DateTime curTime = new DateTime();
			DateTime now = new DateTime(curTime.getYear(),curTime.getMonthOfYear(), curTime.getDayOfMonth(),0,0);//년월일만 비교하도록.
			if(date1 == null || !date1.isAfter(now))errors.reject(field, defaultMessage);
		}
		
		//시작일이 종료일보다 이후이면 reject
		public static void rejectIfDateGreaterThan(
				DateTime date1, DateTime date2,
				Errors errors, String field, String defaultMessage){
			rejectIfDateGreaterThan(date1, date2, errors, field, null, defaultMessage);
		}
		public static void rejectIfDateGreaterThan(
				DateTime date1, DateTime date2,
				Errors errors, String field, Object[] errorArgs, String defaultMessage){
			if(date1 == null || date2 == null){
				errors.reject(field, errorArgs, defaultMessage);
			}else{
				if(!date2.isEqual(date1) && !date2.isAfter(date1)){
					errors.reject(field, errorArgs, defaultMessage);
				}
			}
			
		}

		//시작일이 종료일보다 같거나 이후이면 reject
		public static void rejectIfDateEqualOrGreaterThan(
				DateTime date1, DateTime date2,
				Errors errors, String field, String defaultMessage){
			rejectIfDateEqualOrGreaterThan(date1, date2, errors, field, null, defaultMessage);
		}
		public static void rejectIfDateEqualOrGreaterThan(
				DateTime date1, DateTime date2,
				Errors errors, String field, Object[] errorArgs, String defaultMessage){
			if(date1 == null || date2 == null){
				errors.reject(field, errorArgs, defaultMessage);
			}else{
				if(!date2.isAfter(date1)){
					errors.reject(field, errorArgs, defaultMessage);
				}
			}
			
		}

	}
