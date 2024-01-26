package com.KoreaIT.java.Jsp_AM.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.config.Config;
import com.KoreaIT.java.Jsp_AM.exception.SQLErrorException;
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
		int id = 0;
		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());

			SecSql sql = SecSql.from("INSERT INTO `member`");
			sql.append("SET regDate = NOW(),");
			sql.append("loginId = ?,", loginId);
			sql.append("loginPw = ?,", loginPw);
			sql.append("`name` = ?;", name);

			id = DBUtil.insert(conn, sql);
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public boolean isJoinableLoginId(Connection conn, String loginId) {
		boolean isJoinableLoginId = false;
		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());

			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);
			isJoinableLoginId = DBUtil.selectRowIntValue(conn, sql) == 0;
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isJoinableLoginId;
	}

	public Map<String, Object> getMemberRow(Connection conn, String loginId, String loginPw) {
		Map<String, Object> memberRow = new HashMap<>();
		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);

			memberRow = DBUtil.selectRow(conn, sql);
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberRow;
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
