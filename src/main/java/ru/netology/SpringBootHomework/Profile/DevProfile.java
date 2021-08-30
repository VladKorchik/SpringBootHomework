package ru.netology.SpringBootHomework.Profile;

import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;


public class DevProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}