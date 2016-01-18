package com.yf.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cykj.grcloud.mybatis.page.PageObject;
import com.cykj.grcloud.util.Ognl;

public class BaseController {
	protected static Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	/**
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	protected PageObject getPageObject(HttpServletRequest request) {
		return getPageObject(request, null);
	}

	/**
	 * order asc page 1 rows 10 sort roleId
	 * 
	 * @param request
	 * @param orderByClause
	 * @return
	 */
	protected PageObject getPageObject(HttpServletRequest request,
			String orderByClause) {
		PageObject pageObject = new PageObject();
		String currPageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		String property = request.getParameter("sort");
		if (Ognl.isEmpty(orderByClause)) {
			if (!Ognl.isEmpty(property)) {
				orderByClause = getColumn(property) + " "
						+ request.getParameter("order");
			}
		}
		try {
			pageObject.setCurrPage(Integer.valueOf(currPageStr));
			pageObject.setPageSize(Integer.valueOf(pageSizeStr));
		} catch (NumberFormatException e) {
			pageObject.setCurrPage(1);
			pageObject.setPageSize(Integer.MAX_VALUE);
		}
		pageObject.addCondition("orderByClause", orderByClause);
		return pageObject;
	}

	private String getColumn(String property) {
		StringBuffer buffer = new StringBuffer(property);
		for (int i = 0; i < buffer.length(); i++) {
			char c = buffer.charAt(i);
			if (Character.isUpperCase(c)) {
				buffer.insert(i++, '_');
			}
		}
		return buffer.toString();
	}

	private static String makeJsonResponse(boolean result, String errMsg) {
		if (result) {
			return "{\"success\":" + result + ",\"msg\":\"" + errMsg + "\"}";
		}
		return "{\"success\":" + result + ",\"msg\":\"" + errMsg + "\"}";
	}

	public static void ajaxJsonResponse(PrintWriter out, boolean result,
			String errMsg) {
		writeToPage(makeJsonResponse(result, errMsg), out);
	}

	public static void ajaxJsonResponseSuccess(PrintWriter out) {
		ajaxJsonResponse(out, true, "操作成功");
	}

	public static void writeToPage(String content, PrintWriter out) {
		out.print(content);
		out.flush();
		out.close();
	}

	public static void writeToPage(String content, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(content);
		} catch (IOException e) {
			logger.warn("write to page error: ", e);
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
	}

}