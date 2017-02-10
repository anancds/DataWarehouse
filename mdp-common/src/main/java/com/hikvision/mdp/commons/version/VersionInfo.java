/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/8 14:01
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.version;

import com.hikvision.mdp.commons.annotation.VersionAnnotation;

/**
 * <p>提供MDP的版本信息</p>
 * author chendongsheng5 2017/2/8 14:01
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/8 14:01
 * modify by reason:{方法名}:{原因}
 */
public class VersionInfo {

  private static Package myPackage;
  private static VersionAnnotation version;

  static {
    myPackage = VersionAnnotation.class.getPackage();
    version = myPackage.getAnnotation(VersionAnnotation.class);
  }


  /**
   * Get the meta-data for the MDP package.
   *
   * @return MDP package
   */
  static Package getPackage() {
    return myPackage;
  }

  /**
   * Get the MDP version.
   *
   * @return the MDP version string, eg. "1.1"
   */
  public static String getVersion() {
    return version != null ? version.version() : "Unknown";
  }

  /**
   * Get the subversion revision number for the root directory
   *
   * @return the revision number, eg. "100755"
   */
  public static String getRevision() {
    if (version != null
        && version.revision() != null
        && !version.revision().isEmpty()) {
      return version.revision();
    }
    return "Unknown";
  }

  /**
   * Get the branch on which this originated.
   *
   * @return The branch name, e.g. "trunk" or "branches/branch-1.1"
   */
  public static String getBranch() {
    return version != null ? version.branch() : "Unknown";
  }

  /**
   * The date that MDP was compiled.
   *
   * @return the compilation date in unix date format
   */
  public static String getDate() {
    return version != null ? version.date() : "Unknown";
  }

  /**
   * The user that compiled MDP.
   *
   * @return the username of the user
   */
  public static String getUser() {
    return version != null ? version.user() : "Unknown";
  }

  /**
   * Get the subversion URL for the root MDP directory.
   */
  public static String getUrl() {
    return version != null ? version.url() : "Unknown";
  }

  /**
   * Get the checksum of the source files from which MDP was
   * built.
   **/
  public static String getSrcChecksum() {
    return version != null ? version.srcChecksum() : "Unknown";
  }

  /**
   * Returns the build version info which includes version,
   * revision, user, date and source checksum
   */
  public static String getBuildVersion() {
    return VersionInfo.getVersion() +
        " from " + VersionInfo.getRevision() +
        " by " + VersionInfo.getUser() +
        " on " + VersionInfo.getDate() +
        " source checksum " + VersionInfo.getSrcChecksum();
  }

  public static void main(String[] args) {
    System.out.println("MDP " + getVersion());
    System.out.println("Revision: " + getRevision());
    System.out.println("Branch: " + getBranch());
    System.out.println("Compiled by " + getUser() + " on " + getDate());
    System.out.println("From source with checksum " + getSrcChecksum());
  }

}
