package com.pricklen.machines.block;

import net.minecraftforge.items.IItemHandler;
import net.minecraft.world.item.ItemStack;

public class SingleSlotItemHandler implements IItemHandler {

    private final IItemHandler base;
    private final int slot;

    public SingleSlotItemHandler(IItemHandler base, int slot) {
        this.base = base;
        this.slot = slot;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return base.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int index, ItemStack stack, boolean simulate) {
        return base.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int index, int amount, boolean simulate) {
        return base.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int index) {
        return base.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int index, ItemStack stack) {
        return base.isItemValid(slot, stack);
    }
}
