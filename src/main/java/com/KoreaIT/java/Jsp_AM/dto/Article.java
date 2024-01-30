package com.KoreaIT.java.Jsp_AM.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Article {
	private int id;
	private LocalDateTime regDate;
	private int memberId;
	private String title;
	private String body;
	private String extra_writer;

	public Article(Map<String, Object> articleRow) {
		this.id = (int) articleRow.get("id");
		this.regDate = (LocalDateTime) articleRow.get("regDate");
		this.memberId = (int) articleRow.get("memberId");
		this.title = (String) articleRow.get("title");
		this.body = (String) articleRow.get("body");
		this.extra_writer = (String) articleRow.get("extra_writer");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getExtra_writer() {
		return extra_writer;
	}

	public void setExtra_writer(String extra_writer) {
		this.extra_writer = extra_writer;
	}
}
