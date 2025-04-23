package com.oracleous.extention_manager.services.farmersServices.FarmerRegistration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TemporaryTokenStore {
    public static final Map<String, String> tokenStore = new ConcurrentHashMap<>();
}
