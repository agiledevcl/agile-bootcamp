package ${groupId}.core.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ${groupId}.core.form.Person;
import ${groupId}.core.service.PersonService;
import ${groupId}.utils.InformacionPersonal;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

@Controller
public class ClaveUnicaOAuth2Controller {
	@Autowired
	private PersonService personService;
	
	private Logger logger = Logger.getLogger(ClaveUnicaOAuth2Controller.class);	
	private static final Properties appProperties=new Properties();	
	
	private AuthorizationCodeFlow authorization;	
	private static String SECRET;
	private static String ID;	
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static String AUTHORIZATION_SERVER_URL;
	private static String TOKEN_SERVER_URL;
	private static String RESOURCE_SERVER_URL;	
	private String returnToUrl="";
	
	/**
	 * Inicializa los datos necesarios desde 'claveunica.properties'
	 * En caso de existir alguna excepción en la lectura del archivo, los datos son inicializados con valores en duro.
	 */
	@PostConstruct
	public void init(){
		try {
			appProperties.load(getClass().getClassLoader().getResourceAsStream("claveunica.properties"));
			ID=appProperties.getProperty("userid");
			SECRET=appProperties.getProperty("secret");
			AUTHORIZATION_SERVER_URL = appProperties.getProperty("authorization_server_url");
			TOKEN_SERVER_URL = appProperties.getProperty("token_server_url");
			RESOURCE_SERVER_URL=appProperties.getProperty("resource_server_url");
		} catch (IOException e) {
			ID="019bb0fc02173a38a078b3d67ac17978";
			SECRET="e18ba76a7c174db9dd4cb8de64715dae";
			AUTHORIZATION_SERVER_URL = "https://www.claveunica.cl/oauth2/auth";
			TOKEN_SERVER_URL = "https://www.claveunica.cl/oauth2/token";
			RESOURCE_SERVER_URL="https://apis.modernizacion.cl/registrocivil/informacionpersonal/v1/info.php?access_token=";
			logger.error(e.getMessage());
		}
	}
		
	/**
	 * Se inicia un proceso de autorización de la aplicación frente al authorization server.
	 * El usuario es dirigido hacia la página de autorización del resource owner, para brindar los permisos necesarios a la aplicación.
	 * @param request
	 * @param response
	 * @param map
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping("/system/loginOauth2CU")
	private void loginOAuth2CU(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map, HttpSession session) throws IOException{
//		User user=null;
		//(User)session.getAttribute("userSession");
		logger.debug("Iniciando Login Clave Única OAuth");
		GenericUrl tokenServerUrl=new GenericUrl(TOKEN_SERVER_URL);		
		authorization=new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),  HTTP_TRANSPORT, JSON_FACTORY, tokenServerUrl, new BasicAuthentication(ID, SECRET), ID, AUTHORIZATION_SERVER_URL).setScopes(Arrays.asList("basico")).build();
		String realm = request.getScheme() + "://" + request.getServerName() + (request.getServerPort()==443?"":(":" + request.getServerPort())) + request.getContextPath();
		returnToUrl = realm + "/system/cucallback";
		String url = authorization.newAuthorizationUrl().setState("xyz")
		        .setRedirectUri(returnToUrl).build();
		logger.debug(url);
		response.sendRedirect(url);
	}
	
	/**
	 * Respuesta del authorization server con un authorizationCode que puede ser canjeado por un token en el token server.
	 * La aplicación solicita recursos al resource server identificándose con el token.
	 * Los recursos obtenidos sirven para autenticar al usuario y se redirige hacia su sesión. 
	 * @param request
	 * @param response
	 * @param session
	 * @param authorizationCode
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/system/cucallback")
	private String returnedOAuth2CU(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="code",required=false) String authorizationCode) throws IOException{
		try{
			if(null==authorizationCode){
				logger.warn("El usuario no ha brindado permisos a la aplicación.");
	            return "redirect:/";
			}
			logger.info("AuthorizationCode: " + authorizationCode);
			if(null==returnToUrl || returnToUrl.isEmpty()){
				String realm = request.getScheme() + "://" + request.getServerName() + (request.getServerPort()==443?"":(":" + request.getServerPort())) + request.getContextPath();
				returnToUrl = realm + "/system/cucallback";
			}
			TokenResponse tokenResponse=authorization.newTokenRequest(authorizationCode).setRedirectUri(returnToUrl).execute();		
			final Credential credential=new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(tokenResponse.getAccessToken());
			HttpRequestFactory requestFactory =
			          HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			            @Override
			            public void initialize(HttpRequest request) throws IOException {
			              credential.initialize(request);
			              request.setParser(new JsonObjectParser(JSON_FACTORY));
			            }
			          });
			HttpRequest requested = requestFactory.buildGetRequest(new GenericUrl(RESOURCE_SERVER_URL+tokenResponse.getAccessToken()));
			Gson gson=new Gson();
			InformacionPersonal personal_info=gson.fromJson(requested.execute().parseAsString(), InformacionPersonal.class);		
			logger.info("Usaurio Autenticado con CU OAuth: " + requested.execute().parseAsString());
			if(null!=personal_info){
			logger.debug("personal_info NO es NULL para: " + personal_info.run);
				if(personal_info.run.contains("-")){
					personal_info.run=personal_info.run.split("-")[0];
	            }
				
				Person person = personService.getPersonByDni(personal_info.run);
				if(person==null){
					person = new Person();
					person.setDni(personal_info.run);
					person.setCommonName(personal_info.nombres+" "+personal_info.apellidoPaterno+" "+personal_info.apellidoMaterno);
					personService.addPerson(person);
					person = personService.getPersonByDni(personal_info.run);
				}
				
				session.setAttribute("personSession", person);
				
				return "redirect:/system/home";
			}else{
				logger.error("Personal Info es NULL " + requested.execute().parseAsString() );
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}		
		return "redirect:/login";
	}

}
