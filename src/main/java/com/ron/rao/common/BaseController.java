package com.ron.rao.common;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基础controller
 * <p>Title:com.ron.paradise.core.base.BaseController</p>
 * <p>Description:</p>
 * <p>Company:沫兰遗夏的后花园</p>
 * @author 沫兰遗夏
 * @date 2020年3月11日 下午10:29:17
 */
public class BaseController {
	/**
	 * 以json方式返回ajax请求
	 * @param response
	 * @param obj
	 */
	public void renderJson(HttpServletResponse response, Object obj) {
		render(response, JSONObject.toJSONString(obj),
				"application/json;charset=UTF-8");
	}
	
	public void render(HttpServletResponse response, String text,
                       String contentType) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
