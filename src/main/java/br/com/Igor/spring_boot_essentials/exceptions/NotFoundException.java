package br.com.Igor.spring_boot_essentials.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }
}
