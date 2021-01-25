package com.vw.workbench.core;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang.StringUtils;
@SuppressWarnings({"unused", "rawtypes"})
public class VWBuildEnhancer
{
	static HashMap<String, String> mapFiles= new HashMap<String, String>();
	static HashMap<String, String> mapReplace= new HashMap<String, String>();
	static HashMap<String, String> mapConstants= new HashMap<String, String>();
	static HashMap<String, String> mapStatus= new HashMap<String, String>();
	static Properties configProperties = new Properties();
	static String sBuildPath="";
	static String sOriginalPath="";
	static String sNewPath="";
	static String sWorkingPath="";
	static String sWarFile="";
	static String sWarFileWithoutExtension="";
	static String sBuildSite="";
	public static void main(String args[]) throws Exception 
	{		
		VWBuildEnhancer build = new VWBuildEnhancer();
		build.loadConfigProperties();
		sWarFile = getProperty("WAR_FILE");
		sWarFileWithoutExtension = sWarFile.replace(".war", ""); 
		sBuildPath = getProperty("BUILD_PATH");
		sOriginalPath = sBuildPath + getProperty("ORIGINAL_PATH");
		sNewPath = sBuildPath + getProperty("NEW_BUILD_PATH");
		sWorkingPath = sBuildPath + getProperty("WORKING_PATH");
		sBuildSite=	getProperty("BUILD_SITE");
		System.out.println("Build Enhancement has began...");
		build.loadMaps();
		processPreBuild();
		explodeWebArchiveFile();
		processBuild();
		createArchiveFile();
		processPostBuild();
		System.out.println("Build Enhancement has completed...");
	}
	private static void processPreBuild() throws IOException
	{
		Path path = Paths.get(sWorkingPath);
		File workingDirectory = new File(sWorkingPath);
		if (Files.exists(path,LinkOption.NOFOLLOW_LINKS))
		{
			delete(workingDirectory);
			workingDirectory.mkdir();
		}else 
		{
			workingDirectory.mkdir();
		}
		CopyBinaryFile(sOriginalPath + "\\" + sWarFile, sWorkingPath + "\\" + sWarFile);
	}
	private static void explodeWebArchiveFile() throws IOException
	{
		String sWarFilePath = sWorkingPath + "\\" + sWarFile;
		unzipJar(sWorkingPath, sWarFilePath,sWarFile);
	}
	private static void processPostBuild() throws IOException
	{
		String sBuildSitePath = sNewPath + File.separator + sBuildSite; 
		Path buildSiteFolderPath = Paths.get(sBuildSitePath);
		File buildSiteFolder = new File(sBuildSitePath);
		if (Files.exists(buildSiteFolderPath,LinkOption.NOFOLLOW_LINKS))
		{
			delete(buildSiteFolder);
			buildSiteFolder.mkdir();
		}else 
		{
			buildSiteFolder.mkdir();
		}
		String ssPathTemp= sNewPath + File.separator + sWarFile;
		File file = new File(ssPathTemp);
		String ssNewPathTemp= sBuildSitePath +File.separator + sWarFile ;
		File fileNew = new File(ssNewPathTemp);
		file.renameTo(fileNew);
	}
	private static void createArchiveFile() throws Exception
	{
		//String foldertozip = sWorkingPath + "\\" + "MessageGenie" + "\\";
		String foldertozip = sWorkingPath + "\\" + sWarFileWithoutExtension + "\\";
		String sNewWarFilePath = sNewPath + "\\" + sWarFile;
		FileOutputStream fos = new FileOutputStream(sNewWarFilePath);
	    ZipOutputStream zos = new ZipOutputStream(fos);
	    addDirToZipArchive(zos, new File(foldertozip), null);
	    zos.flush();
	    fos.flush();
	    zos.close();
	    fos.close();
	}
	private static void processBuild() throws IOException
	{
		for (Object o: mapFiles.entrySet()) 
		{
			Map.Entry entry = (Map.Entry) o;
			String sFileName = (String) entry.getValue();
			sFileName = sWorkingPath + "\\" + sFileName;
			String sFileContent = readTextFile(sFileName);
			for (Object p: mapReplace.entrySet()) 
			{
				Map.Entry pentry = (Map.Entry) p;
				String[] allReplaces = StringUtils.split((String) pentry.getValue(),",");
				for (int i=0;i<allReplaces.length;i++) 
				{
					String sTemp = allReplaces[i];
					sTemp=StringUtils.remove(sTemp, "(");
					sTemp=StringUtils.remove(sTemp, ")");
					String[] findReplaceStr = StringUtils.split(sTemp, "^");
					String sFind = findReplaceStr[0];
					String sFindConstant = (String) mapConstants.get(sFind);
					if (sFindConstant!=null && sFindConstant.length() >0) 
					{
						sFind=sFindConstant;
					}
					String sReplace = findReplaceStr[1];
					String sReplaceConstant = (String) mapConstants.get(sReplace);
					if (sReplaceConstant!=null && sReplaceConstant.length() >0) 
					{
						sReplace=sReplaceConstant;
					}	
					sFileContent = StringUtils.replace(sFileContent,sFind,sReplace);
				}	
			}		
		    if (sFileName.contains("hibernate.cfg")) 
		    {
		    	String sTemp="";
		    }
			writeToTextFile(sFileName, sFileContent);
		}
	}
	public static String readTextFile(String fileName) throws IOException 
	{
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }
    public static List<String> readTextFileByLines(String fileName) throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return lines;
    }
    public static void writeToTextFile(String fileName, String content) throws IOException 
    {
    	Files.write(Paths.get(fileName), content.getBytes());
    }
	public static String getProperty(String sKey)
	{
		String sValue = configProperties.getProperty(sKey);
		return sValue;
	}
	private void loadMaps()
	{
		Enumeration<?> e = configProperties.propertyNames();
		while (e.hasMoreElements()) 
		{
		      String key = (String) e.nextElement();
		      //System.out.println(key + " -- " + configProperties.getProperty(key));
		      String sValue= configProperties.getProperty(key);
		      if (StringUtils.contains(key, "FILE_NAME_"))
		      {
		    	  mapFiles.put(key, sValue);  
		      }
		      if (StringUtils.contains(key, "REPLACE_"))
		      {
		    	  mapReplace.put(key, sValue);  
		      }
		      if (StringUtils.contains(key, "CONSTANT_"))
		      {
		    	  mapConstants.put(key, sValue);  
		      }
		}
	}
	public void loadConfigProperties()
	{
    	InputStream input = null;
    	try {
    		String filename = "build.properties";
    		input = this.getClass().getResourceAsStream(filename);
    		if(input==null) 
    		{
    	        System.out.println("Unable to find " + filename +" in the classpath");
    		    return;
    		}
    		configProperties.load(input);
    	} catch (IOException ex)
    	{
    		ex.printStackTrace();
        } finally
        {
	        if(input!=null)
	        {
		        try 
		        {
						input.close();
				} catch (IOException e)
				{
						e.printStackTrace();
				}
	        }
        }
    }
	public static void CopyBinaryFile (String strSourceFile,String strDestinationFile ) 
	{      
               try
                {
                        //create FileInputStream object for source file
                        FileInputStream fin = new FileInputStream(strSourceFile);
                        //create FileOutputStream object for destination file
                        FileOutputStream fout = new FileOutputStream(strDestinationFile);
                        byte[] b = new byte[1024];
                        int noOfBytes = 0;
                        while( (noOfBytes = fin.read(b)) != -1 )
                        {
                                fout.write(b, 0, noOfBytes);
                        }
                        fin.close();
                        fout.close();                  
                }
                catch(FileNotFoundException fnf)
                {
                        System.out.println("Specified file not found :" + fnf);
                }
                catch(IOException ioe)
                {
                        System.out.println("Error while copying file :" + ioe);
                }
        }
	public static void unzipJar(String destinationDir, String jarPath,String resourceName) throws IOException
	{
		File file = new File(jarPath);
		@SuppressWarnings("resource")
		JarFile jar = new JarFile(file);
		String jarFolderName = resourceName.replace(".war", "");
		destinationDir = destinationDir + File.separator + jarFolderName;
		for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();)
		{
			JarEntry entry = (JarEntry) enums.nextElement();
			String fileName = destinationDir + File.separator + entry.getName();
			File f = new File(fileName);
			if (fileName.endsWith("/")) 
			{
				f.mkdirs();
			}
		}
		for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();)
		{
			JarEntry entry = (JarEntry) enums.nextElement();
			String fileName = destinationDir + File.separator + entry.getName();
			File f = new File(fileName);
			if (!fileName.endsWith("/")) 
			{
				InputStream is = jar.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(f);
				while (is.available() > 0) 
				{
					fos.write(is.read());
				}
				fos.close();
				is.close();
			}
		}
		System.out.println("Unwar completed !!!");
	}
	public static void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws Exception
	{
	    if (fileToZip == null || !fileToZip.exists()) 
	    {
	        return;
	    }
	    String zipEntryName = fileToZip.getName();
	    if (parrentDirectoryName!=null && !parrentDirectoryName.isEmpty()) 
	    {
	        zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
	    }
	    if (fileToZip.isDirectory()) 
	    {
	        System.out.println("+" + zipEntryName);
	        for (File file : fileToZip.listFiles()) 
	        {
	            addDirToZipArchive(zos, file, zipEntryName);
	        }
	    }
	    else
	    {
	        System.out.println("   " + zipEntryName);
	        byte[] buffer = new byte[1024];
	        FileInputStream fis = new FileInputStream(fileToZip);
	        zipEntryName = zipEntryName.replace(sWarFileWithoutExtension+ "/", "");
	        zos.putNextEntry(new ZipEntry(zipEntryName));
	        int length;
	        while ((length = fis.read(buffer)) > 0) 
		    {
		            zos.write(buffer, 0, length);
		    }
	        zos.closeEntry();
	        fis.close();
	    }
	}
	private static void delete(File file) throws IOException 
	{
		for (File childFile : file.listFiles())
		{
 			if (childFile.isDirectory())
 			{
				delete(childFile);
			} else 
			{
				if (!childFile.delete()) 
				{
					throw new IOException();
				}
			}
		}
 		if (!file.delete()) 
		{
			throw new IOException();
		}
	}
}
