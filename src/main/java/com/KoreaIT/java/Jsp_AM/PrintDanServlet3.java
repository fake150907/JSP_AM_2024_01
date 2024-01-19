package com.KoreaIT.java.Jsp_AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/printDan")
public class PrintDanServlet3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().append("== 8단 ==<br>" + Dan());

	}

	public String Dan() {
		String dan = "";
		for (int i = 1; i < 10; i++) {
			dan += "8 " + "* " + i + " = " + (8 * i) + "<br>";
		}
		return dan;
	}
}