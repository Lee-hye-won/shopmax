package com.shopmax.dto;

import com.shopmax.constant.itemSellStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
	private String searchDateType;
	private itemSellStatus searchSellStatus;
	private String searchBy;
	private String searchQuery = "";
}
