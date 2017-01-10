/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/9 20:17
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.parser;

/**
 * <p></p>
 *
 * @author zhangsiwei6 2017/1/9 20:17
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: zhangsiwei6 2017/1/9 20:17
 * @modify by reason:{方法名}:{原因}
 */
public class AccountBean {
	private int id;
	private String name;
	private String email;
	private String address;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//getter、setter

	@Override
	public String toString() {
		return this.name + "#" + this.id + "#" + this.address + "#" + this.email;
	}
}
