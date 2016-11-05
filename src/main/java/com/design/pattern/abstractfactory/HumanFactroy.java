package com.design.pattern.abstractfactory;

public interface HumanFactroy {
	
	// 制造黄种人
	Human createYelloHuman();
	
	// 制造白种人
	Human createWhiteHuman();
	
	// 制造黑人
	Human createBlackHuman();
	
}
