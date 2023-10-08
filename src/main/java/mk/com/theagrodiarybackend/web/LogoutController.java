package mk.com.theagrodiarybackend.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.service.TokenBlacklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LogoutController {

//    private TokenBlacklistService tokenBlacklistService;

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletRequest request) {
//        // Extract the token from the request or wherever you store it
////        String token = extractTokenFromRequest(request);
//        String token = "";
//
//        // Add the token to the blacklist
//        tokenBlacklistService.addToBlacklist(token);
//
//        return ResponseEntity.ok("Logged out successfully");
//    }

}