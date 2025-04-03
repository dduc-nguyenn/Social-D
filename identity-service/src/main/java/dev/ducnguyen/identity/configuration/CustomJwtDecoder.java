package dev.ducnguyen.identity.configuration;

import com.nimbusds.jose.JOSEException;
import dev.ducnguyen.identity.dto.request.IntrospectRequest;
import dev.ducnguyen.identity.exception.AppException;
import dev.ducnguyen.identity.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Slf4j
@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signer-key}")
    private String SIGNER_KEY;

    @Autowired
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            var response = authenticationService.introspect(
                    IntrospectRequest.builder()
                            .token(token)
                            .build()
            );

            if (!response.isValid())
                throw new JwtException("Invalid token");

        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }

        if(Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");

            nimbusJwtDecoder = NimbusJwtDecoder
                    .withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
