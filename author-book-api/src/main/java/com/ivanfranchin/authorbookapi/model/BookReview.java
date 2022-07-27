package com.ivanfranchin.authorbookapi.model;

import java.util.List;

public record BookReview(String error, String id, List<Review> reviews) {
}
