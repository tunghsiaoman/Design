package com.design.pattern.proxy;

/**
 * 王婆这个人太聪明了，她太老了，所以男人肯定不会看上她	<br>
 * 但是她有智慧跟经验，她作为一类女人的代理！
 * @author xiaoman.dong
 * @date 2016-09-11 22:57:05
 */
public class WangPo implements KingWomen {
	
	private KingWomen kingWomen;
	
	public WangPo() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 她可以是KingWomen的任何一个女人的代理，只要你是这一类型
	public WangPo(KingWomen kingWomen) {
		super();
		this.kingWomen = kingWomen;
	}

	@Override
	public void makeEyesWithMan() {
		// 王婆年纪这么大，谁会看上她 ？ 
		this.kingWomen.makeEyesWithMan();
	}

	@Override
	public void happyWithMan() {
		// 自己老了，干不了，可以让年轻的代替。
		this.kingWomen.happyWithMan();
	}

}
