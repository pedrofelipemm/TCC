package br.com.mendes.comparator;

import java.util.Comparator;

import br.com.mendes.model.Item;

public class ItemComparator extends Item implements Comparator<Item> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1657116995364632160L;

	@Override
	public int compare(Item item, Item outroItem) {
		return item.getNome().compareTo(outroItem.getNome());
	}

}
