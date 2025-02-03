package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.Entities.Inventory;
import dev.michaelcao512.revshop_springboot.Entities.Notification;
import dev.michaelcao512.revshop_springboot.Entities.User;
import dev.michaelcao512.revshop_springboot.Repositories.InventoryRepository;
import dev.michaelcao512.revshop_springboot.Repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final NotificationRepository notificationRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            NotificationRepository notificationRepository) {
        this.inventoryRepository = inventoryRepository;
        this.notificationRepository = notificationRepository;
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
            checkStock(inventory);
        }
    }

    private void checkStock(Inventory inventory) {
        User seller = inventory.getProduct().getSeller();
        Notification notification = new Notification();
        notification.setUser(seller);
        notification.setMessage("Inventory for product " + inventory.getProduct().getName() + " is low");
        notification.setType(Notification.NotificationType.WEB);
        notificationRepository.save(notification);



    }

}
