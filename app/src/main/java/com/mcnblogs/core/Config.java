package com.mcnblogs.core;

/**
 * Url配置
 */
public class Config {

	/**
	 * 分页获取首页文章列表
	 */
	public  static  final  String siteHome_URL="http://wcf.open.cnblogs.com/blog/sitehome/paged/{pageIndex}/{pageSize}";

	/**
	 * 48小时文章阅读排行榜
	 */
	public static final String List48HoursTop_URL="http://wcf.open.cnblogs.com/blog/48HoursTopViewPosts/{num}";

	/**
	 * 10天推荐排行榜
	 */
	public static  final String List10DayTop_URL="http://wcf.open.cnblogs.com/blog/TenDaysTopDiggPosts/{num}";

	/**
	 * 新闻分页接口
	 */
	public  static final String ListNewsPage_URL="http://wcf.open.cnblogs.com/news/recent/paged/1/30";

	/**
	 * 新闻内容
	 */
	public  static  final String NewItem_URL="http://wcf.open.cnblogs.com/news/item/{0}";

	/**
	 * 文章正文Url
	 */
	public static final String Body_URL="http://wcf.open.cnblogs.com/blog/post/body/{0}";

	/**
	 * 本地html
	 */
	public static final String LOCAL_PATH = "file:///android_asset/";

	/**
	 * 全局编码方式
	 */
	public static final String ENCODE_TYPE = "utf-8";// 
}
