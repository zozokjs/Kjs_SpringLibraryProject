package com.kjs.library.service.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Service;

import com.ibm.icu.util.ChineseCalendar;

import lombok.extern.slf4j.Slf4j;

//날짜 관련 공통 서비스 모음
@Slf4j
@Service
public class DateCommonService {

	/**
	 * 주어진 시간과 현재 시간을 비교
	 * @param timeAfter : 기준 시간(yyyy-MM-dd HH:mm:ss 형식), String
	 * @return 현재 시간보다 timeAfter가 늦으면 True 리턴
	 * @throws ParseException 
	 * 
	 * */
	public static boolean 현재시간보다늦음(String timeAfter) throws ParseException {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		//1. 넘어온 값을 Date 형식으로 변경
		Date dateAfter = simpleDateFormat.parse(timeAfter);
		
		//2. 현재 시간을 구함
		Date dateNow = new Date();
		
		//3. 비교함
		/** 202109.after( 202110 )  -> FALSE
		 *   202110.after( 202109 )  -> TRUE
		 *   dateNow는 dateAfter보다 이후일 때 True
		 * */
		if(dateNow.after(dateAfter)) {
			return false;
		}else {
			//dateNow보다 dateAfter가 빠르면 False
			return true;
		}
	}
	
	
	/**
	 * 주어진 날짜가 오늘인지 비교
	 * @param targetTime : 기준 시간(yyyy-MM-dd HH:mm:ss 형식), String
	 * @return targetTime가 오늘 날짜가 아니면 False 리턴
	 * @throws ParseException 
	 * 
	 * */
	public static boolean 오늘날짜다(LocalDateTime targetDate) throws ParseException {
		
		/**
		 * 1. Date로 오늘 날짜를 가져옴
		 * 2. yyyyMMdd 형태로 포맷 변경(Date -> String)
		 * 3. 비교를 위해 Date 형태로 변경(String -> Date)
		 * 
		 * 4. targetTime을 yyyyMMdd 형태로 포맷 변경(LocalDateTime -> Date)
		 * 5. 비교를 위해 Date 형태로 변경(Date -> String)
		 * 6. 비교를 위해 Date 형태로 변경(String -> Date)
		 * 7. 비교 
		 * */
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		
		//1. 오늘 날짜를 가져옴
		Date dateNowDate = new Date();
		
		//2. dateNowDate을 주어진 포맷으로 변경(Date -> String)
		String dateNowFormatted= simpleDateFormat.format(dateNowDate);
		log.info("2 : {}",dateNowFormatted); //20220909
		
		//3. Date 형태로 변경(String -> Date)
		Date dateNowDateParsed = simpleDateFormat.parse(dateNowFormatted);
		
		//4. targetTime의 포맷 변경(LocalDateTime -> Date)
		Date targetDate2  = Timestamp.valueOf(targetDate);
		
		//5. targetTime을 주어진 포맷으로 변경(Date -> String)
		String targetDateFormatted = simpleDateFormat.format(targetDate2);
		log.info("5 : {}",targetDateFormatted); //20220909
		
		//6. targetDateFormatted를 Date로 변경(String -> Date)
		Date targetDateParsed = simpleDateFormat.parse(targetDateFormatted);
		
		//7. 비교함
		/** 202109.after( 202110 )  -> FALSE
		 *   202110.after( 202109 )  -> TRUE
		 *   dateNowDate가 tragetTime보다 이후이거나 이전일 때(오늘 날짜가 아닐 때) False
		 * */
		if(dateNowDateParsed.after(targetDateParsed) || dateNowDateParsed.before(targetDateParsed)) {
			return false;
		}else {
			return true;
		}
	}
	
	
	/**
	 * 주어진 시간에서 특정 시간만큼 더함 값을 구함
	 * @param standardTime : 기준 시간(yyyy-MM-dd HH:mm:ss 형식), String
	 * @param hour : 더할 시간, int
	 * @return yyyy-MM-dd HH:mm:ss 형식의 String 
	 * @throws ParseException 
	 * */
	public static String 시간더하기(String standardTime, int hour) throws ParseException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		date = simpleDateFormat.parse(standardTime); //String > Date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour); //hour만큼 더함
		
		String afterHour_String = simpleDateFormat.format(calendar.getTime()); //포맷에 맞춤
		
		return afterHour_String;
	}
	
	
	/**
	 *  2022-06-23 17:21:59.959571 등의 LocalDateTime 형식으로 오는 것을
	 *  2022년 6월 23일 String 형식으로 바꿈
	 * **/
	public static String 날짜포맷변경(LocalDateTime givenDate) throws ParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		
		//포맷 적용
		String formatted = givenDate.format(formatter); 
		
		//System.out.println(lend2);
		
		return formatted;
	}
	
	/**
	 *  2022-06-23 17:21:59.959571 등의 LocalDateTime 형식으로 오는 것을
	 *  2022년 6월 23일 23시 21분 34초 String 형식으로 바꿈
	 * **/
	public static String 날짜포맷변경시간추가(LocalDateTime givenDate) throws ParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		
		//포맷 적용
		String formatted = givenDate.format(formatter); 
		
		//System.out.println(lend2);
		
		return formatted;
	}
	
	/**
	 * 20200623 등의 String 형식으로 오는 것을
	 * 2020년 6월 23일 형식으로 바꿈
	 * */
	public static String 날짜포맷변경2(String givenDate) {

		if(givenDate != null) {
			String year = givenDate.substring(0,4);
			String month = givenDate.substring(4,6);
			String day  = givenDate.substring(6,8);
			
			return year+"년 "+month+"월 "+day+"일";
		}else {
			return null;
		}
	}
	
	
	/**
	 * 연장된 반납 예정 날자를 구함
	 * given : String yyyyMMdd(기준 반납예정날짜)
	 * */
	public String 반납예정날짜_연장됨(String strDate) throws Exception {
		
		//1. 7일 더함
		String returnPlanDate = 날짜더하기(strDate, 7);
		
		//2. 7일 더한 값이 휴일일 때 가장 가까운 미래의 평일을 구함
		String extensionReturnPlanDate = 날짜구하기_평일(returnPlanDate);
		
		//System.out.println("연장된 반납 예정일 > "+extensionReturnPlanDate);
		return extensionReturnPlanDate;
	}
	
	
	/**
	 * 오늘부터 계산하여 반납 예정 날자를 구함
	 * */
	public String 반납예정날짜() throws Exception {
		
		//1. 오늘 날짜 가져옴
		LocalDate now = LocalDate.now(); 
		
		//2. 날짜 포맷 세팅(yyyyMMdd)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		//3. 날짜 포맷 적용(yyyyMMdd)
		String lendDate = now.format(formatter); 
		
		//4. 14일 더함
		String returnPlanDate = 날짜더하기(lendDate, 14);
		
		//System.out.println("최초 반납 예정일 > "+returnPlanDate);
		
		//5. 14일 더한 값이 휴일일 때 가장 가까운 미래의 평일을 구함
		String fixReturnPlanDate = 날짜구하기_평일(returnPlanDate);
		
		System.out.println("수정된 반납 예정일 > "+fixReturnPlanDate);
		return fixReturnPlanDate;
	}
	
	
	/**
	 * strDate(String yyyyMMdd)가 휴일인 경우 
	 * 해당 일자에서 가장 가까운 평일을 리턴
	 * */
	public String 날짜구하기_평일(String strDate) throws Exception {
		
		boolean 공휴일 = true;
		while(공휴일) {
			//System.out.println("기준 날짜 : "+strDate);
			if(일요일이다(strDate)  || 공휴일이다( strDate)  ) {
				strDate = 날짜더하기(strDate, 1);
				//System.out.println("변경된 날자 : "+strDate);
			}else {
				공휴일 = false;				
			}
		}
		return strDate;
	}
	
	/**
	 *strDate(String yyyymmdd)에 day(Integer)를 더한 값을 리턴
	 * */
	public String 날짜더하기(String strDate, int day) throws Exception{
		
		//날짜 포맷 정하기
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
		
		//달력 객체 들고옴
		Calendar cal = Calendar.getInstance();
		
		//strDate를 포맷으로 파싱
		Date dt = dtFormat.parse(strDate);
		
		//달력에 세팅
		cal.setTime(dt);
		
		cal.add(Calendar.DATE, day);
		
		return dtFormat.format(cal.getTime());
	}
	
	
	
	
	/**
	 * author : https://rangerang.tistory.com/71
	 * method : Lunar2Solar(), holidayArray(), SolarDays()
	 * **/
	
	static Set<String> holidaysSet = new HashSet<>();
    public static final int LD_SUNDAY = 7;
    public static final int LD_SATURDAY = 6;
    public static final int LD_MONDAY = 1;

	
	/**
	 *yyyymmdd를 양력으로 변경함 
	 * */
	public String Lunar2Solar(String yyyymmdd) {
		
		ChineseCalendar cc = new ChineseCalendar();
		
		if(yyyymmdd == null) {
			return null;
		}
		
		//trim으로 앞뒤 공백 제거
		String date = yyyymmdd.trim();
		
		if(date.length() != 8) {
			if(date.length() == 4) {
				date = date + "0101";
			}else if(date.length() == 6) {
				date = date + "01";
			}else if(date.length() > 8) {
				date = date.substring(0,8);
			}else {
				return null;
			}
		}
		
		cc.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(date.substring(0,4))+2637);
		cc.set(ChineseCalendar.MONTH,                   Integer.parseInt(date.substring(4,6))-1);
		cc.set(ChineseCalendar.DAY_OF_MONTH,   Integer.parseInt(date.substring(6)) );
		
		LocalDate solar = Instant.ofEpochMilli(cc.getTimeInMillis()).atZone(ZoneId.of("UTC")).toLocalDate();
		
		int y = solar.getYear();
		int m =solar.getMonth().getValue();
		int d = solar.getDayOfMonth();
		
		StringBuilder ret = new StringBuilder();
		ret.append(String.format("%04d", y));
        ret.append(String.format("%02d", m));
        ret.append(String.format("%02d", d));
		
		 
		return ret.toString();
	}
	

	/**
	 *특정 년도(yyyy)의 공휴일이면 배열을 리턴(주말 제외)
	 * */
	public Set<String> holidayArray(String yyyy){
	        holidaysSet.clear();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

	        // 양력 휴일
	        holidaysSet.add(yyyy+"0101");   // 신정
	        holidaysSet.add(yyyy+"0301");   // 삼일절
	        holidaysSet.add(yyyy+"0505");   // 어린이날
	        holidaysSet.add(yyyy+"0606");   // 현충일
	        holidaysSet.add(yyyy+"0815");   // 광복절
	        holidaysSet.add(yyyy+"1003");   // 개천절
	        holidaysSet.add(yyyy+"1009");   // 한글날
	        holidaysSet.add(yyyy+"1225");   // 성탄절

	        // 음력 휴일
	        String prev_seol = LocalDate.parse(Lunar2Solar(yyyy+"0101"), formatter).minusDays(1).toString().replace("-","");
	        holidaysSet.add(yyyy+prev_seol.substring(4));        // ""
	        holidaysSet.add(yyyy+SolarDays(yyyy, "0101"));  // 설날
	        holidaysSet.add(yyyy+SolarDays(yyyy, "0102"));  // ""
	        holidaysSet.add(yyyy+SolarDays(yyyy, "0408"));  // 석탄일
	        holidaysSet.add(yyyy+SolarDays(yyyy, "0814"));  // ""
	        holidaysSet.add(yyyy+SolarDays(yyyy, "0815"));  // 추석
	        holidaysSet.add(yyyy+SolarDays(yyyy, "0816"));  // ""


	        try {
	            // 어린이날 대체공휴일 검사 : 어린이날은 토요일, 일요일인 경우 그 다음 평일을 대체공유일로 지정

	            int childDayChk = LocalDate.parse(yyyy+"0505", formatter).getDayOfWeek().getValue();
	            if(childDayChk == LD_SUNDAY) {      // 일요일
	                holidaysSet.add(yyyy+"0506");
	            }
	            if(childDayChk == LD_SATURDAY) {  // 토요일
	                holidaysSet.add(yyyy+"0507");
	            }

	            // 설날 대체공휴일 검사
	            if(LocalDate.parse(Lunar2Solar(yyyy+"0101"),formatter).getDayOfWeek().getValue() == LD_SUNDAY) {    // 일
	                holidaysSet.add(Lunar2Solar(yyyy+"0103"));
	            }
	            if(LocalDate.parse(Lunar2Solar(yyyy+"0101"),formatter).getDayOfWeek().getValue() == LD_MONDAY) {    // 월
	                holidaysSet.add(Lunar2Solar(yyyy+"0103"));
	            }
	            if(LocalDate.parse(Lunar2Solar(yyyy+"0102"),formatter).getDayOfWeek().getValue() == LD_SUNDAY) {    // 일
	                holidaysSet.add(Lunar2Solar(yyyy+"0103"));
	            }

	            // 추석 대체공휴일 검사
	            if(LocalDate.parse(Lunar2Solar(yyyy+"0814"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
	                holidaysSet.add(Lunar2Solar(yyyy+"0817"));
	            }
	            if(LocalDate.parse(Lunar2Solar(yyyy+"0815"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
	                holidaysSet.add(Lunar2Solar(yyyy+"0817"));
	            }
	            if(LocalDate.parse(Lunar2Solar(yyyy+"0816"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
	                holidaysSet.add(Lunar2Solar(yyyy+"0817"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return holidaysSet;
	    }
	 
	private String SolarDays(String yyyy, String date){
	    return Lunar2Solar(yyyy+date).substring(4);
	}
	
	/**
	 *givenDate가 일요일이면 True 
	 *givenDate = 20201011형식
	 * */
	public boolean 일요일이다(String givenDate) throws Exception {         
		
		//1. givenDate 자르기
		String year = givenDate.substring(0,4);
		String month = givenDate.substring(4,6);
		String day = givenDate.substring(6,8);
		
		//2. LocalDate 생성        
		LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));  
		
		//3. DayOfWeek 객체 구하기        
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();        
		
		//4. 숫자 요일 구하기        
		int dayOfWeekNumber = dayOfWeek.getValue();       
		
		//5. 검증
		if(dayOfWeekNumber == 7) {
			return true;
		}else {
			return false;
		}
	}

	
	/**
	 *givenDate가 공휴일이면 True 
	 *givenDate = 20201011형식
	 * */
	public boolean 공휴일이다(String givenDate) {
		
		//20201010을 2020으로 자르기
		String yearFromGivenDate = givenDate.substring(0, 4); 
		
		//주어진 년도의 공휴일 세팅(주말 제외) 
		Set<String> holidaySet= holidayArray(yearFromGivenDate);
			
		/*		Iterator<String> iter = holidaySet.iterator();
				while(iter.hasNext()) {
					System.out.println(";;----------------------");
					System.out.println(iter.next());
				}
		*/			
		
		boolean isHoliday= holidaySet.contains(givenDate);
		
		if(isHoliday == true) {
			System.out.println("해당 날짜는 공휴일에 포함됩니다.");
		}else{
			System.out.println("해당 날짜는 공휴일 아님.");
		}
		return isHoliday;
	}

	
}
