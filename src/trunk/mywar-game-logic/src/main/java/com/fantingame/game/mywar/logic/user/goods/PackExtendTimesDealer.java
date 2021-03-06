package com.fantingame.game.mywar.logic.user.goods;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;
import com.fantingame.game.mywar.logic.user.service.UserService;

public class PackExtendTimesDealer implements IDealGoods {
    @Autowired
	private UserService userService;
	@Override
	public GoodsType getGoodsType() {
		return GoodsType.packExtendTimes;
	}
	@Override
	public void sendGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		userService.addPackExtendTimes(userId, goodsNum,GoodsUseType,drop);
	}
	@Override
	public void reduceGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		throw new RuntimeException("没有实现背包扩展次数的减少"+userId+",goodsNum="+goodsNum);
	}
}
