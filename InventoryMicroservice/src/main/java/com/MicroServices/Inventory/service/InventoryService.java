package com.MicroServices.Inventory.service;

import com.MicroServices.Inventory.repository.InventoryRepository;
import com.MicroServices.Inventory.model.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(List<String> skuCodes) {
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);
        for(Inventory inventory: inventories){
            if(inventory.getQuantity()<0){
                return false;
            }
        }
        return true;
    }
}
