package com.design.pattern.abstractfactory;

public class WomanHumanFactory extends AbstractHumanFactory {

	@Override
	public Human createYelloHuman() {
		// TODO Auto-generated method stub
		return super.createHuman(HumanEnum.YellowWomanHuman);
	}

	@Override
	public Human createWhiteHuman() {
		// TODO Auto-generated method stub
		return super.createHuman(HumanEnum.WhiteWomanHuman);
	}

	@Override
	public Human createBlackHuman() {
		// TODO Auto-generated method stub
		return super.createHuman(HumanEnum.BlackWomanHuman);
	}

}
