package com.isg.hr.asset.Asset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "asset" , description="Its Gives  the information about asset through API's and it is document using swagger"+""
    , contact = @Contact(name = "Yogendra Rane", url = "", email = "yogendrarane@insolutionsglobal.com"),
	license = @License(name = "license", url = "")), servers = @Server(url = "http://localhost:9090/") )
public class AssetApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetApplication.class, args);
	}

}
