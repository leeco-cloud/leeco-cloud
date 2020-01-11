package com.coco.cloud.spring.demo.service.impl;

import com.coco.cloud.spring.demo.service.IModifyService;
import com.coco.cloud.spring.framework.annotation.CoCoService;

/**
 * 增删改业务
 * @author Tom
 *
 */
@CoCoService
public class ModifyService implements IModifyService {

	/**
	 * 增加
	 */
	public String add(String name,String addr) throws Exception {
		throw new Exception("这是coco故意抛的异常！！");
	}

	/**
	 * 修改
	 */
	public String edit(Integer id,String name) {
		return "modifyService edit,id=" + id + ",name=" + name;
	}

	/**
	 * 删除
	 */
	public String remove(Integer id) {
		return "modifyService id=" + id;
	}
	
}
