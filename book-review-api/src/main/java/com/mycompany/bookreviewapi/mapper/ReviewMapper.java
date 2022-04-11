package com.mycompany.bookreviewapi.mapper;

import com.mycompany.bookreviewapi.input.ReviewInput;
import com.mycompany.bookreviewapi.model.Review;
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
