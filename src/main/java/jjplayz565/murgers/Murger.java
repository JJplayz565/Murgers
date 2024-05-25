package jjplayz565.murgers;

import java.util.List;

import net.minecraft.client.item.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

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

    
    
}
