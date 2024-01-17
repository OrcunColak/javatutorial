package com.colak.mock_with_proxy;

import java.util.List;

class Controller {
    private final ItemService itemService;

    public Controller(ItemService itemService) {
        this.itemService = itemService;
    }

    public Item fetch(int id) {
        return itemService.fetch(id);
    }

    public List<Item> fetchAll() {
        return itemService.fetchAll();
    }
}
