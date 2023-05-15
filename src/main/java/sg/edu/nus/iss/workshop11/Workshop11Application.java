package sg.edu.nus.iss.workshop11;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop11Application {

	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);

	//default port-number
	public static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {

		logger.info("main method started ...... ");

//initialise the spring app
		SpringApplication app = new SpringApplication(Workshop11Application.class);

		//read args array 

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List opsValues = appArgs.getOptionValues("port");

		String portNumber = null;

			// if port number is not in argument
			if(opsValues == null || opsValues.get(0) == null) {
				
				//read port number from env variables
				portNumber = System.getenv("PORT");

				if(portNumber == null) {
					portNumber = DEFAULT_PORT;
				}

			} else {
				//passing port number from command line interface
				portNumber = (String) opsValues.get(0);

			}

			if (portNumber!=null) {

				app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
			}
		
//launch spring boot app
		app.run(args);
	}

}
