/**
 * 
 */
package com.emc.procheck.rule.model.rules;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emc.procheck.rule.model.AbstractRule;
import com.emc.procheck.rule.model.RuleResult.RuleStatus;
import com.emc.procheck.storage.model.Inventory;
import com.emc.procheck.storage.service.InventoryService;
import com.emc.procheck.util.ApplicationContextUtil;

public class DiskRule extends AbstractRule {
    
    private static final String HTYPE = "disk";
    
    private final static Logger logger = LoggerFactory.getLogger(DiskRule.class);
    
    @Override
    public void execute(String systemKey) {
        InventoryService service = (InventoryService) ApplicationContextUtil.getBean(InventoryService.BEANNAME);
        List<Inventory> disks = service.findAllByHardwareType(systemKey, HTYPE);

        if (disks == null || disks.isEmpty()) {
            logger.info("No disk found for " + systemKey);
            result.setStatus(RuleStatus.NOT_APPLICABLE);
            result.setEndTime(new Date());
            return;
        }
        
        boolean failed = false;
        double score = MAX_SCORE;
        double unitScore = score / disks.size();
        
        // run the rule against default rule config
        for (Inventory disk : disks) {
            if (disk.getNeedsReplacement() == null) {
                logger.warn("No needReplacement value for " + disk.getInventoryKey());
                continue;
            }
             
            if (disk.getNeedsReplacement()) {
                logger.info("Found disk need replacement: " + disk.getInventoryKey());
                failed = true;
                
                String diskName = disk.getName();
                if (StringUtils.isEmpty(diskName)) {
                    diskName = disk.getInventoryKey();
                }
                
                result.addAction("The disk " + diskName + " needs replacement.");
                score -= unitScore;
            } 
        }
        
        result.setScore((int)score);
        if (failed) {
            result.setStatus(RuleStatus.FAIL);
        }
        else {
            result.setStatus(RuleStatus.PASS); 
        }
        
        result.setEndTime(new Date());
    }

}
