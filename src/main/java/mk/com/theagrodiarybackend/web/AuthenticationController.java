package mk.com.theagrodiarybackend.web;


import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.dto.AuthenticationDto;
import mk.com.theagrodiarybackend.model.dto.AuthenticationResponse;
import mk.com.theagrodiarybackend.service.jwt.UserDetailsServiceImpl;
import mk.com.theagrodiarybackend.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:3000"})
@RequestMapping(path = "/api/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDto authenticationDto,
                                                            HttpServletResponse response)
            throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationDto.getUsername(), authenticationDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);

    }
}
