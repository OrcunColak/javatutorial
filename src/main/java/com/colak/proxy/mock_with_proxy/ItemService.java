package com.colak.proxy.mock_with_proxy;

import java.util.List;

interface ItemService {
    Item fetch(int id);

    List<Item> fetchAll();
}