package com.KoreaIT.java.Jsp_AM.member.dao;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class MemberDao {
	String loginId;
	String loginPw;
	String name;
	boolean isJoinableLoginId;

	public int doJoin(Connection conn, String loginId, String loginPw, String name) {
		SecSql sql = SecSql.from("INSERT INTO `member`");
		sql.append("SET regDate = NOW(),");
		sql.append("loginId = ?,", loginId);
		sql.append("loginPw = ?,", loginPw);
		sql.append("`name` = ?;", name);
		return DBUtil.insert(conn, sql);
	}

	public boolean isJoinableLoginId(Connection conn, String loginId) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?;", loginId);

		return DBUtil.selectRowIntValue(conn, sql) == 0;
	}

	public Map<String, Object> getMemberRow(Connection conn, String loginId, String loginPw) {

		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?;", loginId);

		return DBUtil.selectRow(conn, sql);
	}

	public void doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberLoginId");
		session.removeAttribute("loginedMember");
		return;
	}

	public void setLoginedMember(HttpServletRequest request, Map<String, Object> memberRow) {
		HttpSession session = request.getSession();
		session.setAttribute("loginedMemberId", memberRow.get("id"));
		session.setAttribute("loginedMemberLoginId", memberRow.get("loginId"));
		session.setAttribute("loginedMember", memberRow);
		return;
	}
}
