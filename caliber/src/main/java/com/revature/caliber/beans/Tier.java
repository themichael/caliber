package com.revature.caliber.beans;

public class Tier {

	private short tierId;
	private String tier;
	private short ranking;
	public short getTierId() {
		return tierId;
	}
	public void setTierId(short tierId) {
		this.tierId = tierId;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public short getRanking() {
		return ranking;
	}
	public void setRanking(short ranking) {
		this.ranking = ranking;
	}
	public Tier(short tierId, String tier, short ranking) {
		super();
		this.tierId = tierId;
		this.tier = tier;
		this.ranking = ranking;
	}
	public Tier() {
		super();
	}
	public Tier(String tier, short ranking) {
		super();
		this.tier = tier;
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "Tier{" +
				"tierId=" + tierId +
				", tier='" + tier + '\'' +
				", ranking=" + ranking +
				'}';
	}
}
