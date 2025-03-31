package dev.ducnguyen.identity.mapper;

import dev.ducnguyen.identity.dto.request.PostCreateRequest;
import dev.ducnguyen.identity.dto.request.PostUpdateRequest;
import dev.ducnguyen.identity.dto.response.PostResponse;
import dev.ducnguyen.identity.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostCreateRequest request);

    PostResponse toPostResponse(Post post);

    void updatePost(@MappingTarget Post post, PostUpdateRequest request);
}
