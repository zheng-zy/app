<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	out.clear();
	String action = request.getParameter("action");
	if (action != null) {
		if (action.equals("send_pass")) {
			out.print("{\"status\":1}");
		} else if (action.equals("login")) {
			out.print("{\"status\":1,\"token\":\"abcdefgh\"}");
		} else if (action.equals("upload_contacts")) {
			out.print("{\"status\":1}");
		} else if (action.equals("timeline")) {
			out.print("{\"status\":1,\"page\":1,\"perpage\":20,\"items\":["
					+ "{\"msg\":\"I want have sex with some one\",\"phone_md5\":\"md5xxx\",\"msgId\":\"123\"},"
					+ "{\"msg\":\"I want have sex with some two\",\"phone_md5\":\"md5xxx\",\"msgId\":\"456\"},"
					+ "{\"msg\":\"I want have sex with some three\",\"phone_md5\":\"md5xxx\",\"msgId\":\"789\"}"
					+ "]}");
		}
	} else {
		out.print("请指定action");
	}
%>