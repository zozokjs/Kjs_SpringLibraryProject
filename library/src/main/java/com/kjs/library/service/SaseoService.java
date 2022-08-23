package com.kjs.library.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.book.BookRepository;
import com.kjs.library.domain.book.Samebook;
import com.kjs.library.domain.book.SamebookRepository;
import com.kjs.library.handler.aop.ex.CustomApiException;
import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.book.BookRegistrationDto;
import com.kjs.library.web.dto.book.BookRegistration_kdcDto;
import com.kjs.library.web.dto.book.BookUpdateDto;
import com.kjs.library.web.dto.book.BookUpdate_kdcDto;
import com.kjs.library.web.dto.book.ImageDto;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaseoService {

		private final CommonService commonService;
		private final BookRepository bookRepository;
		private final SamebookRepository sameBookRepository;
		private final SaseoSelectService saseoSelectService;
        		
		// SAVE 책 등록
		@Transactional
		public void 책등록(BookRegistrationDto bookRegistrationDto, PrincipalDetails principalDetails) {                       
			String imageFileName = "";
			
			ImageDto imageDto = new ImageDto();
			
			//첨부한 이미지가 없다면 
			if( bookRegistrationDto.getFile().isEmpty()) {
				imageFileName ="noTitleImage.jpg";
			}else {
				imageDto.setFile(bookRegistrationDto.getFile());
				imageFileName = commonService.사진저장(imageDto, "imageTitle");
			}
			
			//같은 책 남은 권 수
			bookRegistrationDto.setRemainAmount("0");
			//같은 책 등록된 권 수
			bookRegistrationDto.setTotalAmount("0");
			
			Book book = bookRegistrationDto.toEntity(imageFileName);
			
			//책 정보 입력한 사람
			int loginedId = principalDetails.getUser().getId();
			book.setRegistrationUserId(String.valueOf(loginedId));
			//책 정보 수정한 사람
			book.setEditedUserId("");
			
			//책 사용 상태 True
			book.setUseState(true);
			
			bookRepository.save(book);
		}
		
		
		// SAVE 책 청구기호 최초 등록
		@Transactional
		public void 책청구기호등록(int bookId, BookRegistration_kdcDto bookRegistration_kdcDto, PrincipalDetails principalDetails) {                       
			
			//넘어온 청구 기호 출력
		//	System.out.println("-------------------------");
			//System.out.println(bookRegistration_kdcDto); //BookRegistration_kdcDto(bookId=1, kdcCallSign=7,8,9)
			//System.out.println(bookRegistration_kdcDto.getKdcCallSign());
			
			//1. Front에서 넘어온 청구기호 묶음
			String kdcCallSignList = bookRegistration_kdcDto.getKdcCallSign(); // 2,3,4
			
			//2. Front에서 넘어온 청구기호 묶음을 ,로 나눠서 배열에 세팅
			String[] array = kdcCallSignList.split(",");
			
			//3. bookId
			Samebook sameBook = new Samebook();
			Book book = new Book();
			book.setId(bookId);
			
			bookRegistration_kdcDto.setBook(book); //bookId 세팅
			
			for(int i=0;i<array.length;i++) {
					sameBook = bookRegistration_kdcDto.toEntity(bookRegistration_kdcDto.getBook(), array[i]);
					sameBook.setLendState(false); //대여 상태를 false로 세팅
					sameBook.setUseState(true); //청구기호 사용 상태를 true로 세팅(추후 사서에 의해 제거될 시, false로 세팅됨)
					sameBookRepository.save(sameBook);
			}
		}
		
		
		// UPDATE 책 수정
		@Transactional
		public Book 책수정(int bookId, BookUpdateDto bookUpdateDto, String loginedId) {                       
			
			String imageFileName = "";
			
			Book bookEntity = bookRepository.findById(bookId).orElseThrow(()->{
				return new CustomValidationApiException("찾을 수 없는 책입니다. 수정 불가");
			});
			
			ImageDto imageDto = new ImageDto();
			
			//이미지가 변경되지 않는다면 null이 표시되고... 기본 이미지가 저장됨
			//null이더라도 기존의 이미지와 같다면 저장하지 않아야 함. 
			
			/*
			System.out.println("기존 거");
			System.out.println(bookEntity.getTitleImageUrl());
			System.out.println("바뀐 거");
			System.out.println(bookUpdateDto.getTitleImageUrl());
			System.out.println("첨부한 파일 이름");
			System.out.println(bookUpdateDto.getFile().getOriginalFilename());
			//첨부한 파일 이름
			//bookUpdateDto.getFile().getOriginalFilename();
		 	*/
			
			//파일을 첨부 안 했음
			if(bookUpdateDto.getFile() == null || bookUpdateDto.getFile().isEmpty() ) {
				System.out.println("이미지가 변경되지 않았으므로 유지합니다.");
				/*
				//기존 것이 noTitleImage라면 저장 안함
				if(bookEntity.getTitleImageUrl().equals("noTitleImage.jpg")) {
					System.out.println("----------------------");
					System.out.println("첨부 안 했는데 기존 것이 noImageTitle이라서 저장 안함");
				}
				//기존 것과 noTitleImage가 아니라면 기본 이미지를 저장함
				else {
					System.out.println("----------------------");
					System.out.println("첨부 안 했는데 기존 것이 존재하므로 기존 것을 유지함");
				}
				*/
			}
			//파일을 첨부 했음
			else {
				imageDto.setFile(bookUpdateDto.getFile());
				imageFileName = commonService.사진저장(imageDto, "imageTitle");
				
				//System.out.println(imageFileName);
				
				//변경된 이미지 이름을 저장함.
				bookEntity.setTitleImageUrl(imageFileName);
				
				System.out.println("이미지가 변경 되었으므로 새로 저장합니다.");
			}
			
			
			bookEntity.setEditedUserId(loginedId);
			bookEntity.setTitle(bookUpdateDto.getTitle());
			bookEntity.setWriter(bookUpdateDto.getWriter());
			bookEntity.setBindType(bookUpdateDto.getBindType());
			bookEntity.setPage(bookUpdateDto.getPage());
			bookEntity.setLanguage(bookUpdateDto.getLanguage());
			bookEntity.setPrice(String.valueOf(bookUpdateDto.getPrice()));
			bookEntity.setPublishDate(bookUpdateDto.getPublishDate());
			bookEntity.setDeliveryState(bookUpdateDto.getDeliveryState());
			bookEntity.setPublish(bookUpdateDto.getPublish());
			bookEntity.setKdcTable(bookUpdateDto.getKdcTable());
			bookEntity.setKdcCallSignFamily(bookUpdateDto.getKdcCallSignFamily());
			bookEntity.setIsbn(bookUpdateDto.getIsbn());
			bookEntity.setIsbnSet(bookUpdateDto.getIsbnSet());
			bookEntity.setContents(bookUpdateDto.getContents());
			//System.out.println(bookEntity);
			
			return bookEntity; //이후 더티 채킹 발생하여 업데이트 됨...
		}
		
		
		// UPDATE 책 청구기호 수정
		@Transactional
		public List<Samebook> 책청구기호수정(int bookId, BookUpdate_kdcDto bookUpdate_kdcDto, String loginedId) {
			
			/** 1. 유효성 체크*/
			Book bookEntity = bookRepository.findById(bookId).orElseThrow(()->{
				return new CustomValidationApiException("찾을 수 없는 책 입니다. 책을 찾을 수 없으므로 청구기호 수정이 불가능합니다.");
			});
			
			/** 2. bookId 기준으로 samebook가져와서 영속화 */
			List<Samebook> samebookEntity = sameBookRepository.findBybookid(bookId); 
			
			/** 3. samebook에 저장된 청구기호 갯수 세팅*/
			int 기존kdc크기 = samebookEntity.size();
			
			/** 4. Front에서 DTO에 담겨 넘어온 청구기호 꺼냄 */
			List <String> kdcCallSignList = bookUpdate_kdcDto.getKdcCallSign();
			
			
			
			
			/** 5. List에 공백이 포함된 경우 공백제거 */
			List<String> kdcCallSignList_공백제거 = new ArrayList<>();
			if(  !(CollectionUtils.isEmpty(kdcCallSignList)) ) {
				
				System.out.println("kdcCallSignList 길ㅇ "+kdcCallSignList.size());
				for (int i = 0; i < kdcCallSignList.size(); i++) {
					
					//i번째 항목이 공백이 아닐 때만 add
					if(!kdcCallSignList.get(i).equals("")) {
						kdcCallSignList_공백제거.add(kdcCallSignList.get(i));
						//System.out.println("공백이 없습니다." + i);
					}
				}
			}else {
				System.out.println("kdcCallSignList(Front에서 넘어온 samebook 값)이 null이라서 공백 제거하지 않습니다.");
			}
			
			/**6. DTO에 담겨 온 청구기호 갯수 세팅*/
			int 변경후kdc크기 = kdcCallSignList_공백제거.size(); 
			
			/** 7. bookId 세팅*/
			bookEntity.setId(bookId);
			
			
			System.err.println("기존kdc 크기 "+기존kdc크기);
			System.err.println("변경후kdc크기 "+변경후kdc크기);
			
			
			/**9. 값 변경 시작 */
			for(int index = 0; index < 변경후kdc크기; index++) {
				
				//System.out.println( index+"번째 청구기호 : "+kdcCallSignList_공백제거.get(index));

				//영속화된 객체에 새 것이 추가되는 경우
				/**기존 samebook에 새 것이 추가될 때*/
				if(변경후kdc크기 > 기존kdc크기) {
					
					/**기존 samebook부터 업데이트*/
					if( index < 기존kdc크기) {
						//기존 청구기호를 업데이트합니다.
						samebookEntity.get(index).setBook(bookEntity);
						samebookEntity.get(index).setKdcCallSign(kdcCallSignList_공백제거.get(index));
						samebookEntity.get(index).setUseState(true);
					}else {
						/** 추가된 것 SAVE*/
						//새 청구기호를 추가합니다.
						Samebook sameBook = new Samebook(bookEntity, kdcCallSignList_공백제거.get(index));
						sameBook.setUseState(true);
						sameBookRepository.save(sameBook);
						
					} //end of if
					
				}
				/**기존과 새 것의 크기가 동일할 때*/
				else if(변경후kdc크기 == 기존kdc크기) {
					//기존 청구기호를 업데이트합니다.
					samebookEntity.get(index).setBook(bookEntity);
					samebookEntity.get(index).setKdcCallSign(kdcCallSignList_공백제거.get(index));
				}	
				/** 변경후kdc크기가 0이 아니면서(단 1개라도 변경할 청구기호가 있을 때)
				 * 기존kdc 크기보다 변경후kdc 크기가 작을 때
				 * 
				 * 예) 기존kdc크기(기존에 등록된 청구기호 갯수)가 3개이고
				 * 기존에 등록된 청구기호를 모두 삭제하려는 경우에는
				 * 업데이트에서 제외됨.
				 */
				else if( (변경후kdc크기 != 0) && (변경후kdc크기 < 기존kdc크기) ){
					
					/**기존 것부터 업데이트*/
					if( index < 변경후kdc크기) {
						//System.out.println("수정된 id "+samebookEntity.get(index).getId());
						//기존 청구기호를 업데이트합니다.
						samebookEntity.get(index).setBook(bookEntity);
						samebookEntity.get(index).setKdcCallSign(kdcCallSignList_공백제거.get(index));
						samebookEntity.get(index).setUseState(true);
					}
				}//end of else if
			} //end of for
			
			/** 10. Front에서 넘어온 삭제할 SamebookId를 가져와서 삭제 처리*/
			List<String> deletSamebookIdList = bookUpdate_kdcDto.getDeleteSamebookId();
			
			//System.out.println("제거할 청구기호 길이 : "+deletSamebookIdList.size());
			
			/** 11. deletSamebookIdList에 공백이 포함된 경우 공백제거 */
			List<String> deletSamebookIdList_공백제거 = new ArrayList<>();
			if(  !(CollectionUtils.isEmpty(deletSamebookIdList)) ) {
				
				System.out.println(" deletSamebookIdList은 null 아님");
				
				//if(deletSamebookIdList_공백제거.size() != 0) {
					for (int i = 0; i < deletSamebookIdList.size(); i++) {
						
						System.out.println(i+"번째 값 "+deletSamebookIdList.get(i));
						
						//i번째 항목이 공백이 아닐 때만 add
						if(!deletSamebookIdList.get(i).equals("")) {
							deletSamebookIdList_공백제거.add(deletSamebookIdList.get(i));
							//System.out.println("공백이 없습니다." + i);
						}
					}
				//}
			}else {
				System.out.println("deletSamebookIdList가 null이라서 공백 제거하지 않습니다.");
			}
			
			System.out.println(" 제거할 청구기호 길이 : "+deletSamebookIdList_공백제거.size());
			
			
			
			
			if(deletSamebookIdList_공백제거 != null) {
				for (int i = 0; i < deletSamebookIdList_공백제거.size(); i++) {
					System.err.println("제거할 SamebookId(useState변경) "+deletSamebookIdList_공백제거.get(i));
					
					samebookUseState변경(  Integer.parseInt(deletSamebookIdList_공백제거.get(i))    );
				}
			}
			
			
			return samebookEntity;
		}
		
		
		//Update 
		/**
		 * Samebook 테이블의 useState(사용상태)를 false로 변경
		 * */
		@Transactional
		public Samebook samebookUseState변경(int samebookId) {
			
			Samebook samebookEntity = sameBookRepository.findById(samebookId).orElseThrow();
			samebookEntity.setUseState(false);
			return samebookEntity;
			
		}
		
		
		//UPDATE 책 1개의 remainAmount 수정
		@Transactional
		public Book remainAmount수정(int bookId, int remainAmount) {
			
			//System.out.println("남은 양 >"+remainAmount);
			
			Book bookEntity = saseoSelectService.bookSelectOne(bookId);
			
			bookEntity.setRemainAmount(String.valueOf(remainAmount));
			//bookEntity
			
			return bookEntity;
		}
		
		
		
		
		// UPDATE [청구기호 최초 등록 시] Book 테이블 업데이트를 위한 함수
		@Transactional
		public Book totalAmountSave(int bookId, int totalAmount) {
			
			Book bookEntity = saseoSelectService.bookSelectOne(bookId);
			
			bookEntity.setTotalAmount(String.valueOf(totalAmount));
			bookEntity.setRemainAmount(String.valueOf(totalAmount));
			
			return bookEntity;
		}
		
		
		// UPDATE [청구기호 수정 시] Book 테이블 업데이트를 위한 함수
		@Transactional
		public Book totalAmountUpdate(int bookId, BookUpdate_kdcDto bookUpdate_kdcDto) {
			
			//최종 변경된 samebook 갯수가 저장될 공간
			int samebookSize = 0;
			
			//변경된 samebook 갯수
			List <String> kdcCallSignList = bookUpdate_kdcDto.getKdcCallSign();
			
			/**List에 공백이 포함된 경우 공백제거 */
			List<String> kdcCallSignList_공백제거 = new ArrayList<>();
			
			//kdcCallSignList가 null 아닐 때
			if( !(CollectionUtils.isEmpty(kdcCallSignList)) ) {
				
				if(kdcCallSignList.size() != 0 ) {
					for (int i = 0; i < kdcCallSignList.size(); i++) {
						//i번째 항목이 공백이 아닐 때만 add
						if(!kdcCallSignList.get(i).equals("")) {
							kdcCallSignList_공백제거.add(kdcCallSignList.get(i));
							//System.out.println("공백이 없습니다." + i);
						}
					}
				}
				samebookSize = kdcCallSignList_공백제거.size();
			}
			
			/** 1. 현재 book 테이블의 remainAmount를 가져 옴*/
			Book bookEntity = saseoSelectService.bookSelectOne(bookId);
			
			bookEntity.setTotalAmount(String.valueOf(samebookSize));
			bookEntity.setRemainAmount(String.valueOf(samebookSize));
			
			return bookEntity;
		}

		
		// UPDATE 
		//책 삭제시 Book 테이블의 useState를 false로 변경
		@Transactional
		public Book 책삭제(int bookId) {
				
			Book bookEntity = bookRepository.findById(bookId).orElseThrow();
			bookEntity.setUseState(false);
			
			return bookEntity;
		}
			
			
		
}
