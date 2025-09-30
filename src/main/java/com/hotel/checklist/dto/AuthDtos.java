package com.hotel.checklist.dto;
public record LoginReq(String email,String password){} 
public record SignupReq(String name,String email,String password,String role){}
