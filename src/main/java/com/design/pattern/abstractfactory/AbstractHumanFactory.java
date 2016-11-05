package com.design.pattern.abstractfactory;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractHumanFactory implements HumanFactroy {
	
	protected Human createHuman(HumanEnum humanEnum){
		Human human = null;
		// 如果传递进来不是一个Enum中具体的一个Element的话,则不处理
		if(StringUtils.isNotBlank(humanEnum.getValue())){
			try {
				human = (Human) Class.forName(humanEnum.getValue()).newInstance();
			} catch (Exception e) {
				//因为使用了enum,这个种异常情况不会产生了,除非你的enum有问题
				e.printStackTrace();
			}
		}
		return human;
	}
}
