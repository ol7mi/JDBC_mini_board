package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import dto.BoardDTO;
import dto.MiniBoardDTO;
//static 변수 : 로그인 하면 로그인 한 내가 누군지를 
public class MiniBoardDAO {
	public static String loggedInUserId = null; //로그인 ID
	
	private static BasicDataSource bds = new BasicDataSource(); 
	
	public MiniBoardDAO() {
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kedu1");
		bds.setPassword("kedu1");
		
		bds.setInitialSize(20); 
	}
	private Connection getConnection () throws Exception{
		return bds.getConnection();
	}
	//로그인
	public boolean userLogin (MiniBoardDTO dto) throws Exception{
		String sql = "select * from members where id = ? and pw = ?";
		System.out.println(sql);
		try(
				Connection con = getConnection();
				PreparedStatement stat = con.prepareStatement(sql);
			){
				stat.setString(1, dto.getId());
				stat.setString(2, dto.getPw());
				loggedInUserId = dto.getId();
				try(
						ResultSet result = stat.executeQuery();
				
				){
					return result.next();
					
				}
			}
	}
	//회원가입 
	public int insert (MiniBoardDTO dto) throws Exception{
		String sql = "insert into members values(?,?,?,sysdate)";
		System.out.println(sql);
		try(
			Connection con = getConnection();
			PreparedStatement stat = con.prepareStatement(sql);		
		){
			stat.setString(1, dto.getId());
			stat.setString(2, dto.getPw());
			stat.setString(3, dto.getUsername());
			return stat.executeUpdate();
		}
	}
	
	// 글 작성하기
	public int writer (String target) throws Exception{
		String sql = "insert into board values(members_seq.nextval, '"+loggedInUserId+"',?, sysdate)";
		try(
			Connection con = getConnection();
			PreparedStatement stat = con.prepareStatement(sql);		
		){
			stat.setString(1, target);
			return stat.executeUpdate();
		}
	}
	public ArrayList<BoardDTO> listAll () throws Exception{
		String sql = "select * from board";
		try(
				Connection con = getConnection();
				PreparedStatement stat = con.prepareStatement(sql);	
				ResultSet rs= stat.executeQuery();
			){
				ArrayList<BoardDTO> list = new ArrayList();
				while(rs.next()) {
					int seq = rs.getInt("seq");
					String writer =rs.getString("writer");
					String contents =rs.getString("contents");
					Timestamp write_date =rs.getTimestamp("write_date");
					BoardDTO board = new BoardDTO(seq,writer,contents,write_date);
					list.add(board);
				}
				return list;
			}

	}
	public ArrayList<BoardDTO> search (String target) throws Exception{
		String sql = "select * from board where writer = ? or contents like ?";
		try(
				Connection con = getConnection();
				PreparedStatement stat = con.prepareStatement(sql);	
			){
			stat.setString(1, target);
			stat.setString(2, "%"+target+"%");
				try (
						ResultSet rs= stat.executeQuery();
						){
					
					ArrayList<BoardDTO> list = new ArrayList();
					while(rs.next()) {
						int seq = rs.getInt("seq");
						String writer =rs.getString("writer");
						String contents =rs.getString("contents");
						Timestamp write_date =rs.getTimestamp("write_date");
						BoardDTO board = new BoardDTO(seq,writer,contents,write_date);
						list.add(board);
					}
					return list;
				}
			}
	}
	public int update (BoardDTO target) throws Exception{
		String sql = "update board set writer='"+target.getWriter()+"', contents='"+target.getContents()+"', write_date = sysdate where seq = "+ target.getSeq();
		System.out.println(sql);
		try(
			Connection con = getConnection();
			PreparedStatement stat = con.prepareStatement(sql);
		){
			return stat.executeUpdate();
		}
	}
	public boolean isIdExist (int target) throws Exception {
		String sql = "select * from board where seq = ?";
		try(
			Connection con = getConnection();
			PreparedStatement stat = con.prepareStatement(sql);
		){
			stat.setInt(1, target);
			try(
					ResultSet result = stat.executeQuery();
			){
				return result.next();
				
			}
		}
	}
	public int delete(int target) throws Exception{
		String sql = "delete from board where seq = ?";
		System.out.println(sql);
		try(
			Connection con = getConnection();
			PreparedStatement stat = con.prepareStatement(sql);
		){
			stat.setInt(1, target);
			return stat.executeUpdate();
		}
	}
	
	
	
	
}
