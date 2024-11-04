package com.polarbookshop.catalog_service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.polarbookshop.catalog_service.domain.Book;

// Identifies a test class that focuses on JSON serialization
@JsonTest
class BookJsonTests {

    // Utility class to assert JSON serialization and deserialization
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1234567890", "Title", "Author", 9.90);
        var jsonContent = json.write(book);
        // Verifying the parsing from Java to JSON, using JsonPath format to navigate
        // the JSON object
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
    }

    // @Test
    // void testDeserialize() throws Exception {

    // // Defines a JSON object using the Java text
    // var content = "{\"isbn\": \"1234567890\", \"title\": \"Title\", \"author\":
    // \"Author\", \"price\": 9.90}";

    // assertThat(json.parse(content)).usingRecursiveComparison()
    // .isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
    // }

}
