package com.patientapp.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.patientapp.util.CommonUtil;
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static int BUFFER_SIZE = 1024 * 100;    
    /***** This Method Is Called By The Servlet Container To Process A 'GET' Request *****/
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        handleRequest(request, response);
    }
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String fileIdString = request.getParameter("fileId").trim();
    	int fileId = Integer.parseInt(fileIdString);
    	String filePath = "";//CommonUtil.fileIDAndNamesMap.get(fileId);
    	//Get The Absolute Path Of The File To Be Downloaded     	
    	String fileName = "";
    	int lastIndexOfFileSeperator = filePath.lastIndexOf(File.separator);
    	fileName = filePath.substring(lastIndexOfFileSeperator + 1, filePath.length());
        File file = new File(filePath);
        OutputStream outStream = null;
        FileInputStream inputStream = null;
        if (file.exists()) 
        {
            // Setting The Content Attributes For The Response Object 
            //String mimeType = "application/octet-stream";
        	String mimeType = "application/pdf";
            response.setContentType(mimeType);
            //Setting The Headers For The Response Object 
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
            response.setHeader(headerKey, headerValue);
            try {
                //Get The Output Stream Of The Response 
                outStream = response.getOutputStream();
                inputStream = new FileInputStream(file);
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                // Write Each Byte Of Data Read From The Input Stream Write Each Byte Of Data  Read From The Input Stream Into The Output Stream
                while ((bytesRead = inputStream.read(buffer)) != -1)
                {
                    outStream.write(buffer, 0, bytesRead);
                }               
            } 
            catch(IOException ioExObj)
            {
                System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
            }
            finally
            {             
                if (inputStream != null) {
                    inputStream.close();
                }
                outStream.flush();
                if (outStream != null) {
                    outStream.close();
                }
            }
        }
        else
        { 
            response.setContentType("text/html");
            response.getWriter().println("<h3>File "+ fileName +" is not existed .....!</h3>");
        }
    }
}
