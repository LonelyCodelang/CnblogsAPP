package com.mcnblogs.dto;

import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;

public class BlogListDTO implements Parcelable {

	/**
	 * ����id
	 */
	private String id;

	/**
	 * ����
	 */
	private String title;

	/**
	 * ����
	 */
	private String content;

	/**
	 * ���߲�����ҳ
	 */
	private String authorUrl;

	/**
	 * ��������
	 */
	private String authorName;

	/**
	 * ����ͼ��Url
	 */
	private String avatarUrl;

	/**
	 * ��������
	 */
	private Date published;

	/**
	 * ����url
	 */
	private String url;

	/**
	 * �Ķ���
	 */
	private String views;

	/**
	 * �Ƽ���
	 */
	private String diggs;

	/**
	 * ������
	 */
	private String comments;

	@Override
	public int describeContents() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO �Զ����ɵķ������
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

	// ���һ����̬��Ա,��ΪCREATOR,�ö���ʵ����Parcelable.Creator�ӿ�
	public static final Parcelable.Creator<BlogListDTO> CREATOR = new Parcelable.Creator<BlogListDTO>() {

		// ��Parcel�ж�ȡ����,����worker����
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
	 *            Ҫ���õ� content
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
	 *            Ҫ���õ� authorUrl
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
	 *            Ҫ���õ� authorName
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
	 *            Ҫ���õ� avatarUrl
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
	 *            Ҫ���õ� published
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
	 *            Ҫ���õ� url
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
	 *            Ҫ���õ� views
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
	 *            Ҫ���õ� diggs
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
	 *            Ҫ���õ� comments
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
	 *            Ҫ���õ� content
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
	 * @param id Ҫ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
