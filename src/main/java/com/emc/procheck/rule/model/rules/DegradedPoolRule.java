package com.emc.procheck.rule.model.rules;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.rule.model.AbstractRule;
import com.emc.procheck.rule.model.RuleResult.RuleStatus;
import com.emc.procheck.storage.model.UemPool;
import com.emc.procheck.storage.service.UemPoolService;
import com.emc.procheck.util.ApplicationContextUtil;

public class DegradedPoolRule extends AbstractRule {
    
    private final static Logger logger = LoggerFactory.getLogger(DegradedPoolRule.class);
    
    @Override
    protected void execute(String systemKey) {
        logger.debug("Running DegradedPoolRule " + this.toString());
        UemPoolService service = (UemPoolService) ApplicationContextUtil.getBean(UemPoolService.BEANNAME);
        List<UemPool> pools = service.findBySystemKey(systemKey);
        boolean failed = false;
        double totalSize = 0;
        for (UemPool pool: pools) {
            totalSize += pool.getSizeTotal();
        }
        
        int score = MAX_SCORE;
        
        for (UemPool pool: pools) {
            if ("OK".equalsIgnoreCase(pool.getHealthValue()) 
                    || "OK_BUT".equalsIgnoreCase(pool.getHealthValue())) {
            	logger.debug("Pool is in good health: " + pool.getPoolKey());
                continue;
            }
            failed = true;
            double degradedPercent = pool.getSizeTotal() / totalSize;

            String poolId = pool.getPoolKey();
            if (pool.getName() != null && !pool.getName().isEmpty()) {
                poolId += "(" + pool.getName() + ")";
            }
                
            score = score - (int)(MAX_SCORE * degradedPercent);
            result.addAction("Pool " + poolId + " is in unhealthy state (" + pool.getHealthValue() + ")");
        }
        
        result.setScore(score);
        
        if (failed) {
            result.setStatus(RuleStatus.FAIL);
        }
        else {
            result.setStatus(RuleStatus.PASS); 
        }
        
        result.setEndTime(new Date());
    }

}
