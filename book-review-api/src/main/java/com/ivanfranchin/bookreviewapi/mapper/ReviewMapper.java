package com.ivanfranchin.bookreviewapi.mapper;

import com.ivanfranchin.bookreviewapi.graphql.input.ReviewInput;
import com.ivanfranchin.bookreviewapi.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ReviewMapper {

    @Mapping(target = "createdAt", ignore = true)
    Review toReview(ReviewInput reviewInput);
}
