package com.colak.stringformatting.messageformat.number;

import java.text.MessageFormat;

public class MessageFormatNumberTest {

    public static void main() {

        Object[] objects = {1234.5678, 10.0};

        // Basic formatting. This uses grouping separators
        String message = MessageFormat.format("Number {0,number} and {1,number}", objects);
        // Number 1.234,568 and 10
        System.out.println("message = " + message);

        // Controlling decimal places. This adds decimal separator but does not add two decimal places if they are zero
        message = MessageFormat.format("Number {0,number,#.##} and {1,number,#.##}", objects);
        // Number 1234,57 and 10
        System.out.println("message = " + message);

        // Controlling decimal places. This adds decimal separator and always adds two decimal places even if they are zero
        message = MessageFormat.format("Number {0,number,0.00} and {1,number,0.00}", objects);
        // Number 1234,57 and 10,00
        System.out.println("message = " + message);

        // Controlling decimal places. This adds both thousands separator and decimal separator
        message = MessageFormat.format("Number {0,number,#,###.00} and {1,number,#,###.00}", objects);
        // Number 1.234,57 and 10,00
        System.out.println("message = " + message);
    }
}
