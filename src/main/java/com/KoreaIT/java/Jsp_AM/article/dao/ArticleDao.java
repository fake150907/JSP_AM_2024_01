package com.KoreaIT.java.Jsp_AM.article.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.config.Config;
import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

public class ArticleDao {

	public List<Map<String, Object>> getArticleRows(Connection conn, int page) {
		List<Map<String, Object>> articleRows = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());

			int itemsInAPage = 10;
			int limitFrom = (page - 1) * itemsInAPage;

			SecSql sql = SecSql.from("SELECT a.*, m.`name` AS writer");
			sql.append("FROM `member` AS m");
			sql.append("INNER JOIN article AS a");
			sql.append("ON a.memberId = m.id");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT ?, ?;", limitFrom, itemsInAPage);

			articleRows = DBUtil.selectRows(conn, sql);
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
		return articleRows;
	}

	public Map<String, Integer> getTotalMap(Connection conn) {
		Map<String, Integer> totalMap = new HashMap<>();
		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());

			int itemsInAPage = 10;

			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM article");

			int totalCnt = DBUtil.selectRowIntValue(conn, sql);
			int totalPage = (int) Math.ceil(totalCnt / (double) itemsInAPage);

			totalMap.put("totalCnt", totalCnt);
			totalMap.put("totalPage", totalPage);
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

		return totalMap;
	}

}
