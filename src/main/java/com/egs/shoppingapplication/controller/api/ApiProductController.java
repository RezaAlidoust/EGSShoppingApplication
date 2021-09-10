package com.egs.shoppingapplication.controller.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "ApiProductController")
@RequestMapping(path = {"/admin/categories"}, produces = APPLICATION_JSON_VALUE)
public class UserProductController {
}
