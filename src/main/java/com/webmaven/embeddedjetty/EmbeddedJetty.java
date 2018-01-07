package com.webmaven.embeddedjetty;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbeddedJetty {
	private static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String CONFIG_LOCATION = "eu.kielczewski.example.config";
    private static final String MAPPING_URL = "/*";
    private static final String DEFAULT_PROFILE = "dev";

    public static void main(String[] args) throws Exception {
        new EmbeddedJetty().startJetty(getPortFromArgs(args));
    }

    private static int getPortFromArgs(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.valueOf(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }
        return DEFAULT_PORT;
    }

    private void startJetty(int port) throws Exception {
        Server server = new Server(port);
        server.setHandler(getServletContextHandler(getContext()));
        server.start();
        server.join();
    }

    private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet()), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener());
        contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        //context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        return context;
    }

}
	
	
	
	
	
	/*

	private void initEmbeddedJettyServer(String[]... args) {
		try {
			Server embeddedServer = new Server(8680);
			// ServletHandler servletHandler = new ServletHandler();
			// embeddedServer.setHandler(servletHandler);
			// servletHandler.addServletWithMapping(DispatcherServlet.class, "/");
			embeddedServer.start();
			// embeddedServer.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EmbeddedJetty ejs = new EmbeddedJetty();
		ejs.initEmbeddedJettyServer(null);
		ApplicationContext context = new ClassPathXmlApplicationContext("BusinessManagement-beans.xml");
		HelloWorld obj = (HelloWorld) context.getBean("helloBean");
		// obj.setName("Nikunj");
		obj.printHello();

		// get sqlSessionFactory
		// sessionFactory should be cast as DefaultSqlSessionFactory!!!!
		DefaultSqlSessionFactory sessionFactory = (DefaultSqlSessionFactory) context.getBean("sqlSessionFactory");

		//LoginDAO logindao = new LoginDAO(sessionFactory);
		LoginDAO logindao = (LoginDAO) context.getBean("loginDao");
		LoginController controller = (LoginController) context.getBean("loginController");
		Login login = new Login();
		login.setUsername("Nikunj");
		login.setPassword("niks");
		logindao.insert(login);
	
		PersonDAO personDAO = new PersonDAO(sessionFactory);

		// create person bean to insert
		Person person = new Person();
		person.setName("Person 1");

		// ( 1 ) insert person
		personDAO.insert(person);

		// **set name of person
		person.setName("Person 2");
		// ** insert another person
		int id = personDAO.insert(person);

		// ( 2 ) select persons by id
		personDAO.selectById(id);

		// ( 3 ) select all
		List<Person> persons = personDAO.selectAll();

		// **set name of all persons
		for (int i = 0; i < persons.size(); i++) {
			persons.get(i).setName("Person Name " + i);
			// ( 4 ) update person
			personDAO.update(persons.get(i));
		}

		// **check update
		persons = personDAO.selectAll();

	}
*/
