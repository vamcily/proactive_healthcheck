package com.emc.procheck.rule.exception;

public class TenantServiceUnavailableException extends Exception {

    private static final long serialVersionUID = 1L;

    public TenantServiceUnavailableException(String message) {
        super(message);
    }
}
