package com.mcnblogs.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.mcnblogs.db.Scheam.BlogInfoTable;
import com.mcnblogs.dto.BlogListDTO;
import com.mcnblogs.utility.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：hl
 * 时间:2016/7/22 19:19
 * 说明:
 */
public class BloginfoDao {

   private final static String insertSql=" insert into "+BlogInfoTable.TABLE_NAME
            +"("
           // +BlogInfoTable.UID+","
            +BlogInfoTable.BLOG_ID+","
            +BlogInfoTable.TITLE+","
            +BlogInfoTable.CONTENT+","
            +BlogInfoTable.AUTHORURL+","
            +BlogInfoTable.AUTHORNAME+","
            +BlogInfoTable.AVATARURL+","
            +BlogInfoTable.PUBLISHED+","
            +BlogInfoTable.VIEWS+","
            +BlogInfoTable.DIGGS+","
            +BlogInfoTable.COMMENTS+","
            +BlogInfoTable.BTYPE+")"
            +" values(?,?,?,?,?,?,?,?,?,?,?) ";

    private static SQLiteDatabase getWritable() {
        DBHelper databaseHelper = DBHelper.getInstance();
        return databaseHelper.getWritableDatabase();
    }

    private static SQLiteDatabase getReadble() {
        DBHelper databaseHelper = DBHelper.getInstance();
        return databaseHelper.getReadableDatabase();
    }

    /**
     * 单条数据插入
     * @param dto
     */
    public static void insert(BlogListDTO dto,String type){
        getWritable().execSQL(insertSql,new Object[]{
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getAuthorUrl(),
                dto.getAuthorName(),
                dto.getAvatarUrl(),
                DateUtil.ToString(dto.getPublished()),
                dto.getViews(),
                dto.getDiggs(),
                dto.getComments(),
                type
        });
    }

    /**
     * 单条数据插入
     * @param dto
     */
    public  static void insert2(BlogListDTO dto,String type){
        ContentValues values=new ContentValues();
        values.put(BlogInfoTable.BLOG_ID,dto.getId());
        values.put(BlogInfoTable.TITLE,dto.getTitle());
        values.put(BlogInfoTable.CONTENT,dto.getContent());
        values.put(BlogInfoTable.AUTHORURL,dto.getAuthorUrl());
        values.put(BlogInfoTable.AUTHORNAME,dto.getAuthorName());
        values.put(BlogInfoTable.AVATARURL,dto.getAvatarUrl());
        values.put(BlogInfoTable.PUBLISHED,dto.getPublished().toString());
        values.put(BlogInfoTable.VIEWS,dto.getViews());
        values.put(BlogInfoTable.DIGGS,dto.getDiggs());
        values.put(BlogInfoTable.COMMENTS,dto.getComments());
        values.put(BlogInfoTable.BTYPE,type);
        getWritable().insert(BlogInfoTable.TABLE_NAME,null,values);
    }

    /**
     * 批量插入数据
     * @param listDTO
     */
    public static  void insertBatch(List<BlogListDTO> listDTO,String type){
        SQLiteDatabase database=getWritable();
        SQLiteStatement sqls=getWritable().compileStatement(insertSql);
        if(database.isOpen()){
            try{
                database.beginTransaction();
                for (BlogListDTO item:listDTO) {
                    //注意，sql字段的顺序不能改变
                    sqls.bindLong(1,Long.parseLong(item.getId()));
                    sqls.bindString(2,item.getTitle());
                    sqls.bindString(3,item.getContent());
                    sqls.bindString(4,item.getAuthorUrl());
                    sqls.bindString(5,item.getAuthorName());
                    sqls.bindString(6,item.getAvatarUrl());
                    sqls.bindString(7, DateUtil.ToString(item.getPublished()));
                    sqls.bindString(8,item.getViews());
                    sqls.bindString(9,item.getDiggs());
                    sqls.bindString(10,item.getComments());
                    sqls.bindString(11,type);
                    long result = sqls.executeInsert();
                }
                database.setTransactionSuccessful();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            finally {
                database.endTransaction();
                database.close();
            }
        }
        //SQLiteStatement sqlListStatment = database.compileStatement(sql);
    }

    /**
     * 判断文章是否存在
     * @param title 文章标题
     * @return 返回行数
     */
    public static int GetCountByTitle(String title,String pageType){
        int result=0;
        String sql=" select count(1) as count from  "+BlogInfoTable.TABLE_NAME+" where  "+BlogInfoTable.TITLE+"='"+title+"'";
        if(!pageType.isEmpty()){
            sql+=" and btype='"+pageType+"' ";
        }
        Cursor c=getReadble().rawQuery(sql,null);
        c.moveToFirst();//光标移动到第一行
        result=c.getInt(c.getColumnIndex("count")); //c.getString(c.getColumnIndex("count"));
        return result;
    }

    /**
     * 获取数据列表
     * @param type 数据类型
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static ArrayList<BlogListDTO> getList(String type,int pageIndex,int pageSize){
        ArrayList<BlogListDTO> result=new ArrayList<>();
        String sql=" select * from "+BlogInfoTable.TABLE_NAME+" where "+BlogInfoTable.BTYPE+"='"+type+"' order by id  desc limit "+ pageSize +" offset "+pageSize*(pageIndex-1);

        //offset代表从第几条记录“之后“开始查询，limit表明查询多少条结果
       // String sql = String.format(" select * from {0} where {1}={2} order by id desc ase limit {0} offset {0}*{1} ",
         //       BlogInfoTable.TABLE_NAME,BlogInfoTable.BTYPE,type,pageSize,pageIndex-1);

        Cursor c=getReadble().rawQuery(sql,null);
        while (c.moveToNext()){
            BlogListDTO dto=new BlogListDTO();
            dto.setId(c.getString(c.getColumnIndex(BlogInfoTable.BLOG_ID)));
            dto.setTitle(c.getString(c.getColumnIndex(BlogInfoTable.TITLE)));
            dto.setContent(c.getString(c.getColumnIndex(BlogInfoTable.CONTENT)));
            dto.setAuthorUrl(c.getString(c.getColumnIndex(BlogInfoTable.AUTHORURL)));
            dto.setAuthorName(c.getString(c.getColumnIndex(BlogInfoTable.AUTHORNAME)));
            dto.setAvatarUrl(c.getString(c.getColumnIndex(BlogInfoTable.AVATARURL)));
            dto.setPublished(DateUtil.ToDate(c.getString(c.getColumnIndex(BlogInfoTable.PUBLISHED))));
            dto.setViews(c.getString(c.getColumnIndex(BlogInfoTable.VIEWS)));
            dto.setDiggs(c.getString(c.getColumnIndex(BlogInfoTable.DIGGS)));
            dto.setComments(c.getString(c.getColumnIndex(BlogInfoTable.COMMENTS)));
            result.add(dto);
        }
        return  result;
    }
}

