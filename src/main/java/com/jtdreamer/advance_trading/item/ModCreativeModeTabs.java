package com.jtdreamer.advance_trading.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
	public static final CreativeModeTab ADVANCE_TRADING_TAB = new CreativeModeTab("advance_trading_tab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.TAB_ICON.get());
		}
	};
}
