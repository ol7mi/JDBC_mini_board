package dto;

import java.rmi.server.RemoteStub;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {
	/*
	 * create table board (
    seq number primary key,
    writer varchar(300) not null,
    contents varchar(300) not null,
    write_date TIMESTAMP not null
); 
*/
	private int seq;
	private String writer;
	private String contents;
	private Timestamp write_date;
	
	public String writeDate () {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yy.MM.dd");
		return sdf2.format(write_date);
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public BoardDTO(int seq, String writer, String contents, Timestamp write_date) {
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.write_date = write_date;
	}
	public BoardDTO() {
		super();
		
	}

	
	
	
	
}
