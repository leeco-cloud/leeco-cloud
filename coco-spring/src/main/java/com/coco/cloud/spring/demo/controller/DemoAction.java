package com.coco.cloud.spring.demo.controller;

import com.coco.cloud.spring.framework.mvc.CoCoModelAndView;
import com.coco.cloud.spring.demo.service.IModifyService;
import com.coco.cloud.spring.demo.service.IQueryService;
import com.coco.cloud.spring.framework.annotation.CoCoAutowired;
import com.coco.cloud.spring.framework.annotation.CoCoController;
import com.coco.cloud.spring.framework.annotation.CoCoRequestMapping;
import com.coco.cloud.spring.framework.annotation.CoCoRequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试API
 * @author liuqiang@ourdocker.cn
 *
 */
@CoCoController
@CoCoRequestMapping("/api")
public class DemoAction {

	@CoCoAutowired
	private IQueryService queryService;
	@CoCoAutowired
	private IModifyService modifyService;

	@CoCoRequestMapping("/query.json")
	public CoCoModelAndView query(HttpServletRequest request, HttpServletResponse response,
                                  @CoCoRequestParam("name") String name){
		String result = queryService.query(name);
		return out(response,result);
	}

	@CoCoRequestMapping("/add*.json")
	public CoCoModelAndView add(HttpServletRequest request,HttpServletResponse response,
							  @CoCoRequestParam("name") String name,@CoCoRequestParam("addr") String addr){
		String result = null;
		try {
			result = modifyService.add(name,addr);
			return out(response,result);
		} catch (Exception e) {
//			e.printStackTrace();
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("detail",e.getCause().getMessage());
//			System.out.println(Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			model.put("stackTrace", Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			return new CoCoModelAndView("500",model);
		}

	}

	@CoCoRequestMapping("/remove.json")
	public CoCoModelAndView remove(HttpServletRequest request,HttpServletResponse response,
								 @CoCoRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}

	@CoCoRequestMapping("/edit.json")
	public CoCoModelAndView edit(HttpServletRequest request,HttpServletResponse response,
							   @CoCoRequestParam("id") Integer id,
							   @CoCoRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}



	private CoCoModelAndView out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
