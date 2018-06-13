



public class MailInitializer extends AbstractAnnotationConfigDispactherServletInitializer{

	protected Class<?>[]getRootConfigClasses(){
		return new Class[]{
				ApplicationConfiguration.class
		};
	}
	
	protected String[] getServletMappings(){
		return new String[]{
				*/rest/**
		}
	}
}
