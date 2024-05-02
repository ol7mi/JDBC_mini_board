* JDBC 최종 문제 조건

0. 프로젝트 생성
프로젝트 이름 : JDBC_mini_board

1. DBMS 계정 생성
 > 계정명 : kedu01
 > 계정 권한 : 접속 및 자원

2. 테이블 생성
 1> 테이블 이름 : members 
   - id varchar primary key,
   - pw varchar(128) not null,      <-- 암호화 패스워드를 저장하기 위함
   - name varchar(30) not null,
   - join_date timestamp not null    <-- 입력 아니고 sysdate 로 처리

 2> 테이블 이름 : board
   - seq number primary key,
   - writer varchar not null, 
   - contents varchar(300) not null,
   - write_date timestamp not null   <-- 입력 아니고 sysdate 로 처리

 3> 시퀀스 생성 : board_seq
   - start with 1 increment by 1 nocache nomaxvalue
------------------------------------------------------------------


3. View 및 기능 구성

* Main 클래스

* 첫 화면
<< Mini Board 인증 >>
1. 로그인
2. 회원가입
0. 시스템 종료

* 로그인에 성공 후 화면.
<< Mini Board >>
1. 글 작성하기  ( 글 내용 ) 
2. 글 목록 보기 ( 글 번호 , 작성자 , 글 내용 , 작성날짜 [yy.MM.dd] )
3. 글 검색 하기 ( 작성자, 글 내용을 대상으로 검색 )
4. 글 수정 하기 ( 글 번호 기반으로 수정 - 작성자, 내용, 날짜를 수정 ) 
5. 글 삭제 하기 ( 글 번호 기반으로 삭제 )
0. 시스템 종료

------------------------------------------------------------------

* 제작 조건

* 프로젝트 개발 필수 옵션
 - Connection 관리는 DBCP를 적용하세요.
 - DB관련 모든 자원은 반드시 close 되어야 합니다.
 - 회원가입 및 로그인 시 패스워드는 암호화 되어 처리되어야 합니다.
 - SQL Injection 공격으로 부터 안전해야 합니다.
 - MVC 패턴을 적용하여 개발해주세요.

* 프로젝트 개발 선택 옵션
 - 난이도 조건 상
   > 서버 클라이언트 모델로 개발 
   > 멀티쓰레딩 서버로 개발

 - 난이도 조건 중 
   > 서버 클라이언트 모델로 개발 

 - 난이도 조건 하
   > 네트워크 빼고 1인 프로젝트 개발
