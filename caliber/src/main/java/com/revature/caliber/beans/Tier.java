package com.revature.caliber.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The type Tier.
 */
@Entity
@Table(name = "CALIBER_TIER")
public class Tier {

	@Id
	@Column(name = "TIER_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIER_ID_SEQUENCE")
	@SequenceGenerator(name = "TIER_ID_SEQUENCE", sequenceName = "TIER_ID_SEQUENCE")
	@NotNull
    private short tierId;
    
	@Column(name = "TIER", nullable = false)
	@NotNull
	private String tier;

	@Column(name = "RANKING", nullable = false)
	@NotNull
	private short ranking;

    /**
     * Gets tier id.
     *
     * @return the tier id
     */
    public short getTierId() {
        return tierId;
    }

    /**
     * Sets tier id.
     *
     * @param tierId the tier id
     */
    public void setTierId(short tierId) {
        this.tierId = tierId;
    }

    /**
     * Gets tier.
     *
     * @return the tier
     */
    public String getTier() {
        return tier;
    }

    /**
     * Sets tier.
     *
     * @param tier the tier
     */
    public void setTier(String tier) {
        this.tier = tier;
    }

    /**
     * Gets ranking.
     *
     * @return the ranking
     */
    public short getRanking() {
        return ranking;
    }

    /**
     * Sets ranking.
     *
     * @param ranking the ranking
     */
    public void setRanking(short ranking) {
        this.ranking = ranking;
    }

    /**
     * Instantiates a new Tier.
     *
     * @param tierId  the tier id
     * @param tier    the tier
     * @param ranking the ranking
     */
    public Tier(short tierId, String tier, short ranking) {
        super();
        this.tierId = tierId;
        this.tier = tier;
        this.ranking = ranking;
    }

    /**
     * Instantiates a new Tier.
     */
    public Tier() {
        super();
    }

    /**
     * Instantiates a new Tier.
     *
     * @param tier    the tier
     * @param ranking the ranking
     */
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
