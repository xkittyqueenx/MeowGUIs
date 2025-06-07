package dev.xkittyqueenx.meowGUIs.gui;

import dev.xkittyqueenx.meowGUIs.MeowGUIs;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class GuiPage implements Listener {

    private final Inventory inventory;
    private final Map<Integer, Runnable> actions = new HashMap<>();

    public GuiPage(Player player, String title, int rows) {
        this.inventory = Bukkit.createInventory(player, rows * 9, MiniMessage.miniMessage().deserialize(title));
        Bukkit.getPluginManager().registerEvents(this, MeowGUIs.getPlugin());
    }

    protected void setItem(int slot, ItemStack item, Runnable onClick) {
        inventory.setItem(slot, item);
        actions.put(slot, onClick);
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public abstract void build();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!Objects.equals(e.getClickedInventory(), inventory)) return;
        e.setCancelled(true);
        if (e.getWhoClicked() instanceof Player) {
            Runnable action = actions.get(e.getRawSlot());
            if (action != null) action.run();
        }
    }

}
