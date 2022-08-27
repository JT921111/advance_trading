package com.jtdreamer.advance_trading.client;

public class ClientData {
	private static int tradeLv;
	private static int tradeRank;
	private static int skillPoint;
	private static int skill1;
	private static int skill2;

	public static void setTradeLv(int i) {
		ClientData.tradeLv = i;
	}

	public static int getPlayerTradeLevel() {
		return tradeLv;
	}

	public static void setTradeRank(int i) {
		ClientData.tradeRank = i;
	}

	public static int getPlayerTradeRank() {
		return tradeRank;
	}

	public static void setSkillPoint(int i) {
		ClientData.skillPoint = i;
	}

	public static int getPlayerSkillPoint() {
		return skillPoint;
	}

	public static void setSkill1(int i) {
		ClientData.skill1 = i;
	}

	public static int getPlayerSkill1() {
		return skill1;
	}

	public static void setSkill2(int i) {
		ClientData.skill2 = i;
	}

	public static int getPlayerSkill2() {
		return skill2;
	}
}
