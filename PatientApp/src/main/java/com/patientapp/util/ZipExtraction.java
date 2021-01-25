package com.patientapp.util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FilenameUtils;
public class ZipExtraction
{
	   /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception
    {
        String zipFilePath = "C:\\Users\\chandu2222\\Desktop\\Test.zip";
        String outputDirectory = "";
        try
        {
            outputDirectory = FilenameUtils.removeExtension(zipFilePath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        int isExtracted = extractZipFile(outputDirectory, zipFilePath);
        if (isExtracted != 1)
        {
            System.out.println("File Could not be extracted.");
        }
        System.out.println("File Extracted Successfully.");
    }
    public static int extractZipFile(String destinationDir, String zipFilePath) throws Exception
    {
        try
        {
            File destDir = new File(destinationDir);
            if (!destDir.exists())
            {
                destDir.mkdir();
            }
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipIn.getNextEntry();
            // iterates over entries in the zip file
            while (entry != null)
            {
                String entryName = entry.getName();
                if (entryName.contains("/") || entryName.contains("\\"))
                {
                    if (File.separator.equalsIgnoreCase("\\")) //windows
                    {
                        entryName = entryName.replaceAll("/", Matcher.quoteReplacement("\\"));
                    }
                    else//linux
                    {
                        entryName = entryName.replaceAll(Matcher.quoteReplacement("\\"), "/");
                    }
                }
                String filePath = destinationDir + File.separatorChar + entryName;
                if (entry.getName().endsWith("/") || entry.getName().endsWith("\\"))
                {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                else
                {
                    readDataIntoExtractedFile(zipIn, filePath);
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    public static int readDataIntoExtractedFile(ZipInputStream zipIn, String filePath) throws Exception
    {
        try
        {
            int BUFFER_SIZE = 1024;
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) > -1)
            {
                bos.write(bytesIn, 0, read);
            }
            bos.close();
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
