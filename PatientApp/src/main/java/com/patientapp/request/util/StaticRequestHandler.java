package com.patientapp.request.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.sql.Blob;
import java.sql.Connection;
import com.patientapp.request.util.StaticFileUtil;
import com.patientapp.bean.FileUpload;
import com.patientapp.request.service.ServiceAPIUtil;
import com.patientapp.util.SettingsUtil;
import com.patientapp.request.util.RequestUtil;
import com.patientapp.request.util.ApplicationRequestHandlerServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.request.util.AjaxRequestHandler;
import com.patientapp.util.PrivilegeChecker;
import com.patientapp.util.SessionFactoryBuilder;
import com.patientapp.util.SettingsUtil;
public class StaticRequestHandler
{
	private ServletContext servletContext;
	public StaticRequestHandler()
	{
	}
	public StaticRequestHandler(ServletContext sc)
	{
		servletContext = sc;
	}
	public JsonObject handleStaticRequest(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession) throws IOException
	{
		JsonObject dataObject = new JsonObject();
		try
		{
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Content-Length");
			ApplicationStaticRequestHandler applicationStaticRequestHandler = new ApplicationStaticRequestHandler();
			JsonObject responseObject = applicationStaticRequestHandler.handleStaticRequest(request, response, userSessionInfo, requestInfo, organisationSession);
			if (responseObject.has("isRequestHandled"))
			{
				int isRequestHandled = responseObject.get("isRequestHandled").getAsInt();
				if (isRequestHandled == 1)
				{
					return responseObject;
				}
			}
			String requestURI = requestInfo.get("requestURI").getAsString();
			String projectPath = SettingsUtil.getProjectPath();
			String webContentFilesPath = projectPath + "/src/main/resources/WebContent"; 
			String staticHtmlFormsPath = projectPath + "/src/main/resources/WebContent/html/forms"; 
			String staticHtmlFilesPath = projectPath + "/src/main/resources/WebContent/html"; 
			String staticJSFilesPath = projectPath + "/src/main/resources/WebContent"; 
			//String staticResourcesFilesPath = projectPath + "/src/main/resources/WebContent/resources"; 
			String staticResourcesFilesPath = projectPath + "/src/main/resources/WebContent"; 
			String staticHtmlReportPath = projectPath + "/src/main/resources/WebContent/html/reports";
			String staticJSReportPath = projectPath + "/src/main/resources/WebContent/js/reports";
			String filePath = "";
			boolean isUserLoggedIn = RequestUtil.isUserLoggedIn(request, response, masterSession);
			if(1>2)
			{
			}
						else if (requestURI.equalsIgnoreCase("/contact-us"))
			{
				response.setContentType("text/html");
				filePath = staticHtmlFilesPath + "/contact-us.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				try
				{
					staticFileUtil.handleStaticFileRequest(filePath, request, response);
				}
				catch (Exception e)
				{
					response.sendRedirect("/page-not-found");
				}
			}

			else if (requestURI.startsWith("/entity/"))
			{
				response.setContentType("text/html");
				String[] URLSplit = requestURI.split("/");
				String entityFileName = URLSplit[2];
				filePath = staticHtmlFormsPath + "/" + entityFileName;
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				try
				{
					staticFileUtil.handleStaticFileRequest(filePath, request, response);
				}
				catch (Exception e)
				{
					response.sendRedirect("/page-not-found");
				}
			}
			else if (requestURI.startsWith("/theme/*.html"))
			{
				response.setContentType("text/html");
				filePath = staticResourcesFilesPath + "/" + requestURI;
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				try
				{
					staticFileUtil.handleStaticFileRequest(filePath, request, response);
				}
				catch (Exception e)
				{
					response.sendRedirect("/page-not-found");
				}
			}
			/*
			 * /fonts/*.woff2
			 */
			else if (requestURI.endsWith(".woff2"))
			{
				response.setContentType("font/woff2");
				System.out.println("Font uri : " + requestURI);
				filePath = staticResourcesFilesPath + requestURI;
				setImageFileContentToResponse(filePath, response);			
				return dataObject;		         
			}
			else if (requestURI.endsWith(".woff"))
			{
				response.setContentType("font/woff");
				System.out.println("Font uri : " + requestURI);
				filePath = staticResourcesFilesPath + requestURI;
				setImageFileContentToResponse(filePath, response);			
				return dataObject;		
			}						
			else if (requestURI.startsWith("/layoutFormat"))
			{
				response.setContentType("text/html");
				filePath = projectPath + "/src/main/resources/WebContent/util/V6/layoutFormat.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}				
			else if (requestURI.startsWith("/reports/") && requestURI.endsWith(".js"))
			{
				response.setContentType("text/javascript");
				String[] URLSplit = requestURI.split("/");
				String reportFileName = URLSplit[2];
				filePath = staticJSReportPath +  "/" + reportFileName;				
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}			
			else if (requestURI.startsWith("/reports/"))
			{
				response.setContentType("text/html");
				String[] URLSplit = requestURI.split("/");
				String reportFileName = URLSplit[2];
				filePath = staticHtmlReportPath + "/" + reportFileName + ".html";				
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}			
			else if (requestURI.endsWith(".js"))
			{
				response.setContentType("text/javascript");
				//filePath = projectPath + requestURI;
				filePath = staticResourcesFilesPath + requestURI;
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}
			else if (requestURI.endsWith(".css"))
			{
				response.setContentType("text/css");
				//filePath = projectPath + requestURI;
				filePath = staticResourcesFilesPath + requestURI;
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}
			else if (requestURI.endsWith(".gif") 
					|| requestURI.endsWith(".png")
					|| requestURI.endsWith(".jpg")
				|| requestURI.endsWith(".jpeg"))
			{
				response.setContentType("image/gif");
				if(requestURI.endsWith(".png"))
				{
					response.setContentType("image/png");
				}
				else if(requestURI.endsWith(".jpg"))
				{
					response.setContentType("image/jpeg");
				}
				else if(requestURI.endsWith(".jpeg"))
				{
					response.setContentType("image/jpeg");
				}
				filePath = staticResourcesFilesPath + requestURI;
				setImageFileContentToResponse(filePath, response);			
				return dataObject;								          
			}
			
			
			
			
			else if (requestURI.equals("/login") 
					|| requestURI.equals("/")
					|| requestURI.equals("/home"))
			{
				response.setContentType("text/html");
				filePath = webContentFilesPath + "/html/login/LoginSuperUser.html";
				if(isUserLoggedIn == true)
				{
					filePath = webContentFilesPath + "/html/home.html";
				}
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}
			else if (requestURI.equals("/forgot-password"))
			{
				response.setContentType("text/html");
				filePath = webContentFilesPath + "/html/login/forgot-password.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}
			else if (requestURI.equals("/reset-password"))
			{
				response.setContentType("text/html");
				filePath = webContentFilesPath + "/html/login/reset-password.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}
			else if (requestURI.equals("/emailid-activation-link"))
			{
				response.setContentType("text/html");
				filePath = webContentFilesPath + "/html/login/emailid-activation-link.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}
			else if (requestURI.equals("/page-not-found"))
			{
				response.setContentType("text/html");
				filePath = webContentFilesPath + "/html/pageNotFound.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}
			else if (requestURI.equals("/privilege-required"))
			{
				response.setContentType("text/html");
				filePath = webContentFilesPath + "/html/privilegeWarning.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
			}
			
			
			
			else if (requestURI.equals("/downloadZipFile"))
			{
				fileDownloadRequestHandler(request, response, requestInfo, userSessionInfo, masterSession, organisationSession);
				return dataObject;
			}
			else if (requestURI.equals("/ViewImageFile"))
			{
				handleImageFileRequest(request, response, organisationSession);
				return dataObject;
			}
			else if ("/UploadServlet".equalsIgnoreCase(requestURI))
			{
				return doHandleFileUpload(request, response, organisationSession);
			}
			else if (requestURI.equals("/download")
					  ||requestURI.equals("/viewFile"))
			{
				fileDownloadRequestHandler(request, response, requestInfo, userSessionInfo, masterSession, organisationSession);
				return dataObject;
			}
			else if (requestURI.startsWith("/repository"))
			{
				jarFileDownloadRequestHandler(request, response, requestURI);
				return dataObject;
			}
			else
			{
				ApplicationRequestHandlerServlet applicationRequestHandlerServlet = new ApplicationRequestHandlerServlet();
				dataObject = applicationRequestHandlerServlet.handleAjaxRequest(request, response);				
				int isRequestHandled = 0;
				try
				{
					if (dataObject.has("isRequestHandled"))
					{
						isRequestHandled = dataObject.get("isRequestHandled").getAsInt();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(getStackTrace(e));			
		}
		return dataObject;
		//PrintWriter out = response.getWriter();
	}
	private void setImageFileContentToResponse(String filePath, HttpServletResponse response)throws Exception
	{
		OutputStream os = null;
		try
		{
			int resourcesIndex = filePath.indexOf("/resources/");
			if(resourcesIndex>0)
			{
				int filePathBeginIndex = resourcesIndex + "/resources".length();
				String resourceFilePath = filePath.substring(filePathBeginIndex);							
				filePath = resourceFilePath;
			}							
			ServletContext sc = servletContext;
			InputStream is = getClass().getResourceAsStream(filePath); 				
			os = response.getOutputStream();
	        if (is == null)
	        {
	            response.setContentType("text/plain");
	            os.write("Failed to return file".getBytes());
	        } 
	        else 
	        {
	            byte[] buffer = new byte[1024];
	            int bytesRead;	                
	            while ((bytesRead = is.read(buffer)) != -1)
	            {
	                os.write(buffer, 0, bytesRead);
	            }
	        }	
		}
		catch (Exception e)
		{
			response.setContentType("text/plain");
            os.write("Failed to return file".getBytes());
			e.printStackTrace();
		}	
	}
	protected String getFileContentFromResource(String resourceFilePath) throws Exception
	{
		URL url = getClass().getResource(resourceFilePath);
		System.out.println("Last modified date : " + new Date(url.openConnection().getLastModified()));
		InputStream in = getClass().getResourceAsStream(resourceFilePath); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		String sFileContent = "";
		while ((line = reader.readLine()) != null)
		{
			if (isExists(line))
			{
				sFileContent = sFileContent + line + "\n";
			}
		}
		return sFileContent;
	}
	protected String getFileContent(String filePath) throws Exception
	{
		System.out.println("Filepath being handled : " + filePath);
		/*int resourcesIndex = filePath.indexOf("/resources/");
		if(resourcesIndex>0)
		{
			int filePathBeginIndex = resourcesIndex + "/resources".length();
			String resourceFilePath = filePath.substring(filePathBeginIndex);
			System.out.println("Equivalent resource filepath being handled : " + resourceFilePath);
			return getFileContentFromResource(resourceFilePath);
		}*/
		return getFileContent(getRequestFilePath(filePath));
		/*if(new File(filePath).exists())
		{
			return getFileContent(getRequestFilePath(filePath));
		}
		else
		{
			int resourcesIndex = filePath.indexOf("/resources/");
			if(resourcesIndex>0)
			{
				int filePathBeginIndex = resourcesIndex + "/resources/".length();
				String resourceFilePath = filePath.substring(filePathBeginIndex);
				return getFileContentFromResource(resourceFilePath);
			}
		}
		return null;*/
	}
	protected String getFileContent(Path path) throws Exception
	{
		BufferedReader reader = readBufferedFile(path);
		String line = "";
		String sFileContent = "";
		while ((line = reader.readLine()) != null)
		{
			if (isExists(line))
			{
				sFileContent = sFileContent + line + "\n";
			}
		}
		return sFileContent;
	}
	protected BufferedReader readBufferedFile(Path sFileName) throws IOException
	{
		Charset charset = Charset.forName("UTF-8");
		BufferedReader reader = Files.newBufferedReader(sFileName, charset);
		return reader;
	}
	protected static boolean isExists(String sString)
	{
		return (sString != null && sString.trim().length() > 0);
	}
	protected Path getRequestFilePath(String sTemplateFileName)
	{
		sTemplateFileName = sanitizeUri(sTemplateFileName);
		return FileSystems.getDefault().getPath(sTemplateFileName);
	}
	public static String getStackTrace(Exception e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	public String sanitizeUri(String uri)
	{
		uri = uri.replace('/', File.separatorChar);
		uri = uri.replace('\\', File.separatorChar);
		return uri;
	}
	public void handleImageFileRequest(HttpServletRequest request, HttpServletResponse response, Session masterSession) throws ServletException, IOException
	{
		try
		{
			int BUFFER_SIZE = 1024 * 100;
			String fileIdString = "";
			String fileNameString = "";
			if(request.getParameterMap().containsKey("fileId"))
			{
				fileIdString = request.getParameter("fileId").trim();
			}
			if(request.getParameterMap().containsKey("fileName"))
			{
				fileNameString = request.getParameter("fileName").trim();
			}
			String filePath = "";
			if (fileIdString != null && fileIdString.length() > 0)
			{
				int fileId = Integer.parseInt(fileIdString);
				//filePath = CommonUtil.fileIDAndNamesMap.get(fileId);
				filePath = CommonUtil.getFilePath(fileNameString, fileId, masterSession);
			}
			else if (fileNameString != null && fileNameString.length() > 0)
			{
				//filePath = com.testfile.util.SettingsUtil.getProjectFilesPath() + fileNameString;
				filePath = CommonUtil.getFilePath(fileNameString, -1, masterSession);
			}
			// Get The Absolute Path Of The File To Be Downloaded
			String fileName = "";
			int lastIndexOfFileSeperator = filePath.lastIndexOf(File.separator);
			fileName = filePath.substring(lastIndexOfFileSeperator + 1, filePath.length());
			response.setContentType(CommonUtil.getFileContentTypeFromExtension(fileName));
			setImageFileContentToResponseFromFile(filePath, response);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void setImageFileContentToResponseFromFile(String filePath, HttpServletResponse response)throws Exception
	{
		OutputStream os = null;
		try
		{
			int resourcesIndex = filePath.indexOf("/resources/");
			if(resourcesIndex>0)
			{
				int filePathBeginIndex = resourcesIndex + "/resources".length();
				String resourceFilePath = filePath.substring(filePathBeginIndex);							
				filePath = resourceFilePath;
			}							
			ServletContext sc = servletContext;
			//InputStream is = getClass().getResourceAsStream(filePath); 				
			InputStream is = new FileInputStream(filePath); 				
			os = response.getOutputStream();
	        if (is == null)
	        {
	            response.setContentType("text/plain");
	            os.write("Failed to return file".getBytes());
	        } 
	        else 
	        {
	            byte[] buffer = new byte[1024];
	            int bytesRead;	                
	            while ((bytesRead = is.read(buffer)) != -1)
	            {
	                os.write(buffer, 0, bytesRead);
	            }
	        }	
		}
		catch (Exception e)
		{
			response.setContentType("text/plain");
            os.write("Failed to return file".getBytes());
			e.printStackTrace();
		}	
	}
	public void fileDownloadRequestHandler(HttpServletRequest request, HttpServletResponse response, JsonObject requestInfo, JsonObject userSessionInfo, Session masterSession, Session organisationSession) throws ServletException, IOException
	{
		int BUFFER_SIZE = 1024 * 100;
		String fileIdString = "";
		String fileNameString = "";
		if(request.getParameterMap().containsKey("fileId"))
		{
			fileIdString = request.getParameter("fileId").trim();
		}
		if(request.getParameterMap().containsKey("fileName"))
		{
			fileNameString = request.getParameter("fileName").trim();
		}
		String requestURI = requestInfo.get("requestURI").getAsString();
	    if(requestURI.equals("/viewFile"))
		{
	    	JsonObject responseData = EntityRequestHandler.getEntityAttributeValue(userSessionInfo, requestInfo, masterSession, organisationSession);
	    	if(!responseData.has("success") || responseData.get("success").getAsInt() != 1)
	    	{
				response.setContentType("text/html");
				response.getWriter().println("<h3>You dont have privilege to view this file</h3>");
				return;
	    	}
	    	String attributeName = requestInfo.get("attributeName").getAsString();
			fileNameString = responseData.get(attributeName).getAsString();
		}
		String filePath = "";
		if (fileIdString != null && fileIdString.length() > 0)
		{
			int fileId = Integer.parseInt(fileIdString);
			//filePath = CommonUtil.fileIDAndNamesMap.get(fileId);
			filePath = CommonUtil.getFilePath(fileNameString, fileId, organisationSession);
		}
		else if (fileNameString != null && fileNameString.length() > 0)
		{
			//filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath() + fileNameString;
			filePath = CommonUtil.getFilePath(fileNameString, -1, organisationSession);
		}
		// Get The Absolute Path Of The File To Be Downloaded
		String fileName = "";
		int lastIndexOfFileSeperator = filePath.lastIndexOf(File.separator);
		fileName = filePath.substring(lastIndexOfFileSeperator + 1, filePath.length());
		File file = new File(filePath);
		OutputStream outStream = null;
		FileInputStream inputStream = null;
		if (file.exists())
		{
			// Setting The Content Attributes For The Response Object
			// String mimeType = "application/octet-stream";
			String mimeType = CommonUtil.getFileContentTypeFromExtension(fileName);
			response.setContentType(mimeType);
			// Setting The Headers For The Response Object
			String headerKey = "Content-Disposition";
			//String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
			String headerValue = String.format("inline; filename=\"%s\"", file.getName());
			response.setHeader(headerKey, headerValue);
			try
			{
				// Get The Output Stream Of The Response
				outStream = response.getOutputStream();
				inputStream = new FileInputStream(file);
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				// Write Each Byte Of Data Read From The Input Stream Write Each Byte Of Data
				// Read From The Input Stream Into The Output Stream
				while ((bytesRead = inputStream.read(buffer)) != -1)
				{
					outStream.write(buffer, 0, bytesRead);
				}
			}
			catch (IOException ioExObj)
			{
				System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
			}
			finally
			{
				if (inputStream != null)
				{
					inputStream.close();
				}
				outStream.flush();
				if (outStream != null)
				{
					outStream.close();
				}
			}
		}
		else
		{
			response.setContentType("text/html");
			response.getWriter().println("<h3>File " + fileName + " is not existed .....!</h3>");
		}
	}
	public void jarFileDownloadRequestHandler(HttpServletRequest request, HttpServletResponse response, String requestURI) throws ServletException, IOException
	{
		int BUFFER_SIZE = 1024 * 100;
		String filePath = requestURI;
		String fileName = "";		
		fileName = filePath.replace("repository/", "");;
		String fileRelativePath = "//home/hts-11/Documents/RemoteRepositoryJars";
		String fileAbsolutePath = fileRelativePath + File.separatorChar +fileName;
		File file = new File(fileAbsolutePath);
		OutputStream outStream = null;
		FileInputStream inputStream = null;
		if (file.exists())
		{
			String mimeType = "application/java-archive";
			if(fileName.endsWith(".pom"))
			{
				mimeType = "text/plain";				
			}
			response.setContentType(mimeType);
			// Setting The Headers For The Response Object
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
			response.setHeader(headerKey, headerValue);
			try
			{			
				outStream = response.getOutputStream();
				inputStream = new FileInputStream(file);
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1)
				{
					outStream.write(buffer, 0, bytesRead);
				}
			}
			catch (IOException ioExObj)
			{
				System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
			}
			finally
			{
				if (inputStream != null)
				{
					inputStream.close();
				}
				outStream.flush();
				if (outStream != null)
				{
					outStream.close();
				}
			}
		}
		else
		{
			response.setContentType("text/html");
			response.getWriter().println("<h3>File " + fileName + " is not existed .....!</h3>");
		}
	}
	private int maxFileSize = 100000 * 1024;
	private int maxMemSize = 100000 * 1024;
	public JsonObject doHandleFileUpload(HttpServletRequest request, HttpServletResponse response, Session masterSession) throws ServletException, java.io.IOException
	{
		// Check that we have a file upload request
		JsonObject dataObject = new JsonObject();
		String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
		if (!(new File(filePath).exists()))
		{
			FileUtils.forceMkdir(new File(filePath));
		}
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		File file;
		java.io.PrintWriter out = response.getWriter();
		if (!isMultipart)
		{
			return dataObject;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File(filePath));
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// filePath = "/home/srikanth2/Srik/temp/temp/";
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		try
		{
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			String savedFileName = "";
			while (i.hasNext())
			{
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField())
				{
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					String fileName = fi.getName();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0)
					{
						fileName = CommonUtil.getFileNameByCurrentTime() + "_" + fileName.substring(fileName.lastIndexOf("\\"));
					}
					else
					{
						fileName = CommonUtil.getFileNameByCurrentTime() + "_" + fileName.substring(fileName.lastIndexOf("\\") + 1);
					}
					savedFileName = filePath + fileName;
					file = new File(savedFileName);
					fi.write(file);
					dataObject.addProperty("fileName", fileName);
				}
			}
			//int fileId = CommonUtil.sequenceCount;
			//CommonUtil.fileIDAndNamesMap.put(fileId, savedFileName);
			//CommonUtil.sequenceCount++;
			String fileName = dataObject.get("fileName").getAsString();
			int fileId = CommonUtil.saveFile(fileName, savedFileName, masterSession);
			if(fileId < 0)
			{
				dataObject.addProperty("success", 0);
			}
			else
			{
				dataObject.addProperty("success", 1);
				dataObject.addProperty("fileId", fileId);
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
			dataObject.addProperty("success", 0);
		}
		finally
		{
			response.setContentType("application/json");
			out.write(new Gson().toJson(dataObject));
			return dataObject;
		}
	}
}
