package com.shopmax.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shopmax.dto.ItemSearchDto;
import com.shopmax.entity.Item;
import com.shopmax.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemRestController {
	
	/*
	private final ItemService itemService;

	@GetMapping(value = { "/admin/items", "/admin/items{page}" })
	public Page<Item> itemManage(ItemSearchDto itemSearchDto,
			@PathVariable("page") Optional<Integer> page, Model model) {
		
		// url경로에 페이지가 있으면 해당 페이지 번호를 조회하도록하고, 페이지 번호가 없으면 0페이지룰 조회
		Pageable pageable  = PageRequest.of(page.isPresent() ? page.get() : 0, 3);	// of(조회할 페이지의 번호: 0부터 시작, 한 페이지 당 조회할 데이터의 갯수)
		
		Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);
		
		//model.addAttribute("items", items);
		//model.addAttribute("itemSearchDto", itemSearchDto);
		//model.addAttribute("maxPage", 5);	// 상품관리 페이지 하단에 보여줄 최대페이지 번호
		
		return items;
	}
	
	*/
}
