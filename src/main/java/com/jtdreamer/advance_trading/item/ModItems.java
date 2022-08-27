package com.jtdreamer.advance_trading.item;

import com.jtdreamer.advance_trading.ModInit;
import com.jtdreamer.advance_trading.item.custom.StockIncreaseItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModInit.MODID);

	public static final RegistryObject<Item> TAB_ICON = ITEMS.register("tab_icon",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> STOCK_INCREASE = ITEMS.register("stock_increase",
			() -> new StockIncreaseItem(
					new Item.Properties().tab(ModCreativeModeTabs.ADVANCE_TRADING_TAB).stacksTo(16)));

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
