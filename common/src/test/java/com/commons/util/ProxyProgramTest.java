/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/30 15:14
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */

package com.hikvision.mdp.commons.util;

public class ProxyProgramTest {

	public static void main(String[] args) throws Throwable {
		ProxyProgram proxyProgram = new ProxyProgram();
		proxyProgram.addClass("ProxyTest", ProxyTest.class, "ProxyTest class");
		proxyProgram.run(args);
	}

}