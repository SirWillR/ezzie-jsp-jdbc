package launch;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class MainLaunch {
	
	public static void main(String[] args) throws LifecycleException, InterruptedException, ServletException {

		String docBase = "src/main/webapp/";

		Tomcat tomcat = new Tomcat();
		String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(8080);

		tomcat.addWebapp("/", new File(docBase).getAbsolutePath());
		System.out.println("configuring app with basedir: " + new File("./" + docBase).getAbsolutePath());

		tomcat.start();
		tomcat.getServer().await();
	}

}
