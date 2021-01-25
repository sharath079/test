package com.patientapp.request.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.GregorianCalendar;
public class StaticFileUtil
{
    public static final String HTTP_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String HTTP_DATE_GMT_TIMEZONE = "GMT";
    public static final int HTTP_CACHE_SECONDS = 5;
    /**
     * Sets the Date and Cache headers for the HTTP Response
     *
     * @param response HTTP response
     * @param fileToCache file to extract content type
     */
    public void setDateAndCacheHeaders(HttpServletResponse response, File fileToCache)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));
        // Date header
        Calendar time = new GregorianCalendar();
        response.addHeader("Date", dateFormatter.format(time.getTime()));
        // Add cache headers
        time.add(Calendar.SECOND, HTTP_CACHE_SECONDS);
        response.addHeader("Date", dateFormatter.format(time.getTime()));
        response.addHeader("Expires", dateFormatter.format(time.getTime()));
        response.addHeader("Cache-Control", "private, max-age=" + HTTP_CACHE_SECONDS);
        response.addHeader("Last-Modified", dateFormatter.format(new Date(fileToCache.lastModified())));
    }
    public static boolean doesCacheHaveTheLatestFile(HttpServletRequest request, File file)
    {
    	try
    	{    		
            String ifModifiedSince = request.getHeader("If-Modified-Since");
            if (ifModifiedSince != null && !ifModifiedSince.isEmpty())
            {
                SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
                Date ifModifiedSinceDate = dateFormatter.parse(ifModifiedSince);
                // Only compare up to the second because the datetime format we send to the client
                // does not have milliseconds
                long ifModifiedSinceDateSeconds = ifModifiedSinceDate.getTime() / 1000;
                long fileLastModifiedSeconds = file.lastModified() / 1000;
                if (ifModifiedSinceDateSeconds == fileLastModifiedSeconds)
                {
                    //sendNotModified(ctx, cookieList);
                    return true;
                }            
            }
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        return false;    	
    }
    /**
     * When file timestamp is the same as what the browser is sending up, send a
     * "304 Not Modified"
     *
     */
    public void sendNotModified(HttpServletResponse response)
    {
    	response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        //setDateHeader(response);
        SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));
        Calendar time = new GregorianCalendar();
        response.addHeader("Date", dateFormatter.format(time.getTime()));        
    }
	public void handleStaticFileRequest(String filePath, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		JsonObject retValObject = new JsonObject();
		String fileContent = getFileContent(filePath, request, response, retValObject);
		int presentInCache = 0;
		if(retValObject.has("presentInCache") && retValObject.get("presentInCache").isJsonNull()==false)
		{
			presentInCache = retValObject.get("presentInCache").getAsInt();
		}
		if(presentInCache!=1)
		{
			PrintWriter out = response.getWriter();
			out.println(fileContent);
		}
	}
	public String sanitizeUri(String uri)
	{
		uri = uri.replace('/', File.separatorChar);
		uri = uri.replace('\\', File.separatorChar);
		return uri;
	}
	public String getFileContent(String filePath, HttpServletRequest request, HttpServletResponse response, JsonObject retValObject) throws Exception
	{
		//System.out.println("Filepath being handled : " + filePath);
		/*int resourcesIndex = filePath.indexOf("/resources/");
		if(resourcesIndex>0)
		{
			int filePathBeginIndex = resourcesIndex + "/resources".length();
			String resourceFilePath = filePath.substring(filePathBeginIndex);
			System.out.println("Equivalent resource filepath being handled : " + resourceFilePath);
			return getFileContentFromResource(resourceFilePath);
		}*/
		filePath = sanitizeUri(filePath);
		File file = new File(filePath);
		boolean cacheHasLatestFile = StaticFileUtil.doesCacheHaveTheLatestFile(request, file);
		if(cacheHasLatestFile==true)
		{
			sendNotModified(response);
			retValObject.addProperty("presentInCache", 1);
			return null;
		}
		setDateAndCacheHeaders(response, file);
		Path requestFilePath = FileSystems.getDefault().getPath(filePath);
		BufferedReader reader = readBufferedFile(requestFilePath);
		String line = "";
		String sFileContent = "";
		while ((line = reader.readLine()) != null)
		{
			if (isExists(line))
			{
				sFileContent = sFileContent + line + "\n";
			}
		}
		reader.close();		
		return sFileContent;
		//return getFileContent(getRequestFilePath(filePath));
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
}
