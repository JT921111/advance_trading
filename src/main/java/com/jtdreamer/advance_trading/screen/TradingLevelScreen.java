package com.jtdreamer.advance_trading.screen;

import com.jtdreamer.advance_trading.ModInit;
import com.jtdreamer.advance_trading.client.ClientData;
import com.jtdreamer.advance_trading.key.ModKeyBinds;
import com.jtdreamer.advance_trading.networking.ModPackets;
import com.jtdreamer.advance_trading.networking.packet.Skill1UpgradePacket;
import com.jtdreamer.advance_trading.networking.packet.Skill2UpgradePacket;
import com.jtdreamer.advance_trading.networking.packet.Skill3UsedPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class TradingLevelScreen extends Screen {
	private int skillSelected = 0;

	public TradingLevelScreen(Component component) {
		super(component);
	}

	@Override
	public final void init() {
		super.init();

		this.addRenderableWidget(new ExtendedButton((this.width - 175) / 2 + 64, (this.height - 165) / 2 + 47, 16, 16,
				Component.literal("Button1"), e -> {
					skillSelected = 1;
					if (ClientData.getPlayerSkillPoint() > 0 && ClientData.getPlayerSkill1() < 20) {
						ModPackets.sendToServer(new Skill1UpgradePacket());
					}
				}));

		this.addRenderableWidget(new ExtendedButton((this.width - 175) / 2 + 124, (this.height - 165) / 2 + 47, 16, 16,
				Component.literal("Button2"), e -> {
					skillSelected = 2;
					if (ClientData.getPlayerSkillPoint() > 4 && ClientData.getPlayerSkill2() < 20) {
						ModPackets.sendToServer(new Skill2UpgradePacket());
					}
				}));

		this.addRenderableWidget(new ExtendedButton((this.width - 175) / 2 + 64, (this.height - 165) / 2 + 92, 16, 16,
				Component.literal("Button3"), e -> {
					skillSelected = 3;
					if (ClientData.getPlayerSkillPoint() > 2) {
						ModPackets.sendToServer(new Skill3UsedPacket());
					}
				}));
	}

	@Override
	public void render(PoseStack stack, int m, int n, float f) {
		super.renderBackground(stack);

		final ResourceLocation GUI = new ResourceLocation(ModInit.MODID, "textures/gui/trading_level_gui.png");

		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, GUI);

		int i = (this.width - 175) / 2;
		int j = (this.height - 165) / 2;

		int maxTradeLevel = 100 + ClientData.getPlayerTradeRank() * 10;
		int tradeLevel = ClientData.getPlayerTradeLevel();
		int levelWidth = 131 * tradeLevel / maxTradeLevel;
		int rank = ClientData.getPlayerTradeRank();
		int skpt = ClientData.getPlayerSkillPoint();
		int sk1 = ClientData.getPlayerSkill1();
		int sk1Width = 41 * sk1 / 50;
		int sk2 = ClientData.getPlayerSkill2();
		int sk2Width = 41 * sk2 / 50;

		this.blit(stack, i, j, 0, 0, 175, 165);

		// level bar
		this.blit(stack, i + 22, j + 22, 0, 246, 131, 5);
		this.blit(stack, i + 22, j + 22, 0, 251, levelWidth, 5);

		// skill icon
		this.blit(stack, i + 36, j + 33, 176, 0, 33, 33);
		this.blit(stack, i + 96, j + 33, 176, 33, 33, 33);
		this.blit(stack, i + 36, j + 78, 176, 66, 33, 33);

		// skill level bar
		this.blit(stack, i + 32, j + 64, 215, 0, 41, 5);
		this.blit(stack, i + 32, j + 64, 215, 5, sk1Width, 5);
		this.blit(stack, i + 92, j + 64, 215, 0, 41, 5);
		this.blit(stack, i + 92, j + 64, 215, 5, sk2Width, 5);

		// add level button
		this.blit(stack, i + 64, j + 47, 0, 166, 16, 16);
		this.blit(stack, i + 124, j + 47, 0, 166, 16, 16);
		this.blit(stack, i + 64, j + 92, 0, 166, 16, 16);

		this.font.draw(stack,
				Component.translatable("screen.advance_trading.trade_level").append(Component.literal(" " + rank)),
				i + 10, j + 9, 0x404040);
		this.font.draw(stack,
				Component.translatable("screen.advance_trading.skill_point").append(Component.literal(" " + skpt)),
				i + 101, j + 9, 0x404040);

		if (m > i + 21 && m < i + 154 && n > j + 21 && n < j + 28) {
			this.renderTooltip(stack, Component.literal(tradeLevel + "/" + maxTradeLevel), m, n);
		} else if (m > i + 31 && m < i + 74 && n > j + 63 && n < j + 70) {
			this.renderTooltip(stack, Component.literal(sk1 + "/20"), m, n);
		} else if ((Math.pow(m - i - 52.5, 2) + Math.pow(n - j - 49.5, 2) <= 256)) {
			this.renderTooltip(stack,
					Component.translatable("screen.advance_trading.skill_label").append(Component.literal(" 1")), m, n);
			skillSelected = 1;
		} else if (m > i + 91 && m < i + 134 && n > j + 63 && n < j + 70) {
			this.renderTooltip(stack, Component.literal(sk2 + "/20"), m, n);
		} else if ((Math.pow(m - i - 112.5, 2) + Math.pow(n - j - 49.5, 2) <= 256)) {
			this.renderTooltip(stack,
					Component.translatable("screen.advance_trading.skill_label").append(Component.literal(" 2")), m, n);
			skillSelected = 2;
		} else if ((Math.pow(m - i - 52.5, 2) + Math.pow(n - j - 94.5, 2) <= 256)) {
			this.renderTooltip(stack,
					Component.translatable("screen.advance_trading.skill_label").append(Component.literal(" 3")), m, n);
			skillSelected = 3;
		}

		if (skillSelected == 1) {
			this.font.draw(stack, Component.literal("§c" + sk1 + "%§r")
					.append(Component.translatable("screen.advance_trading.skill_1")), i + 16, j + 136, 0x404040);
			this.font.draw(stack, Component.literal("" + (sk2 + 1))
					.append(Component.translatable("screen.advance_trading.skill_2")), i + 16, j + 148, 0x404040);
		} else if (skillSelected == 2) {
			this.font.draw(stack,
					Component.literal(sk1 + "%").append(Component.translatable("screen.advance_trading.skill_1")),
					i + 16, j + 136, 0x404040);
			this.font.draw(stack, Component.literal("§c" + (sk2 + 1) + "§r")
					.append(Component.translatable("screen.advance_trading.skill_2")), i + 16, j + 148, 0x404040);
		} else if (skillSelected == 3) {
			this.font.draw(stack, Component.translatable("screen.advance_trading.skill_3"), i + 16, j + 136,
					0x404040);
		}
	}

	@Override
	public boolean keyPressed(int i, int j, int k) {
		if (i == 256 || i == ModKeyBinds.openLevel.getKey().getValue()) {
			this.onClose();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}