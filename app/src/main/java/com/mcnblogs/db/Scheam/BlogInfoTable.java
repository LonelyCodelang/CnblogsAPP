package com.mcnblogs.db.Scheam;

/**
 * 作者：hl
 * 时间:2016/7/22 17:04
 * 说明:博客列表内容数据
 */
public class BlogInfoTable {
    public final static String TABLE_NAME="bloglist";//表名称

    public final static String UID="id";
    public final static String BLOG_ID="blogid";//博客园的博客id
    public final static String TITLE="title";//文章标题
    public final static String CONTENT="content";//内容概要
    public final static String AUTHORURL="authorurl";//作者博客地址url
    public final static String AUTHORNAME="authorname";//作者名称
    public final static String AVATARURL="avatarurl";//作者图像url
    public final static String PUBLISHED="published";//发布时间
    public final static String VIEWS="views";//阅读数
    public final static String DIGGS="diggs";//推荐数
    public final static String COMMENTS="comments";//评论数
    public final static String BTYPE="btype";//数据类型

    /**
     * 表创建的sql语句
     */
    public final static String CREATE_TABLE_SQL="create table "+BlogInfoTable.TABLE_NAME
            +"("
            +BlogInfoTable.UID+" integer primary key AUTOINCREMENT,"
            +BlogInfoTable.BLOG_ID+" integer,"
            +BlogInfoTable.TITLE+" text,"
            +BlogInfoTable.CONTENT+" text,"
            +BlogInfoTable.AUTHORURL+" text,"
            +BlogInfoTable.AUTHORNAME+" text,"
            +BlogInfoTable.AVATARURL+" text,"
            +BlogInfoTable.PUBLISHED+" text,"
            +BlogInfoTable.VIEWS+" text,"
            +BlogInfoTable.DIGGS+" text,"
            +BlogInfoTable.COMMENTS+" text,"
            +BlogInfoTable.BTYPE+" text "
            +");";
}
