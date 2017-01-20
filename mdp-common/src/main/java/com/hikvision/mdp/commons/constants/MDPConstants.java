/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/6 15:55
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.constants;

/**
 * <p>平台相关常量</p>
 *
 * @author chendongsheng5 2017/1/6 15:55
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/6 15:55
 * @modify by reason:{方法名}:{原因}
 */
public class MDPConstants {

	private MDPConstants() {
	}

	/**
	 * 公共常量
	 */
	public final class COMMON {

		/**
		 * 创建一个新的实例COMMON.
		 */
		private COMMON() {
		}

		/**
		 * 有效
		 */
		public final static String EFFECTIVE = "1";
		/**
		 * 无效
		 */
		public final static String INEFFECTIVENESS = "0";

		public final static String TIME_ZONE = "Asia/Shanghai";

		/**
		 * yyyy-MM-dd格式
		 */
		public final static String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

		/**
		 * yyyy-MM-dd HH:mm:ss格式
		 */
		public final static String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
		public final static String DATE_FORMAT_YYYYMMDDHH24MMSS = "yyyy-MM-dd HH24:mi:ss";
		/**
		 * yyyyMMddkkmmssSSSS格式
		 */
		public final static String DATE_FORMAT_LONG_FORMAT = "yyyyMMddkkmmssSSSS";
		/**
		 * EEE MMM dd HH:mm:ss z yyyy格式
		 */
		public final static String DATE_FORMAT_EEEMMMDDHHMMSSZYYYY = "EEE MMM dd HH:mm:ss z yyyy";

		/**
		 * 国际化异常资源文件
		 */
		public final static String NATIONAL_EXCEPTION = "exceptionMsg";
		/**
		 * 国际化页面资源文件
		 */
		public final static String NATIONAL_PAGE = "pageMsg";
		/**
		 * 国际化菜单资源文件
		 */
		public final static String NATIONAL_MENU = "menuMsg";

		/**
		 * 升序 default
		 */
		public final static String ORDER_ASC = "asc";

		/**
		 * 降序
		 */
		public final static String ORDER_DESC = "desc";

		/**
		 * 编码序号
		 */
		public final static String BMXH = "00000000000000000000";

		public final static String CHARSET_UTF8 = "UTF8";
		public final static String CHARSET_UTF_8 = "UTF-8";
		public final static String CHARSET_GBK = "GBK";
		public final static String CHARSET_ISO88591 = "iso-8859-1";

		public final static String BLANK = "";
		public final static String NEW_LINE = "\n";
		public final static String COLON = ":";
		public final static String COMMA = ",";
		public final static String DOT = ".";
		public final static String LINK = "-";
		public final static String UNDERLINE = "_";
		public final static String SLASH = "/";

		/**
		 * 类全名
		 */
		public final static String COLLECT_KEY = "collectKey";

		/**
		 * 错误队列主题后缀,主题为COLLECT_KEY对应值+ERROR_TOPIC_SUFFIX
		 *
		 */
		public final static String ERROR_TOPIC_SUFFIX = "_ERROR_TOPIC";
	}

	public final class Collector {
		private Collector() {
		}


		public static final String PIPELINE_INFO_HIK_MDP_DATA = "hik_mdp_data.yml";

		public static final String UNDERLINE = "_";

		/**
		 * 数据采集配置目录环境变量名称
		 *
		 */
		public static final String COLLECTOR_CONF_DIR_KEY = "BSP_COLLECTOR_CONF_DIR";

		// 数据采集日志输出目录
		public static final String BSP_COLLECTOR_LOG_DIR_KEY = "BSP_COLLECTOR_LOG_DIR";

		// HBase row key
		public static final String ROW_KEY_NAME = "rowKey";

		// kafka ZK连接路径参数名称
		public static final String KAFKA_ZK_URL_NAME = "kafka.zk.url";

		// kafka 集群连接路径参数名称
		public static final String BOOTSTRAP_SERVERS_NAME = "bootstrap.servers";

		// Hare索引服务路径
		public static final String HARE_SERVICE_URL_NAME = "hare.service.url";

		// 图片分析服务地址
		public static final String ANALYSIS_SERVICE = "analysis.server.ws";

		// Hbase预分配region数
		public static final String HBASE_REGION_NUM_NAME = "region.spliter.regionNum";

		// 分析任务批处理规模
		public static final String ANALYSIS_TASK_BATCH_SIZE = "analysis.task.batch.size";

		// 物联网数据接收开关
		public static final String METADATA_COLLECT_SWITCH = "metadata.collect.switch";

		// 智能元数据索引开关
		public static final String SMART_METADATA_INDEX_SWITCH = "smart.metadata.index.switch";

		//智能元数据发kafka开关
		public static final String SMART_METADATA_KAFKA_SWITCH = "smart.metadata.kafka.switch";

		// 智能元数据图片分析任务开关
		public static final String SMART_METADATA_IMAGE_ANALYSIS_SWITCH = "smart.metadata.image.analysis.switch";

		// 心跳检测时间间隔
		public static final String HEARTBEAT_DETECTION_INTERVAL = "heartbeat.detection.interval";

		// HBase zookeeper地址配置
		public static final String HBASE_ZOOKEEPER_QUORUM_NAME = "hbase.zookeeper.quorum";

		// HBase zookeeper端口配置
		public static final String HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT_NAME = "hbase.zookeeper.property.clientPort";

		// HBase服务
		public static final String SERVICE_HBASE = "HBASE";

		// Hare服务
		public static final String SERVICE_HARE = "HARE";

		// PostgreSQL服务
		public static final String SERVICE_PG = "PG";

		// ES服务
		public static final String SERVICE_ES = "ES";

		// kafka服务
		public static final String SERVICE_KAFKA = "KAFKA";

		// 分析任务类型：人脸
		public static final int ANALYSIS_TASK_TARGET_TYPE_HUMAN = 5;

		// 分析任务类型：车辆
		public static final int ANALYSIS_TASK_TARGET_TYPE_VEHICLE = 6;

		// 图片类型：人脸
		public static final int PIC_TARGET_TYPE_HUMAN = 1;

		// 图片类型：车辆
		public static final int PIC_TARGET_TYPE_VEHICLE = 2;

		// 分析任务：未提交
		public static final int ANALYSIS_TASK_PROGRESS_UNCOMMITTED = 0;

		// 分析任务：执行中
		public static final int ANALYSIS_TASK_PROGRESS_COMMITTED = 1;

		// Kafka消息应答类型-1
		public static final String KAFKA_ACKS_CONFIG_DEFAULT = "-1";

		// 消息接收出错次数
		public static final String MSG_ERROR_TAG = "MSG_ERROR_TAG";

		// 出错消息文件默认保存路径
		public static final String ERROR_FILE_DEFAULT_DIR = "/tmp/";

		// 消息出错次数阈值
		public static final int MSG_ERROR_THRESHOLD_DEFAULT = 3;

		// 消息出错次数阈值
		public static final String MSG_ERROR_THRESHOLD = "msg.error.threshold";

		// 消息出错次数阈值
		public static final String HDFS_RUI = "hdfs.uri";

		public static final String INDEX_ENGINE = "index.engine";

		public static final String ES_SERVER = "es.server";

		public static final String WIFI_CLUSTER_NAME = "cluster.name.wifi";// es集群名称

		// 元数据转换器key
		public static final String SMART_METADATA_CONVERTER_KEY = "HIK-SmartMetadata";

		public static final String COLUMN_FAMILY_INFO = "info";

		public static final String FIELD_POSTFIX_CON = "_con";

		public static final String FIELD_ZXD_POSTFIX = "_zxd";

		public static final String FIELD_POSTFIX_T = "_t";

		public static final String FIELD_POSTFIX_CV = "_cv";
	}
}
