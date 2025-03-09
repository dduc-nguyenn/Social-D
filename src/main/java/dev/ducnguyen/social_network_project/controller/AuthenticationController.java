package dev.ducnguyen.social_network_project.controller;

import com.nimbusds.jose.JOSEException;
import dev.ducnguyen.social_network_project.dto.request.AuthenticationRequest;
import dev.ducnguyen.social_network_project.dto.request.IntrospectRequest;
import dev.ducnguyen.social_network_project.dto.response.ApiResponse;
import dev.ducnguyen.social_network_project.dto.response.AuthenticationResponse;
import dev.ducnguyen.social_network_project.dto.response.IntrospectResponse;
import dev.ducnguyen.social_network_project.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var data = authenticationService.login(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .data(data)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {

        var data = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .data(data)
                .build();
    }
}


