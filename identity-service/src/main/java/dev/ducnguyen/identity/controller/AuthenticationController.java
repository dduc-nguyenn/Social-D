package dev.ducnguyen.identity.controller;

import com.nimbusds.jose.JOSEException;
import dev.ducnguyen.identity.dto.request.AuthenticationRequest;
import dev.ducnguyen.identity.dto.request.IntrospectRequest;
import dev.ducnguyen.identity.dto.request.LogoutRequest;
import dev.ducnguyen.identity.dto.request.RefreshTokenRequest;
import dev.ducnguyen.identity.dto.response.ApiResponse;
import dev.ducnguyen.identity.dto.response.AuthenticationResponse;
import dev.ducnguyen.identity.dto.response.IntrospectResponse;
import dev.ducnguyen.identity.service.AuthenticationService;
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

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);

        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request)
            throws ParseException, JOSEException {

        return ApiResponse.<AuthenticationResponse>builder()
                .data(authenticationService.refreshToken(request))
                .build();
    }
}


