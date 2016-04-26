package com.emc.procheck.storage.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.procheck.storage.dao.InventoryEnvironmentRepository;
import com.emc.procheck.storage.model.InventoryEnvironment;

@Service(value = "inventoryEnvironmentServiceTransform")
public class InventoryEnvironmentServiceImpl extends TransformServiceImpl<InventoryEnvironment, String>
		implements InventoryEnvironmentService {
	
	private final static Logger logger = LoggerFactory.getLogger(InventoryEnvironmentServiceImpl.class);

	@Autowired
	public InventoryEnvironmentServiceImpl(InventoryEnvironmentRepository repository) {
		super(repository);
	}
	
	private InventoryEnvironmentRepository getRepository(){
		return (InventoryEnvironmentRepository) this.repository;
	}

	@Override
	public List<InventoryEnvironment> findByInventoryKey(String inventoryKey) {
		return getRepository().findByInventoryKey(inventoryKey);
	}

	@Override
	public List<InventoryEnvironment> findTempBySystemKey(String systemKey) {
		return getRepository().findTempBySystemKey(systemKey);
	}

	@Override
	public Integer findAvgTempBySystemKey(String systemKey) {
		List<InventoryEnvironment> inventEns = findTempBySystemKey(systemKey);
		
		int avgTempC = 0;
		int cout = 0;
		if(inventEns != null) {
			for(InventoryEnvironment inv : inventEns){
				if(inv.getAverageTempC() != null){
					avgTempC += inv.getAverageTempC();
					cout++;
				}
			}
		}
		
		if(cout == 0){
			logger.debug("Return false because no temperature is got for systemKey: " + systemKey);
			return 0;
		}
		avgTempC = avgTempC / cout;
		
		return avgTempC;
	}

	@Override
	public List<InventoryEnvironment> findInventoryEnvironment(String systemKey, String inventoryType) {
	    return getRepository().findInventoryEnvironment(systemKey, inventoryType);
	}
}
