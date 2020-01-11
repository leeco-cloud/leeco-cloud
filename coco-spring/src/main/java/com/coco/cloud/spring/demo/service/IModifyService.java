package com.coco.cloud.spring.demo.service;

/**
 * 增删改业务
 * @author Tom
 *
 */
public interface IModifyService {

	/**
	 * 增加
	 */
	String add(String name, String addr) throws Exception;
	
	/**
	 * 修改
	 */
	String edit(Integer id, String name);
	
	/**
	 * 删除
	 */
	String remove(Integer id);
	
}
