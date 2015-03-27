package my.swingconnect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alicegeorge
 */


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.Cookie;




public class javapostmethod {
	
	   /************* Php script path ****************/
    String upLoadServerUri = "http://photo-drop.com/uploader.php";
    
    int serverResponseCode = 0;
    
    javapostmethod(){
    	
    }

//HTTP POST request
	public int sendPost(String uploadFilePath) throws Exception {
		
		
		  /**********  File Path *************/
//	    final String uploadFilePath = "/Users/pradil90/Desktop/";
//	    final String uploadFileName = "download.jpeg";
	    
	    String uploadFile=uploadFilePath;
     
//     String uploadFile=uploadFilePath + "" + uploadFileName;
     
     System.out.println(uploadFile);
     
     return (uploadFile(uploadFile));
     

	}
	
	public int uploadFile(String sourceFileUri) {
		
	String fileName = sourceFileUri;
	
	HttpURLConnection conn = null;
	DataOutputStream dos = null; 
	String lineEnd = "\r\n";
 String twoHyphens = "--";
 String boundary = "*****";
 int bytesRead, bytesAvailable, bufferSize;
 byte[] buffer;
 int maxBufferSize = 1 * 1024 * 1024; 
  File sourceFile = new File(sourceFileUri);
  
 if (!sourceFile.isFile()) {

 
 	 System.out.println("Source File not exist :"+fileName);
 
		return 0;
	}else{
		
		try { 
		
		// open a URL connection to the Servlet
			FileInputStream fileInputStream = new FileInputStream(sourceFile);
			URL url = new URL(upLoadServerUri);    //Passing the server complete URL

			// Open a HTTP  connection to  the URL
							conn = (HttpURLConnection) url.openConnection(); 
							conn.setDoInput(true); // Allow Inputs
							conn.setDoOutput(true); // Allow Outputs
							conn.setUseCaches(false); // Don't use a Cached Copy
							conn.setRequestMethod("POST");
							conn.setRequestProperty("Connection", "Keep-Alive");
							conn.setRequestProperty("ENCTYPE", "multipart/form-data");
							conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
							conn.setRequestProperty("file", fileName); 
							
							dos = new DataOutputStream(conn.getOutputStream());
					
dos.writeBytes(twoHyphens + boundary + lineEnd); 



dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\""+ lineEnd);


		dos.writeBytes(lineEnd);

		// create a buffer of  maximum size

		bytesAvailable = fileInputStream.available(); 
		System.out.println(bytesAvailable);	
		System.out.println(maxBufferSize);
		bufferSize = Math.min(bytesAvailable, maxBufferSize);
		buffer = new byte[bufferSize];
		
		// read file and write it into form...
bytesRead = fileInputStream.read(buffer, 0, bufferSize);

while (bytesRead > 0) {

	dos.write(buffer, 0, bufferSize);
	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	bytesRead = fileInputStream.read(buffer, 0, bufferSize);
}

//send multipart form data necesssary after file data...
dos.writeBytes(lineEnd);
dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

//Responses from the server (code and message)
		serverResponseCode = conn.getResponseCode();
		String serverResponseMessage = conn.getResponseMessage();

      System.out.println("HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
      
      if(serverResponseCode == 200){
   
     	 //System.out.println("File Upload Completed.\n\n See uploaded file here : \n\n" + " http://www.photo-drop.com/" + sourceFileUri);
           System.out.println("File Upload Completed.\n\n See uploaded file here : \n\n" + " http://www.photo-drop.com/");

      }
      
      //close the streams //
		fileInputStream.close();
      dos.flush();
      dos.close();
		
		
	}catch (MalformedURLException ex) {
	
		ex.printStackTrace();
		System.out.println("Error");
		
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Error");
		
	}
		
		
		
 return 1;
 
		} 
	}
}

