package com.bookstore.jpa.dtos;

import java.util.Set;
import java.util.UUID;

public record BookStoreRecordDto(String title , UUID publisherId, Set<UUID> authorIds,
                                 String reviewComment) {
    // tinha q colocar
    // NotBlank mas esqueci do validation
}
