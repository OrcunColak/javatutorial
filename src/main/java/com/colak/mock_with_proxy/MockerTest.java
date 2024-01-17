package com.colak.mock_with_proxy;

import java.util.List;

import static com.colak.mock_with_proxy.Mocker.when;

public class MockerTest {

    public static void main(String[] args) {
        ItemService userServiceMock = Mocker.createMock(ItemService.class);

        when(userServiceMock.fetch(1))
                .thenReturn(new Item("An item returned from mocked service"));

        when(userServiceMock.fetchAll())
                .thenReturn(List.of(
                        new Item("Item 1"),
                        new Item("Item 2")
                ));

        Controller controller = new Controller(userServiceMock);

        Item item = controller.fetch(1);
        System.out.println("A mocked item: " + item);


        List<Item> items = controller.fetchAll();
        System.out.println("Mocked items: " + items);
    }
}
