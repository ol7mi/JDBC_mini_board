package main;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import dao.MiniBoardDAO;
import dto.BoardDTO;
import dto.MiniBoardDTO;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MiniBoardDAO dao = new MiniBoardDAO();
		try {
			while(true) {
				System.out.println("<< Mini Board >>");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("0. 시스템 종료");
				int menu = Integer.parseInt(sc.nextLine());
				
				if(menu == 1) {
					System.out.println("<< 로그인 >>");
					
					System.out.println("ID 를 입력하세요");
					String id = sc.nextLine();
					System.out.println("PW 를 입력하세요");
					String pw = sc.nextLine();
					
					MiniBoardDTO dto = new MiniBoardDTO(id,pw,null,null);
					boolean result = dao.userLogin(dto);
					if(result) {
						System.out.println("로그인 성공!");
						break;
					}else {
						System.out.println("로그인 실패ㅠㅠ");
					}

					
				} else if(menu == 2) {
					System.out.println("회원가입");
					
					System.out.println("ID :");
					String id = sc.nextLine();
					System.out.println("PW :");
					String pw = sc.nextLine();
					System.out.println("이름 :");
					String username = sc.nextLine();
					
					MiniBoardDTO dto = new MiniBoardDTO(id,pw,username,null);
					int result =dao.insert(dto);
					
					if(result>0) {
						System.out.println("회원가입 완료!");
					}else {
						System.out.println("회원가입 실패ㅠㅠ");
					}
					
				} else if (menu == 0) {
					System.out.println("시스템을 종료 합니다.");
				} else {
					System.out.println("메뉴를 다시 선택해주세요. ");
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			while(true) {
				System.out.println("<< Main Board >>");
				System.out.println(dao.loggedInUserId + "님 안녕하세요. ");
				System.out.println("1. 글 작성하기"); //글 내용 
				System.out.println("2. 글 목록 보기"); // 글 번호, 작성자, 글 내용, 작성날짜 [yy.MM.dd]
				System.out.println("3. 글 검색 하기"); // 작성자, 글 내용을 대상으로 검색 
				System.out.println("4. 글 수정 하기"); // 글 번호 기반으로 수정 - 작성자, 내용, 날짜를 수정
				System.out.println("5. 글 삭제 하기"); // 글 번호 기반으로 삭제 
				System.out.println("0. 시스템 종료");
				int menu = Integer.parseInt(sc.nextLine());
				
				if(menu == 1) {
					System.out.println("글 작성하기");
					String text = sc.nextLine();
					int test = dao.writer(text);
					if(test > 0) {
						System.out.println("작성 완료");
					}else {
						System.out.println("작성 실패");
					}
				}else if(menu == 2) {
					System.out.println("글 목록 보기");
					ArrayList<BoardDTO> boardDTO = dao.listAll();
					for(BoardDTO b:boardDTO) {
						System.out.println(b.getSeq() + "\t"+b.getWriter() + "\t"+b.getContents() + "\t"+b.writeDate() + "\t");
					}
					
				}else if(menu == 3) {
					System.out.println("작성자, 글 내용을 대상으로 검색 :");
					String result = sc.nextLine();
					ArrayList<BoardDTO> boardDTO= dao.search(result);
					for(BoardDTO b:boardDTO) {
						System.out.println(b.getSeq() + "\t"+b.getWriter() + "\t"+b.getContents() + "\t"+b.writeDate() + "\t");
					}
				}else if(menu == 4) {
					System.out.println("글 수정 하기");
					//글 번호 기반으로 수정 - 작성자, 내용, 날짜를 수정
					System.out.println("글 번호 : ");
					int id = Integer.parseInt(sc.nextLine());
					boolean isIdExists = dao.isIdExist(id);
					
					if(isIdExists) {
						System.out.println("작성자 :");
						String writer = sc.nextLine();
						System.out.println("내용 :");
						String contents = sc.nextLine();
						
						BoardDTO dto = new BoardDTO(id, writer,contents, null);
						int result = dao.update(dto);
						System.out.println(result);
						if(result > 0) {
							System.out.println("수정 완료");
							
						}else {
							System.out.println("실패..");
						}
					}else {
						System.out.println("해당 ID가 없습니다. ");
					}
					
					//BoardDTO dto = new BoardDTO(seq,writer,contents,write_date);
					//dao.update(dto);
				}else if(menu == 5) {
					System.out.println("글 번호로 삭제 : ");
					int id = Integer.parseInt(sc.nextLine());
					int result= dao.delete(id);
					if(result> 0) {
						System.out.println("삭제 완료!");
					}else {
						System.out.println("삭제 실패!");
					}
				}else if(menu == 0) {
					System.out.println("시스템을 종료합니다.");
					System.exit(0);;
				}else {
					System.out.println("메뉴를 다시 선택해주세요. ");
				}
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
