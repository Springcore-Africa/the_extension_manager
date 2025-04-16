package com.oracleous.extention_manager.utilities;

import java.time.Year;

public class ApplicationUtilities {
    public static final String FARMER_ALREADY_EXIST = "Farmer With These details Already Exist";
    public static final String SUPER_ADMIN_EXIST = "Super Admin With These details Already Exist";
    public static final String FARMER_EXIST_CODE = "001";
    public static final String ACCOUNT_CREATED_CODE = "002";
    public static final String ACCOUNT_CREATED_MESSAGE = "Account Created Successfully";
    public static final String BUSINESS_REGISTERED_CODE = "003";
    public static final String BUSINESS_REGISTERED_MESSAGE ="Business Registered Successfully";
    public static final String SUPER_ADMIN_MESSAGE = "You are not permitted to perform this action";
    public static final String INVESTOR_ALREADY_EXIST = "Investor With These details Already Exist";
    public static final String INVESTOR_CREATED_CODE = "004";
    public static final String INVESTOR_CREATED_MESSAGE = "Account Created Successfully";
    public static final String AGRIBUSINESS_NOT_FOUND_MESSAGE = "Email or Phone Number Not Found";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found for the provided contact";
    public static final String REQUIRED_REQUEST_MESSAGE = "Either Email or PhoneNumber must be provided";
    public static final String EMAIL_ALREADY_EXIST = "Email Already Exist";
    public static final String INVALID_EMAIL_ADDRESS = "Invalid email address";
    public static final String SUPER_ADMIN_INITIATIVE = "Only SuperAdmin can initiate registration";
    public static final String ADMIN_NOT_FOUND = "Admin not found";
    public static final String ADMIN_ALREADY_CONFIRMED = "Admin already confirmed";
    public static final String EMAIL_REGISTRATION_SENT = "Registration email sent";


    public static String generateRegNumber() {
        /**
         * Registration Number is "PLM-FRM", Current Year plus 6 random numbers
         */
        Year currentYear = Year.now();
        int maxValue = 999999;
        int minValue = 100000;

        int randomNumber = (int)Math.floor(Math.random()*(maxValue - minValue +1)- minValue);

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

        int randomNumber = (int)Math.floor(Math.random()*(maxValue - minValue +1)- minValue);

        String randomString = String.valueOf(randomNumber);
        String year = String.valueOf(currentYear);

        StringBuilder regNumber = new StringBuilder();
        String FARM = "FRM_BUS";

        return FARM + regNumber.append(year).append(randomString);
    }
}

