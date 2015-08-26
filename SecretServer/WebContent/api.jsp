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
		} else if (action.equals("get_comment")) {
			out.print("{\"status\":1,\"page\":1,\"perpage\":20,\"comments\":["
					+ "{\"content\":\"I want have sex with some 1\",\"phone_md5\":\"md5xxx\"},"
					+ "{\"content\":\"I want have sex with some 2\",\"phone_md5\":\"md5xxx\"},"
					+ "{\"content\":\"I want have sex with some 3\",\"phone_md5\":\"md5xxx\"},"
					+ "{\"content\":\"I want have sex with some 4\",\"phone_md5\":\"md5xxx\"},"
					+ "{\"content\":\"I want have sex with some 5\",\"phone_md5\":\"md5xxx\"},"
					+ "{\"content\":\"I want have sex with some 6\",\"phone_md5\":\"md5xxx\"}"
					+ "],\"msgId\":\"123\"}");
		} else if (action.equals("pub_comment")) {
			out.print("{\"status\":1}");
		} else if (action.equals("publish")) {
			out.print("{\"status\":1}");
		}
	} else {
		out.print("请指定action");
	}
%>