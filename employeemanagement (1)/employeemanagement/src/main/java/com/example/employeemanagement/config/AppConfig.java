package com.example.employeemanagement.config;

public class AppConfig {

    // Single instance
    private static AppConfig instance;

    private String appName;
    private String version;
    private String environment;

    // Private constructor
    private AppConfig() {
        this.appName = "Employee Management System";
        this.version = "1.0.0";
        this.environment = "Development";
        System.out.println("✅ Singleton instance created!");
    }

    // Get instance method (thread-safe)
    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    // Getters
    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getInfo() {
        return appName + " v" + version + " (" + environment + ")";
    }
}