package dev.ducnguyen.identity.repository.httpclient;

import dev.ducnguyen.identity.configuration.AuthenticationRequestInterceptor;
import dev.ducnguyen.identity.dto.request.ProfileCreateRequest;
import dev.ducnguyen.identity.dto.response.ApiResponse;
import dev.ducnguyen.identity.dto.response.ProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
        configuration = { AuthenticationRequestInterceptor.class })
public interface ProfileClient {
    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<ProfileResponse> createProfile(@RequestBody ProfileCreateRequest request);
}
