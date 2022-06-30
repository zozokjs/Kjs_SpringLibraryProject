package com.kjs.library.service.common;

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
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ibm.icu.util.ChineseCalendar;

//날짜 관련 공통 서비스 모음
@Service
public class DateCommonService {

	/**
	 *  2022-06-23 17:21:59.959571 등의 LocalDateTime 형식으로 오는 것을
	 *  2022년 6월 23일 String 형식으로 바꿈
	 * **/
	public static String 날짜포맷변경(LocalDateTime givenDate) throws ParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		
		//포맷 적용
		String lend2 = givenDate.format(formatter); 
		
		//System.out.println(lend2);
		
		return lend2;
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
	
	
	//미완성
	public String 반납예정날짜() throws Exception {
		
		//오늘 날짜 가져옴
		LocalDate now = LocalDate.now(); 
		//날짜 포맷 바꿈 20010101
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		//포맷 적용
		String lendDate = now.format(formatter); 
		//String lendDate = "20220801";
		//14일 더함
		String returnPlanDate = 날짜더하기(lendDate, 14);
		
		System.out.println("최초 반납 예정일 > "+returnPlanDate);
		
		boolean isSunday = 일요일이다(returnPlanDate);
		boolean isHoliday = 공휴일이다(returnPlanDate);
		
		/**
		 * 1. 대출일에서 14일 더함
		 * 2. 그 날짜가 주말이거나 공휴일인 경우 그 다음 날이 반납 예정일..
		 * 그런데 그 다음 날도 주말이거나 공휴일일 때는?
		 * 빙글빙글 돌면서 확인해서 true나 false를 리턴해야 함... 
		 * 
		 * **/
			
		if(일요일이다(returnPlanDate) || 공휴일이다(returnPlanDate)) {
			System.err.println("주말이거나 공휴일이라서 날짜를 1일 더 합니다.");
			returnPlanDate = 날짜더하기(returnPlanDate,1);
		}
			

		System.out.println("수정된 반납 예정일 > "+returnPlanDate);
		return returnPlanDate;
	}
	
	
	//날짜 더하기
	//strDate를 가져와서 포맷하고 특정 일 수를 더함
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

	
	//특정 날짜가 휴일인지를 확인함
	//음력을 양력으로 변경
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
	
	//특정 년도의 공휴일 배열을 리턴함(주말 제외)
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
	
	//givenDate가 주어지면 그날이 일요일인지 리턴함
	public boolean 일요일이다(String givenDate) throws Exception {         

		//1. LocalDate 생성        
		LocalDate localDate = LocalDate.of(2022, 06, 26);    
		
		// 2. DayOfWeek 객체 구하기        
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();        
		
		// 3. 숫자 요일 구하기        
		int dayOfWeekNumber = dayOfWeek.getValue();       
		
		// 4. 숫자 요일 출력        
		//System.out.println(dayOfWeekNumber);  // 6 - 토요일, 7- 일요일
		
		if(dayOfWeekNumber == 7) {
			return true;
		}else {
			return false;
		}
	}

	
	//반드시 20201010 형식으로 들어와야 함
	public boolean 공휴일이다(String givenDate) {
		
		//20201010을 2020으로 자르기
		String yearFromGivenDate = givenDate.substring(0, 4); 
		
		//주어진 년도의 공휴일 세팅(주말 제외) 
		Set<String> holidaySet= holidayArray(yearFromGivenDate);
		
		
		
		boolean isHoliday= holidaySet.contains(givenDate);
		/*
		if(isHoliday == true) {
			System.out.println("해당 날짜는 공휴일에 포함됩니다.");
		}else{
			System.out.println("해당 날짜는 공휴일 아님.");
		}*/
		return isHoliday;
	}

	
}
