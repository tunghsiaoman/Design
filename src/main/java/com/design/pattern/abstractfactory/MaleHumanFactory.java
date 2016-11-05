package com.design.pattern.abstractfactory;

public class MaleHumanFactory extends AbstractHumanFactory {

	@Override
	public Human createYelloHuman() {
		// TODO Auto-generated method stub
		return super.createHuman(HumanEnum.YellowMaleHuman);
	}

	@Override
	public Human createWhiteHuman() {
		// TODO Auto-generated method stub
		return super.createHuman(HumanEnum.WhiteMaleHuman);
	}

	@Override
	public Human createBlackHuman() {
		// TODO Auto-generated method stub
		return super.createHuman(HumanEnum.BlackMaleHuman);
	}

}
