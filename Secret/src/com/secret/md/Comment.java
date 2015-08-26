package com.secret.md;
/**
 * 
 * @author	zhengzy 
 * @data	2015年8月26日 上午11:03:49 
 * @version	V1.0 
 * @describe 消息评论
 */
public class Comment {
	//评论内容
	private String content;
	//评论者
	private String phonr_md5;
	
	public Comment(String content, String phonr_md5) {
		this.content = content;
		this.phonr_md5 = phonr_md5;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhonr_md5() {
		return phonr_md5;
	}
	public void setPhonr_md5(String phonr_md5) {
		this.phonr_md5 = phonr_md5;
	}
	
}
