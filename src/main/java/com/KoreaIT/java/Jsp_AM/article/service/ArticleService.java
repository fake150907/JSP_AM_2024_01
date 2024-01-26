package com.KoreaIT.java.Jsp_AM.article.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.article.dao.ArticleDao;

public class ArticleService {
	ArticleDao articleDao = new ArticleDao();

	public List<Map<String, Object>> getArticleRows(Connection conn, int page) {
		return articleDao.getArticleRows(conn, page);
	}

	public Map<String, Integer> getTotalMap(Connection conn) {
		return articleDao.getTotalMap(conn);
	}

}
