package org.example.repository;

import org.example.entity.Inventory;

public class InventoryRepository extends FoundationReposirory<Inventory>{
    public InventoryRepository() {
        super(Inventory.class);
    }
}
