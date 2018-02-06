package cn.gree.zz.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Plan {

	private Long id;
	private String orderNo;  //订单
	private int orderNum;  //订单数量
	private String item;   //物料代码
	private int checkNum;  //抽检数量
	private int failNum;   //不合格数
	private BigDecimal fialRate; //不合格率
	private String userName;   //创建人
	private Date createTime;  //创建时间
	private String stutus;   //状态
	
	//Setter And Getter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}
	public int getFailNum() {
		return failNum;
	}
	public void setFailNum(int failNum) {
		this.failNum = failNum;
	}
	public BigDecimal getFialRate() {
		return fialRate;
	}
	public void setFialRate(BigDecimal fialRate) {
		this.fialRate = fialRate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStutus() {
		return stutus;
	}
	public void setStutus(String stutus) {
		this.stutus = stutus;
	}
	
	
}
