package com.pricklen.machines.screen;

import com.pricklen.machines.block.ModBlocks;
import com.pricklen.machines.block.entity.KilnControllerBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class KilnMenu extends AbstractContainerMenu {

    public final KilnControllerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public KilnMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }
    public KilnMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.KILN_MENU.get(), pContainerId);
        checkContainerSize(inv, 2);
        blockEntity = (KilnControllerBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;
        addPlayerHotbar(inv);
        addPlayerInventory(inv);
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 56, 17));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 56, 53));
            this.addSlot(new SlotItemHandler(iItemHandler, 2, 116, 35));
        });
        addDataSlots(data);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();

            if (index == 38) {
                if (!this.moveItemStackTo(stack, 0, 36, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onTake(player, stack);
            }

            else if (index == 36 || index == 37) {
                if (!this.moveItemStackTo(stack, 0, 36, false)) {
                    return ItemStack.EMPTY;
                }
            }

            else if (index >= 0 && index < 36) {

                boolean movedToBlock;

                if (isFuel(stack)) {
                    movedToBlock = this.moveItemStackTo(stack, 37, 38, false);
                } else {
                    movedToBlock = this.moveItemStackTo(stack, 36, 38, false);
                }

                if (movedToBlock) {
                    return ItemStack.EMPTY;
                }

                if (index <= 8) {
                    if (!this.moveItemStackTo(stack, 9, 36, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(stack, 0, 9, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }
        }

        return result;
    }
    private boolean isFuel(ItemStack stack) {
        return net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.isFuel(stack);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, ModBlocks.KILN.get());
    }
    public boolean isCrafting() {
        return data.get(0) > 0;
    }
    public boolean isFueling() {
        return data.get(2) > 0;
    }
    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 24;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
    public int getScaledFuel() {
        int progress = this.data.get(2);
        int maxProgress = this.data.get(3);
        int progressArrowSize = 14;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
}