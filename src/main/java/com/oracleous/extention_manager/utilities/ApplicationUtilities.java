package com.oracleous.extention_manager.utilities;

import com.oracleous.extention_manager.data.model.UserPrincipal;
import com.oracleous.extention_manager.data.model.Users;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.SecureRandom;
import java.time.Year;

public class ApplicationUtilities {
    private static final SecureRandom random = new SecureRandom();

    public static final String FARMER_ALREADY_EXIST = "Farmer With These details Already Exist";
    public static final String FARMER_NOT_FOUND = "Farmer not found";
    public static final String SUPER_ADMIN_EXIST = "Super Admin With These details Already Exist";
    public static final String FARMER_EXIST_CODE = "001";
    public static final String ACCOUNT_CREATED_CODE = "002";
    public static final String ACCOUNT_CREATED_MESSAGE = "Account Created Successfully";
    public static final String BUSINESS_REGISTERED_CODE = "003";
    public static final String BUSINESS_REGISTERED_MESSAGE = "Business Registered Successfully";
    public static final String NO_AUTH_USER_FOUND = "No authenticated user found";
    public static final String BUSINESS_WITH_CAC_NUMBER_ALREADY_EXIST = "Business with CAC number already exists.";
    public static final String SUPER_ADMIN_MESSAGE = "You are not permitted to perform this action";
    public static final String INVESTOR_ALREADY_EXIST = "Investor With These details Already Exist";
    public static final String INVESTOR_CREATED_CODE = "004";
    public static final String INVESTOR_CREATED_MESSAGE = "Account Created Successfully";
    public static final String AGRIBUSINESS_NOT_FOUND_MESSAGE = "Email or Phone Number Not Found";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found for the provided details";
    public static final String REQUIRED_REQUEST_MESSAGE = "Either Email or PhoneNumber must be provided";
    public static final String EMAIL_ALREADY_EXIST = "Email Already Exist";
    public static final String INVALID_EMAIL_ADDRESS = "Invalid email address";
    public static final String AGRI_BUSINESS_NOT_FOUND = "AgriBusiness not found for farmer";
    public static final String SUPER_ADMIN_INITIATIVE = "Only SuperAdmin can initiate registration";
    public static final String SUPER_ADMIN_NOT_FOUND = "SuperAdmin not found";
    public static final String ADMIN_NOT_FOUND = "Admin not found";
    public static final String ADMIN_ALREADY_CONFIRMED = "Admin already confirmed";
    public static final String EMAIL_REGISTRATION_SENT = "Registration email sent";
    public static final String ACCOUNT_TOKEN_SENT = "Account token sent";
    public static final String TOKEN_DOES_NOT_MATCH = "Token does not match email";
    public static final String INVALID_TOKEN = "Invalid or expired token";
    public static final String INVALID_TOKEN_CODE = "400";
    public static final String TOKEN_EXPIRED_CODE = "400";
    public static final String TOKEN_SENT_CODE = "201";
    public static final String USER_NOT_FOUND = "User  not found";
    public static final String REGISTRATION_SUCCESSFUL = "Registration successful";
    public static final String USER_ALREADY_EXIST = "User already exists";
    public static final String INVESTOR_NOT_FOUND = "Investor now found";
    public static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null or empty";
    public static final String NO_PENDING_REGISTRATION = "No pending registration found: ";
    public static final String DURATION_HAS_EXPIRE = "Specify duration has expired or invalid token";
    public static final String FARMER_EXIST = "Farmer already exists with email: {}, nationalId: {}, or phoneNumber: {}";
    public static final String INVALID_OR_EXPIRE_TOKEN = "Invalid or expired token.";
    public static final String TOKEN_HAS_EXPIRED_PLEASE_RE_REGISTER = "Token has expired. Please register again.";
    public static final String EMAIL_OR_PHONE_NUMBER_MUST_BE_PROVIDED = "Either Email or PhoneNumber must be provided";
    public static final String EMAIL_OR_PHONE_NUMBER_NOT_FOUND = "Email or PhoneNumber is not found";
    public static final String NO_AUTHENTICATION_USER_FOUND = "No authenticated user found";


    public static String generateRegNumber() {
        /**
         * Registration Number is "PLM-FRM", Current Year plus 6 random numbers
         */
        Year currentYear = Year.now();
        int maxValue = 999999;
        int minValue = 100000;

        int randomNumber = (int) Math.floor(Math.random() * (maxValue - minValue + 1) - minValue);

        String randomString = String.valueOf(randomNumber);
        String year = String.valueOf(currentYear);

        StringBuilder regNumber = new StringBuilder();
        String FARM = "PLM-FRM";

        return FARM + regNumber.append(year).append(randomString);
    }

    public static String generateBusRegNumber() {
        /**
         * Registration Number is "FRM_BUS", Current Year plus 6 random numbers
         */
        Year currentYear = Year.now();
        int maxValue = 999999;
        int minValue = 100000;

        int randomNumber = (int) Math.floor(Math.random() * (maxValue - minValue + 1) - minValue);

        String randomString = String.valueOf(randomNumber);
        String year = String.valueOf(currentYear);

        StringBuilder regNumber = new StringBuilder();
        String FARM = "FRM_BUS";

        return FARM + regNumber.append(year).append(randomString);
    }

    public static String registrationToken() {
        int token = 100000 + random.nextInt(900000);
        return String.valueOf(token);
    };

    public static Users getCurrentUser() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                throw new IllegalStateException("No authenticated user found");
            }
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Users users = userPrincipal.users();
            if(users == null){
                throw new IllegalArgumentException(USER_NOT_FOUND);
            }else {
            return getCurrentUser();
        }
    }


}
