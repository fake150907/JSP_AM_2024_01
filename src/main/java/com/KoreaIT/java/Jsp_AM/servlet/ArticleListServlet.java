package com.KoreaIT.java.Jsp_AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// DB연결
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/JSP_AM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			response.getWriter().append("연결 성공!");

			SecSql sql = new SecSql();
			String searchKeyword = null;

			int page = Integer.parseInt(request.getParameter("page"));

			// 한페이지에 5개씩
			int itemsInPage = 10;

			int limitFrom = (page - 1) * itemsInPage;
			int limitTake = itemsInPage;

			Map<String, Object> args = new HashMap<>();
			args.put("searchKeyword", searchKeyword);
			args.put("limitTake", limitTake);
			args.put("limitFrom", limitFrom);

			if (args.containsKey("limitFrom")) {
				limitFrom = (int) args.get("limitFrom");
			}

			if (args.containsKey("limitTake")) {
				limitTake = (int) args.get("limitTake");
			}

			sql.append("SELECT * ");
			sql.append("FROM article");
			sql.append("ORDER BY id DESC");
			if (limitFrom != -1) {
				sql.append("LIMIT ?, ?;", limitFrom, limitTake);
			}

			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
			request.setAttribute("articleRows", articleRows);
			request.setAttribute("page1", page);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

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

}