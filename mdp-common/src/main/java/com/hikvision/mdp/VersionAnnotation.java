/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/8 13:47
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>用于版本控制的注解类</p>
 * author chendongsheng5 2017/2/8 13:47
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/8 13:47
 * modify by reason:{方法名}:{原因}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface VersionAnnotation {

  /**
   * Get the MDP version
   * @return the version string "1.1"
   */
  String version();

  /**
   * Get the subversion revision.
   * @return the revision number as a string (eg. "100755")
   */
  String revision();

  /**
   * Get the branch from which this was compiled.
   * @return The branch name, e.g. "trunk"
   */
  String branch();

  /**
   * Get the username that compiled MDP.
   */
  String user();

  /**
   * Get the date when MDP was compiled.
   * @return the date in unix 'date' format
   */
  String date();

  /**
   * Get the url for the subversion repository.
   */
  String url();

  /**
   * Get a checksum of the source files from which
   * MDP was compiled.
   * @return a string that uniquely identifies the source
   **/
  String srcChecksum();

}
