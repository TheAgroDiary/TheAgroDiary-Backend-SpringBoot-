package mk.com.theagrodiarybackend.service;

public interface TokenBlacklistService {

    void addToBlacklist(String token);
    boolean isTokenBlacklisted(String token);
}
