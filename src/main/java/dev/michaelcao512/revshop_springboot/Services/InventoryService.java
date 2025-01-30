package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.Entities.Inventory;
import dev.michaelcao512.revshop_springboot.Repositories.InventoryRepository;
import dev.michaelcao512.revshop_springboot.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean checkInventory(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        return inventory.getQuantity() >= quantity;
    }

    public void updateInventory(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        int newQuantity = inventory.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new RuntimeException("Not enough inventory");
        }

        inventory.setQuantity(newQuantity);
        inventoryRepository.save(inventory);

        if (newQuantity < inventory.getThreshold()){
            sendNotification(inventory);
        }
    }

    private void sendNotification(Inventory inventory) {
        // TODO: send web notification for low stock (notification entity))
        System.out.println("Low stock for product: " + inventory.getProduct().getName());
    }

}
