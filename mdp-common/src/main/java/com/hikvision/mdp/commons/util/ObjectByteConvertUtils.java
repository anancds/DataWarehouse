/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/20 11:20
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>object 和 byte 转换工具类</p>
 * author chendongsheng5 2017/2/20 11:20
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/20 11:20
 * modify by reason:{方法名}:{原因}
 */
public class ObjectByteConvertUtils {

  private static Logger LOG = LogManager.getLogger(ObjectByteConvertUtils.class);

  /**
   * byte数组转对象
   */
  public static Object ByteToObject(byte[] bytes) {
    Object obj = null;
    ByteArrayInputStream bi = null;
    ObjectInputStream oi = null;
    try {
      bi = new ByteArrayInputStream(bytes);
      oi = new ObjectInputStream(bi);

      obj = oi.readObject();

    } catch (Exception e) {
      LOG.error("byte conver to object error." + e.getMessage());
    } finally {
      if (null != bi) {
        try {
          bi.close();
        } catch (IOException e) {
          LOG.error("close bi error." + e.getMessage());
        }
      }

      if (null != oi) {
        try {
          oi.close();
        } catch (IOException e) {
          LOG.error("close oi error." + e.getMessage());
        }
      }
    }
    return obj;
  }

  /**
   * 对象转byte数组
   */
  public static byte[] ObjectToByte(Object obj) {
    byte[] bytes = null;
    ByteArrayOutputStream bo = null;
    ObjectOutputStream oo = null;
    try {
      bo = new ByteArrayOutputStream();
      oo = new ObjectOutputStream(bo);
      oo.writeObject(obj);

      bytes = bo.toByteArray();

      bo.close();
      oo.close();
    } catch (Exception e) {
      LOG.error("object conver to byte error." + e.getMessage());
    } finally {
      if (null != bo) {
        try {
          bo.close();
        } catch (IOException e) {
          LOG.error("close bo error." + e.getMessage());
        }
      }

      if (null != oo) {
        try {
          oo.close();
        } catch (IOException e) {
          LOG.error("close oo error." + e.getMessage());
        }
      }
    }
    return bytes;
  }
}
