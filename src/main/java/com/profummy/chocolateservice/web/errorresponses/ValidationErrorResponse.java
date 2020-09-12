package com.profummy.chocolateservice.web.errorresponses;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();
}
