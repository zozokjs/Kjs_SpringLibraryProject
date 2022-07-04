package com.kjs.library.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.internal.build.AllowSysOut;
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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaseoService {

		private final CommonService commonService;
		private final BookRepository bookRepository;
		private final SamebookRepository sameBookRepository;
		
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
			
			bookRepository.save(book);
		}
		
		
		
		// SAVE 책 청구기호 최초 등록
		@Transactional
		public void 책청구기호등록(int bookId, BookRegistration_kdcDto bookRegistration_kdcDto, PrincipalDetails principalDetails) {                       
			
			//넘어온 청구 기호 출력
			System.out.println("-------------------------");
			System.out.println(bookRegistration_kdcDto); //BookRegistration_kdcDto(bookId=1, kdcCallSign=7,8,9)
			System.out.println(bookRegistration_kdcDto.getKdcCallSign());
			
			String kdcCallSignList = bookRegistration_kdcDto.getKdcCallSign(); // 2,3,4
			String[] array = kdcCallSignList.split(",");
			
			//출력				
			Samebook sameBook = new Samebook();
			
			Book book = new Book();
			book.setId(bookId);
			
			bookRegistration_kdcDto.setBook(book); //bookId 세팅
			
			sameBook.setLendState(false); //대여 상태를 false로 세팅
			
			for(int i=0;i<array.length;i++) {
					//sameBook.setUpdateDate(now.toString()); //수정 시간
					sameBook = bookRegistration_kdcDto.toEntity(bookRegistration_kdcDto.getBook(), array[i]);
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
				return new CustomValidationApiException("찾을 수 없는 책 입니다. 책을 찾을 수 없으므로 청구기호 수정 불가");
			});
			
			/** 2. bookId 기준으로 samebook가져와서 영속화 */
			List<Samebook> samebookEntity = sameBookRepository.findBybookid(bookId); 
			
			/** 3. samebook에 저장된 청구기호 갯수 세팅*/
			int 기존kdc크기 = samebookEntity.size();
			
			/** 4. Front에서 DTO에 담겨 넘어온 청구기호 꺼냄 */
			List <String> kdcCallSignList = bookUpdate_kdcDto.getKdcCallSign();
			
			/** 5. List에 공백이 포함된 경우 공백제거 */
			List<String> kdcCallSignList_공백제거 = new ArrayList<>();
			for (int i = 0; i < kdcCallSignList.size(); i++) {
				//i번째 항목이 공백이 아닐 때만 add
				if(!kdcCallSignList.get(i).equals("")) {
					kdcCallSignList_공백제거.add(kdcCallSignList.get(i));
					//System.out.println("공백이 없습니다." + i);
				}
			}
			
			/**6. DTO에 담겨 온 청구기호 갯수 세팅*/
			int 변경된kdc크기 = kdcCallSignList_공백제거.size(); 
			
			/** 7. bookId 세팅*/
			bookEntity.setId(bookId);
			
			
			System.err.println("기존kdc 크기 "+기존kdc크기);
			System.err.println("변경된kdc크기 "+변경된kdc크기);
			
			
			/**9. 값 변경 시작 */
			for(int index = 0; index < 변경된kdc크기; index++) {
				
				System.out.println( index+"번째 청구기호 : "+kdcCallSignList_공백제거.get(index));

				//영속화된 객체에 새 것이 추가되는 경우
				/**기존 samebook에 새 것이 추가될 때*/
				if(변경된kdc크기 > 기존kdc크기) {
					
					/**기존 samebook부터 업데이트*/
					if( index < 기존kdc크기) {
						//기존 청구기호를 업데이트합니다.
						samebookEntity.get(index).setBook(bookEntity);
						samebookEntity.get(index).setKdcCallSign(kdcCallSignList_공백제거.get(index));
					}else {
						/** 추가된 것 SAVE*/
						//새 청구기호를 추가합니다.
						Samebook sameBook = new Samebook(bookEntity, kdcCallSignList_공백제거.get(index));
						sameBookRepository.save(sameBook);
						
					} //end of if
					
				}
				/**기존과 새 것의 크기가 동일할 때*/
				else if(변경된kdc크기 == 기존kdc크기) {
					//기존 청구기호를 업데이트합니다.
					samebookEntity.get(index).setBook(bookEntity);
					samebookEntity.get(index).setKdcCallSign(kdcCallSignList_공백제거.get(index));
					
				}
				/**기존 것보다 더 적게 변경 될 때*/
				else if(변경된kdc크기 < 기존kdc크기){
					
					/**기존 것부터 업데이트*/
					if( index < 변경된kdc크기) {
						//System.out.println("수정된 id "+samebookEntity.get(index).getId());
						//기존 청구기호를 업데이트합니다.
						samebookEntity.get(index).setBook(bookEntity);
						samebookEntity.get(index).setKdcCallSign(kdcCallSignList_공백제거.get(index));
					}
				}//end of else if
			} //end of for
			
			/**Front에서 넘어온 삭제할 SamebookId를 가져와서 삭제 처리*/
			List<String> deletSamebookIdList = bookUpdate_kdcDto.getDeleteSamebookId();
			if(deletSamebookIdList != null) {
				for (int i = 0; i < deletSamebookIdList.size(); i++) {
					//System.err.println("삭제할 SamebookId "+deletSamebookIdList.get(i));
					sameBookRepository.deleteById(Integer.parseInt(deletSamebookIdList.get(i)));
				}
			}
			
			return samebookEntity;
		}
		
		
		// SELECT 책 전부 조회
		@Transactional(readOnly = true)
		// 서비스 단에는 select만 하더라도 Transactional을 걸어주는 게 좋다.
		// readonly True를 넣으면 읽기전용으로 인식하므로 jpa는
		// 영속성 컨텍스트 내의 변경 여부를 감시 및 감지 하지 않는다.
		public List<Book> bookSelect() {
			
			List<Book> bookList = bookRepository.findAll();
			
			//System.out.println("--------------------------");
			//System.out.println(bookList.get(0).getTitleImageUrl() );
			//System.out.println(bookList.get(1).getTitleImageUrl() );
			
			return bookList;
		}
		
		
		//Update 책 1개의 remainAmount 수정
		@Transactional
		public Book remainAmount수정(int bookId, int remainAmount) {
			
			System.out.println("남은 양 >"+remainAmount);
			
			Book bookEntity = bookSelectOne(bookId);
			
			bookEntity.setRemainAmount(String.valueOf(remainAmount));
			//bookEntity
			
			return bookEntity;
		}
		
		
		// SELECT 책 1개의 정보만 가져옴
		@Transactional(readOnly = true)
		public Book bookSelectOne(int id) {
		
			Book bookEntity = bookRepository.findById(id).orElseThrow(()->{
				throw new CustomApiException("해당하는 책이 존재하지 않습니다! 관리자에게 문의하셈");
			});
			
			return bookEntity;
		 }
		
		
		// SELECT 책 1개의 청구기호 정보만 가져옴		
		@Transactional(readOnly = true)
		public List<Samebook> sameBookSelectOne(int bookId) {
			
			//청구기호가 존재하지 않는다면 아무 조치도 취하지 않음
			//System.out.println("전달된 책 아이디 >"+bookId);
			
			List<Samebook> sameBookEntity = sameBookRepository.findBybookid(bookId);
			
			if(sameBookEntity.isEmpty()) {
				System.out.println("등록된 청구기호가 없음");
				
			}else {
				System.out.println("등록된 청구기호가 잇음");
				
			}
			return sameBookEntity;
		}

		
		// Update [청구기호 최초 등록 시] Book 테이블 업데이트를 위한 함수
		@Transactional
		public Book totalAmountSave(int bookId, int totalAmount) {
			
			Book bookEntity = bookSelectOne(bookId);
			
			bookEntity.setTotalAmount(String.valueOf(totalAmount));
			bookEntity.setRemainAmount(String.valueOf(totalAmount));
			
			return bookEntity;
		}
		
		
		// Update [청구기호 수정 시] Book 테이블 업데이트를 위한 함수
		@Transactional
		public Book totalAmountUpdate(int bookId, BookUpdate_kdcDto bookUpdate_kdcDto) {
			
			//변경된 samebook 갯수
			List <String> kdcCallSignList = bookUpdate_kdcDto.getKdcCallSign();
			
			/**List에 공백이 포함된 경우 공백제거 */
			List<String> kdcCallSignList_공백제거 = new ArrayList<>();
			for (int i = 0; i < kdcCallSignList.size(); i++) {
				//i번째 항목이 공백이 아닐 때만 add
				if(!kdcCallSignList.get(i).equals("")) {
					kdcCallSignList_공백제거.add(kdcCallSignList.get(i));
					//System.out.println("공백이 없습니다." + i);
				}
			}
			
			int samebookSize = kdcCallSignList_공백제거.size();
			
			/** 1. 현재 book 테이블의 remainAmount를 가져 옴*/
			Book bookEntity = bookSelectOne(bookId);
			
			bookEntity.setTotalAmount(String.valueOf(samebookSize));
			bookEntity.setRemainAmount(String.valueOf(samebookSize));
			
			return bookEntity;
		}
		
		
		
		@Transactional(readOnly = true)
		public boolean 청구기호수정가능하다(int bookId, BookUpdate_kdcDto bookUpdate_kdcDto ) {
			
			//bookId로 등록된 samebook 데이터 들고옴
			List<Samebook> samebookEntity = sameBookRepository.findBybookid(bookId); 
			
			//수정하려는 청구기호를 나눔
			List<Integer> samebookIdList = bookUpdate_kdcDto.getSamebookId();
			
			List<Boolean> result = sameBookRepository.editAbleKdcCallSign(samebookIdList);
			
			/* 값 체크
			for (int i = 0; i < result.size(); i++) {
				System.out.println("결과 "+result.get(i));
			}
			*/

			//result에 true 포함여부 확인
			boolean resultB = result.contains(true);
			System.out.println();
			if(resultB == true) {
				return false;
			}else {
				return true;
			}
			
		}
		
}
