package com.mcnblogs.core;

import android.util.Log;

import com.mcnblogs.dto.BlogListDTO;
import com.mcnblogs.utility.AppEnum;
import com.mcnblogs.utility.DateUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-07-05.
 */
public class BlogJsonHelper {

    /**
     * Json转列表
     * @param json
     * @return
     */
    public  static List<BlogListDTO> JsonToList(String json)
    {
        final List<BlogListDTO> result = new ArrayList<BlogListDTO>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsfeed = jsonObject.getJSONObject("feed");
            JSONArray jsonArray = jsfeed.getJSONArray("entry");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject JsonBlog = jsonArray.getJSONObject(i);
                BlogListDTO dto= BlogJsonHelper.JsonToDto(JsonBlog);
                Log.i(AppEnum.LogTag.PageHome.toString(), dto.getTitle());
                result.add(dto);
            }
        }
        catch (Exception ex)
        {
            Log.e("JsonToList", ex.getMessage());
        }
        return  result;
    }

    /**
     * Json转DTO
     * @param json
     * @return
     */
    public static BlogListDTO JsonToDto(JSONObject json)
    {
        BlogListDTO result=new BlogListDTO();
        try {
            String blogId= json.getString("id");//博客id
            String title = json.getJSONObject("title").getString(
                    "content");// 标题
            String content = json.getJSONObject("summary")
                    .getString("content");// 文章大纲
            String url = json.getJSONObject("link").getString(
                    "href");// 文章url
            String published = json.getString("published");// 发布时间
            String updated = json.getString("updated");// 更新时间
            String views = json.getString("views");// 阅读数
            String diggs = json.getString("diggs");// 推荐数
            String comments = json.getString("comments");// 评论数

            String authorName = json.getJSONObject("author")
                    .getString("name");// 作者名称

            String authorUrl = json.getJSONObject("author")
                    .getString("uri");// 作者博客主页

            JSONObject authorObj=json.getJSONObject("author");
            String avatarUrl="";
            if(authorObj!=null &&  !authorObj.isNull("avatar")){
                avatarUrl=authorObj.getString("avatar");// 作者图像路径
            }

            // JSONObject ff=JsonBlog.get
            // String xx = feed;
            BlogListDTO dto = new BlogListDTO();
            dto.setId(blogId);
            dto.setTitle(title);
            dto.setContent(content);
            dto.setUrl(url);
            dto.setPublished(DateUtil.ToDate2(published));
            dto.setViews(views);
            dto.setDiggs(diggs);
            dto.setComments(comments);
            dto.setAuthorName(authorName);
            dto.setAuthorUrl(authorUrl);
            dto.setAvatarUrl(avatarUrl);
            result=dto;
        }catch (Exception e)
        {
            Log.e("BlogJsonToDTO", e.getMessage());
        }
        return result;
    }


}
