package edu.scau.mapper.view;

import java.math.BigDecimal;

public class SearchResult {

	private String isbn;
	private String title;
	private BigDecimal price;
	private Integer discount;
	private String author;
	private String publish;
	private Integer mainPic;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public Integer getMainPic() {
		return mainPic;
	}
	public void setMainPic(Integer mainPic) {
		this.mainPic = mainPic;
	}
	
}
