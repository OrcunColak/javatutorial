package com.colak.proxy.mock_with_proxy;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MockerTest {

    public static void main(String[] args) {
        ItemService userServiceMock = Mocker.createMock(ItemService.class);

        Mocker.when(userServiceMock.fetch(1))
                .thenReturn(new Item("An item returned from mocked service"));

        Mocker.when(userServiceMock.fetchAll())
                .thenReturn(List.of(
                        new Item("Item 1"),
                        new Item("Item 2")
                ));

        Controller controller = new Controller(userServiceMock);

        Item item = controller.fetch(1);
        log.info("A mocked item: {}", item);


        List<Item> items = controller.fetchAll();
        log.info("Mocked items: {}", items);
    }
}
