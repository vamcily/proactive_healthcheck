package com.emc.procheck.rule.model.rules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.rule.model.FormulaRule;
import com.emc.procheck.rule.model.HealthPacket;
import com.emc.procheck.rule.model.RuleResult.RuleStatus;
import com.emc.procheck.storage.model.UemPool;
import com.emc.procheck.storage.service.UemPoolService;
import com.emc.procheck.util.ApplicationContextUtil;

public class DegradedPoolRule extends FormulaRule {
    
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
        for (UemPool pool: pools) {
            Map<String, String> rawData = new HashMap<String, String>();
            HealthPacket packet = createHealthPacket();
            if ("OK".equalsIgnoreCase(pool.getHealthValue()) 
                    || "OK_BUT".equalsIgnoreCase(pool.getHealthValue())) {
                packet.setImpact(0);
                result.addHealthPacket(pool.getPoolKey(), packet);
                continue;
            }
            failed = true;
            double degradedPercent = pool.getSizeTotal() / totalSize;

            String poolId = pool.getPoolKey();
            if (pool.getName() != null && !pool.getName().isEmpty()) {
                poolId += "(" + pool.getName() + ")";
            }
            rawData.put(poolId, pool.getHealthValue());
            packet.setDescription("Pool " + poolId + " is in unhealthy state (" + pool.getHealthValue() + ")");
            List<String> resolution = new ArrayList<String>();
            resolution.add("Check whether it's because of hardware failure. If it is, contact your service provider to order and replace the faulted haredware.");
            packet.setRemediation(resolution);
                
            int calculatedImpact = calculateImpact(degradedPercent);
            packet.setImpact(calculatedImpact);
            packet.setRawData(rawData);
                
            result.addHealthPacket(pool.getPoolKey(), packet);
        }
        if (failed) {
            result.setStatus(RuleStatus.FAIL);
        }
        else {
            result.setStatus(RuleStatus.PASS); 
        }
        
        result.setEndTime(new Date());
    }

}
