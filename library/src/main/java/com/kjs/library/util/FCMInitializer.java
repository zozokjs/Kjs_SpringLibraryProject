package com.kjs.library.util;

import java.io.IOException;

import org.hibernate.hql.internal.ast.tree.InitializeableNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class FCMInitializer {
	/**
	@Value("${file.path.google_application_credentials}")
	private String firebaseCredentialKeyPath;
	
	private static final Logger logger = LoggerFactory.getLogger(FCMInitializer.class);
	
	public void initializer() throws IOException{
		
		FirebaseOptions options = FirebaseOptions.builder()
				//다운 받은 비밀키를 가져와서 증명함
				.setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseCredentialKeyPath).getInputStream()))
				.build();
			
			if(FirebaseApp.getApps().isEmpty()) {
				
				FirebaseApp.initializeApp();
				
			}
	}
	*/
	//public void initial
	
	
}