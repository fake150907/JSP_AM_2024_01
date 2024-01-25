package com.KoreaIT.java.Jsp_AM.article.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.config.Config;
import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/doDelete")
public class ArticleDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// DB연결
		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());
			response.getWriter().append("연결 성공!");
			HttpSession session = request.getSession();

			int loginedMemberId = -1;

			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
			}

			if (loginedMemberId == -1) {
				response.getWriter().append(
						String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('../article/list');</script>"));
				return;
			}

			int id = Integer.parseInt(request.getParameter("id"));

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("WHERE id = ?;", id);

			Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

			sql = new SecSql();
			sql.append("SELECT * FROM `member` WHERE id = ?;", loginedMemberId);

			Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);

			if (articleRow.get("memberId") != memberRow.get("id")) {
				response.getWriter().append(String.format(
						"<script>alert('삭제할 권한이 없습니다. 돌아가라 인간.'); location.replace('../article/list');</script>"));
				return;
			}

			sql = SecSql.from("DELETE");
			sql.append("FROM article");
			sql.append("WHERE id = ?;", id);

			DBUtil.delete(conn, sql);

			response.getWriter()
					.append(String.format("<script>alert('%d번 글이 삭제되었습니다.'); location.replace('list');</script>", id));

		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}