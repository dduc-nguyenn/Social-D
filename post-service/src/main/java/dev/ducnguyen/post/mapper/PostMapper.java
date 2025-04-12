package dev.ducnguyen.post.mapper;

import dev.ducnguyen.post.dto.response.PostResponse;
import dev.ducnguyen.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
