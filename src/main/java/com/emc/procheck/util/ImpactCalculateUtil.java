/**
 * 
 */
package com.emc.procheck.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.rule.exception.ImpactCalculationException;

/**
 * Utility to do rule impact calculation.
 *
 */
public class ImpactCalculateUtil {
    
    private final static Logger logger = LoggerFactory.getLogger(ImpactCalculateUtil.class);
    
    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

    
    public static double calculateXFormula(String formula, double x) throws ImpactCalculationException{
        if (StringUtils.isEmpty(formula)) {
            throw new IllegalArgumentException("Formula should not be empty.");
        }
        
        if (formula.indexOf("x") == -1) {
            throw new IllegalArgumentException("Formula doesn't contain x variable.");
        }
        
        StringBuilder bd = new StringBuilder();
        bd.append("var x = ").append(x).append(";");
        bd.append(formula);
        String script = bd.toString();
        
        try {
            logger.info("About to evaluate script: " + script);
            Object value = engine.eval(script);
            return Double.parseDouble(value.toString());
        } catch (ScriptException e) {
            logger.error("Failed to evaluate script value.", e);
            throw new ImpactCalculationException(e.getMessage());
        }
    }

}
