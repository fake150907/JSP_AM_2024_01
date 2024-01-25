package com.KoreaIT.java.Jsp_AM.member.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.config.Config;
import com.KoreaIT.java.Jsp_AM.exception.SQLErrorException;
import com.KoreaIT.java.Jsp_AM.member.service.MemberService;
import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {

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
		MemberService memberService = new MemberService();
		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");

			Map<String, Object> memberRow = memberService.getMemberRow(conn, loginId, loginPw);

			if (memberRow.isEmpty()) {
				response.getWriter().append(String.format(
						"<script>alert('%s는 없는 아이디입니다'); location.replace('../member/login');</script>", loginId));
				return;
			}

			if (memberRow.get("loginPw").equals(loginPw) == false) {
				response.getWriter().append(
						String.format("<script>alert('비밀번호가 틀렸어'); location.replace('../member/login');</script>"));
				return;
			}

			memberService.setLoginedMember(request, memberRow);

			response.getWriter()
					.append(String.format(
							"<script>alert('%s님, 로그인 되었습니다.'); location.replace('../article/list');</script>",
							memberRow.get("name")));

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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}