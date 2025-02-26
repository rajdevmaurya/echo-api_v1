package com.echo.api.rest.dto;

import java.util.List;

public record AuthResponse(String accessToken,Long id,String username,String name,String email,String mobileNumber,List<String> roles) {
	
	
}