package me.BadBones69.CrazyEnchantments.API;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

import me.BadBones69.CrazyEnchantments.Api;
import me.BadBones69.CrazyEnchantments.Main;

public class CustomEBook {
	
	CustomEnchantments CustomE = CustomEnchantments.getInstance();
	
	String enchantment;
	int amount;
	int power;
	int destory_rate;
	int success_rate;
	
	/**
	 * 
	 * @param enchantment Enchantment you want.
	 * @param power Tier of the enchantment.
	 * @param amount Amount of books you want.
	 */
	public CustomEBook(String enchantment, Integer power, Integer amount){
		this.enchantment = enchantment;
		this.amount = amount;
		this.power = power;
		int Smax = Main.settings.getConfig().getInt("Settings.BlackScroll.SuccessChance.Max");
		int Smin = Main.settings.getConfig().getInt("Settings.BlackScroll.SuccessChance.Min");
		int Dmax = Main.settings.getConfig().getInt("Settings.BlackScroll.DestroyChance.Max");
		int Dmin = Main.settings.getConfig().getInt("Settings.BlackScroll.DestroyChance.Min");
		this.destory_rate = percentPick(Dmax, Dmin);
		this.success_rate = percentPick(Smax, Smin);
	}
	
	/**
	 * 
	 * @param enchantment Set the enchantment.
	 */
	public void setEnchantemnt(String enchantment){
		this.enchantment = enchantment;
	}
	
	/**
	 * 
	 * @param amount Set the amount of books.
	 */
	public void setAmount(Integer amount){
		this.amount = amount;
	}
	
	/**
	 * 
	 * @param power Set the tier of the enchantment.
	 */
	public void setPower(Integer power){
		this.power = power;
	}
	
	/**
	 * 
	 * @param destory_rate Set the destroy rate on the book.
	 */
	public void setDestoryRate(Integer destory_rate){
		this.destory_rate = destory_rate;
	}
	
	/**
	 * 
	 * @param success_rate Set the success rate on the book.
	 */
	public void setSuccessRate(Integer success_rate){
		this.success_rate = success_rate;
	}
	
	/**
	 * 
	 * @return Return the book as an ItemStack.
	 */
	public ItemStack buildBook(){
		String item = Main.settings.getConfig().getString("Settings.Enchantment-Book-Item");
		String name = CustomE.getBookColor(enchantment) + CustomE.getCustomName(enchantment) + " " + convertPower(power);
		List<String> lore = new ArrayList<String>();
		for(String l : Main.settings.getConfig().getStringList("Settings.EnchantmentBookLore")){
			if(l.contains("%Description%")||l.contains("%description%")){
				for(String m : CustomE.getDiscription(enchantment)){
					lore.add(Api.color(m));
				}
			}else{
				lore.add(Api.color(l)
						.replaceAll("%Destroy_Rate%", destory_rate+"").replaceAll("%destroy_rate%", destory_rate+"")
						.replaceAll("%Success_Rate%", success_rate+"").replaceAll("%success_Rate%", success_rate+""));
			}
		}
		return Api.makeItem(item, amount, name, lore);
	}
	
	private String convertPower(Integer i){
		if(i==0)return "I";
		if(i==1)return "I";
		if(i==2)return "II";
		if(i==3)return "III";
		if(i==4)return "IV";
		if(i==5)return "V";
		if(i==6)return "VI";
		if(i==7)return "VII";
		if(i==8)return "VII";
		if(i==9)return "IX";
		if(i==10)return "X";
		return i+"";
	}
	
	private Integer percentPick(int max, int min){
		Random i = new Random();
		return min+i.nextInt(max-min);
	}
}