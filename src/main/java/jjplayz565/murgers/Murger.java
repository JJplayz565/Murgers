package jjplayz565.murgers;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class Murger extends Item{
    public Murger(Settings settings){
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type){
        String temp = itemStack.toString();
        int index = temp.indexOf(" ");
        String item = temp.substring(index + 1);
        tooltip.add(Text.translatable("item.murgers." + item + ".tooltip"));
    }

    
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity player = (PlayerEntity) user;
        player.incrementStat(Murgers.EATEN_MURGERS);
        return super.finishUsing(stack, world, user);
    }
    
}
