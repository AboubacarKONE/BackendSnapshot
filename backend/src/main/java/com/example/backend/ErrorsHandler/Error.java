package com.example.backend.ErrorsHandler;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.Exception.ErrorCodes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Error {
	 private Integer httpCode;

	  private ErrorCodes code;

	  private String message;

	  private List<String> errors = new ArrayList<>();

}
