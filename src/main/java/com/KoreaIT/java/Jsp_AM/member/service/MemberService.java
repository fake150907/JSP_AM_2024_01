package com.KoreaIT.java.Jsp_AM.member.service;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.member.dao.MemberDao;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.http.HttpServletRequest;

public class MemberService {
	MemberDao memberDao = new MemberDao();

	public int doJoin(Connection conn, String loginId, String loginPw, String name) {
		return memberDao.doJoin(conn, loginId, loginPw, name);
	}

	public boolean isJoinableLoginId(Connection conn, String loginId) {
		return memberDao.isJoinableLoginId(conn, loginId);
	}

	public Map<String, Object> getMemberRow(Connection conn, String loginId, String loginPw) {
		return memberDao.getMemberRow(conn, loginId, loginPw);
	}

	public void doLogout(HttpServletRequest request) {
		memberDao.doLogout(request);
		return;
	}

	public void setLoginedMember(HttpServletRequest request,Map<String, Object> memberRow) {
		memberDao.setLoginedMember(request,memberRow);
		return;
	}
}
