package com.hotel.checklist.dto;
public record CreateChecklistReq(Long roomId, Long templateId){} 
public record ToggleReq(Boolean checked, String note){}
