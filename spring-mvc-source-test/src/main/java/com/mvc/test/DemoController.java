package com.mvc.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * 测试contr
 *
 * @author wenpan 2022/08/06 20:24
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

	@ResponseBody
	@RequestMapping("/handle01")
	public String handle01(String name, Map<String,Object> model){
		System.out.println("=================handler业务逻辑处理中=================");
		Date date = new Date();
		model.put("date",date);
		// 返回视图名称
		return "success";
	}
}
