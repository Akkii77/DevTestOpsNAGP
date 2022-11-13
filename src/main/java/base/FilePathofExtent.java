package base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;

public class FilePathofExtent {
	
	public String  Filepath() throws IOException {
		
 //    String FilepathAsStringmain ="";
     String FilepathAsString = "";
		Path dir = Paths.get(".//Reports//ExtentReports");  // specify your directory

		Optional<Path> lastFilePath = Files.list(dir)    // here we get the stream with full directory listing
//		    .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
		    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));  // finally get the last file using simple comparator by lastModified field

		if ( lastFilePath.isPresent() ) // your folder may be empty
		{
			 FilepathAsString = lastFilePath.toString().replace("[", "").replace("]", "").replace("Optional", "");
				
		   System.out.println("File Path 1: "+FilepathAsString);
//		   Path dir1 = Paths.get(FilepathAsString);  // specify your directory
//
//			Optional<Path> lastFilePath1 = Files.list(dir1)    // here we get the stream with full directory listing
//			    .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
//			    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));  // finally get the last file using simple comparator by lastModified field
//
//			if ( lastFilePath1.isPresent() ) // your folder may be empty
//			{
//			  FilepathAsStringmain = lastFilePath1.toString().replace("[", "").replace("]", "").replace("Optional", "");
//			   System.out.println("File Path 2: "+FilepathAsStringmain);
//			   
//			}     
		   
		} 
		return FilepathAsString;
	}

}
