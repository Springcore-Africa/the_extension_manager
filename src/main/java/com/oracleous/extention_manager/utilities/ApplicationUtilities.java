package com.oracleous.extention_manager.utilities;

import java.time.Year;

public class ApplicationUtilities {
    public static final String FARMER_ALREADY_EXIST = "Farmer With These details Already Exist";
    public static final String FARMER_EXIST_CODE = "001";
    public static final String ACCOUNT_CREATED_CODE = "002";
    public static final String ACCOUNT_CREATED_MESSAGE = "Account Created Successfully";
    public static final String BUSINESS_REGISTERED_CODE = "003";
    public static final String BUSINESS_REGISTERED_MESSAGE ="Business Registered Successfully";

    public static final String INVESTOR_ALREADY_EXIST = "Investor With These details Already Exist";
    public static final String INVESTOR_CREATED_CODE = "004";
    public static final String INVESTOR_CREATED_MESSAGE = "Account Created Successfully";
    public static final String AGRIBUSINESS_NOT_FOUND_MESSAGE = "Email or Phone Number Not Found";


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

