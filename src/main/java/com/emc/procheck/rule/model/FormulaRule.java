package com.emc.procheck.rule.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.rule.exception.ImpactCalculationException;
import com.emc.procheck.util.ImpactCalculateUtil;

public abstract class FormulaRule extends AbstractRule {

	private final static Logger logger = LoggerFactory.getLogger(FormulaRule.class);
	
    private String impactFormula; 
    
    private Double minImpactedRatio;

    public void setImpactFormula(String impactFormula) {
        this.impactFormula = impactFormula;
    }
    
    public void setMinImpactedRatio(Double minImpactedRatio) {
        this.minImpactedRatio = minImpactedRatio;
    }
    
	/**
	 * Calculate imapct using formula.
	 * @param x
	 * @return
	 */
	protected int calculateImpact(double x) {
		String formula = this.impactFormula;
		try {
			// When min radio is set and is bigger than x, use min radio to calculate.
			if(this.minImpactedRatio != null && this.minImpactedRatio > 0 && x < this.minImpactedRatio)
				x = this.minImpactedRatio;
			
			return (int) ImpactCalculateUtil.calculateXFormula(formula, x);
		} catch (ImpactCalculationException e) {
			logger.error(
					"Failed to calculate impact with formula[" + formula + "] on x[" + x + "]. Use default imapct. ",
					e);
			return this.getDefaultImpact();
		}
	}

}
