package com.mcnblogs.dto;

import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;

public class BlogListDTO implements Parcelable {

	/**
	 * 博客id
	 */
	private String id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 作者博客首页
	 */
	private String authorUrl;

	/**
	 * 作者名称
	 */
	private String authorName;

	/**
	 * 作者图像Url
	 */
	private String avatarUrl;

	/**
	 * 发布日期
	 */
	private Date published;

	/**
	 * 文章url
	 */
	private String url;

	/**
	 * 阅读数
	 */
	private String views;

	/**
	 * 推荐数
	 */
	private String diggs;

	/**
	 * 评论数
	 */
	private String comments;

	@Override
	public int describeContents() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO 自动生成的方法存根
		dest.writeString(this.id);
		dest.writeString(this.title);
		dest.writeString(this.content);
		dest.writeString(this.authorUrl);
		dest.writeString(this.authorName);
		dest.writeString(this.avatarUrl);
		// dest.writeString(val);(this.published);
		
		dest.writeString(this.url);
		dest.writeString(this.views);
		dest.writeString(this.diggs);
		dest.writeString(this.comments);
		dest.writeLong(this.published.getTime());
		

	}

	// 添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
	public static final Parcelable.Creator<BlogListDTO> CREATOR = new Parcelable.Creator<BlogListDTO>() {

		// 从Parcel中读取数据,返回worker对象
		@Override
		public BlogListDTO createFromParcel(Parcel in) {
			BlogListDTO dto = new BlogListDTO();
			dto.id=in.readString();
			dto.title = in.readString();
			dto.content = in.readString();
			dto.authorUrl = in.readString();
			dto.authorName = in.readString();
			dto.avatarUrl = in.readString();
			dto.url = in.readString();
			dto.views = in.readString();
			dto.diggs = in.readString();
			dto.comments = in.readString();
			// dest.writeString(val);(this.published);
			dto.published = new Date(in.readLong());
			return dto;
		}

		@Override
		public BlogListDTO[] newArray(int size) {
			return new BlogListDTO[size];
		}
	};

	/**
	 * @return content
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param content
	 *            要设置的 content
	 */
	public void setTitle(String content) {
		this.title = content;
	}

	/**
	 * @return authorUrl
	 */
	public String getAuthorUrl() {
		return authorUrl;
	}

	/**
	 * @param authorUrl
	 *            要设置的 authorUrl
	 */
	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	/**
	 * @return authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName
	 *            要设置的 authorName
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * @param avatarUrl
	 *            要设置的 avatarUrl
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	/**
	 * @return published
	 */
	public Date getPublished() {
		return published;
	}

	/**
	 * @param published
	 *            要设置的 published
	 */
	public void setPublished(Date published) {
		this.published = published;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            要设置的 url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return views
	 */
	public String getViews() {
		return views;
	}

	/**
	 * @param views
	 *            要设置的 views
	 */
	public void setViews(String views) {
		this.views = views;
	}

	/**
	 * @return diggs
	 */
	public String getDiggs() {
		return diggs;
	}

	/**
	 * @param diggs
	 *            要设置的 diggs
	 */
	public void setDiggs(String diggs) {
		this.diggs = diggs;
	}

	/**
	 * @return comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            要设置的 comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
