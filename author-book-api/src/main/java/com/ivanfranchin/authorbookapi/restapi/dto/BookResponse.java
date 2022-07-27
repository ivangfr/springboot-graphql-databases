package com.ivanfranchin.authorbookapi.restapi.dto;

public record BookResponse(Long id, String isbn, String title, Integer year) {
}
