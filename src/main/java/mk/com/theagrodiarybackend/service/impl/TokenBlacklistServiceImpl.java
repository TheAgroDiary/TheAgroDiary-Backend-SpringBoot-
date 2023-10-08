package mk.com.theagrodiarybackend.service.impl;

import mk.com.theagrodiarybackend.service.TokenBlacklistService;

import java.util.HashSet;
import java.util.Set;

public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    private Set<String> tokenBlacklist = new HashSet<>();

    @Override
    public void addToBlacklist(String token) {
        tokenBlacklist.add(token);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }
}
