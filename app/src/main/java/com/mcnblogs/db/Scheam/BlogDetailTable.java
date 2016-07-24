package com.mcnblogs.db.Scheam;

/**
 * 作者：hl
 * 时间:2016/7/22 17:44
 * 说明:博客详细表
 */
public class BlogDetailTable {
    public final static String TABLE_NAME="blogdetail";//表名称
    public final static String UID="id";//user id
    public final static String BLOG_ID="blogid";
    public final static String BLOG_CONTENT="blogcontent";//文件正文

    /**
     * 表创建sql
     */
    public  final  static String CREATE_TABLE_SQL=" create table "+BlogDetailTable.TABLE_NAME
            +"("
            +BlogDetailTable.UID+" integer primary key autoincrement," //autoincrement id自增的意思
            +BlogDetailTable.BLOG_ID+" integer,"
            +BlogDetailTable.BLOG_CONTENT+" text"
            + ")";
}
