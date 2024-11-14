package com.MotherSon.CRM.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MotherSon.CRM.models.CompanyProfile;
import com.MotherSon.CRM.security.services.CompanyProfileService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/companyprofile")
public class CompanyProfileController {
	
	
	@Autowired
	private CompanyProfileService companyprofileService;
	
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<CompanyProfile>> getAllCompanyProfile() {
		List<CompanyProfile> companyprofile = companyprofileService.getAllCompanyProfile();
		return new ResponseEntity<>(companyprofile, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getby/{id}")
	public ResponseEntity<CompanyProfile> getCompanyProfileById(@PathVariable Long id){
		Optional<CompanyProfile> companyprofile = companyprofileService.getCompanyProfileById(id);
		return (ResponseEntity<CompanyProfile>) companyprofile.map(value  -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	private String timestamp;

	public static String uploadDirectory=System.getProperty("user.dir") + "/src/main/CprofileLogo/Logo";
		@PostMapping("/create")
	public CompanyProfile saveCompanyProfile(
	        @ModelAttribute CompanyProfile companyProfile,
	        @RequestParam(value = "footerLogo", required = false) MultipartFile[] footerFiles,
	        @RequestParam(value = "headerLogo", required = false) MultipartFile[] headerFiles,
	        @RequestParam(value = "appLogo", required = false) MultipartFile[] appFiles,
	        @RequestParam(value = "favicon", required = false) MultipartFile[] faviconFiles,
	        @RequestParam(value = "loadingGif", required = false) MultipartFile[] loadingFiles) throws IOException {

	    // Process each image type
	    if (footerFiles != null) {
	        companyProfile.setWebsitefooterlogo(uploadImages(footerFiles));
	    }
	    if (headerFiles != null) {
	        companyProfile.setWebsiteheaderlogo(uploadImages(headerFiles));
	    }
	    if (appFiles != null) {
	        companyProfile.setApplogo(uploadImages(appFiles));
	    }
	    if (faviconFiles != null) {
	        companyProfile.setWebsitefavicon(uploadImages(faviconFiles));
	    }
	    if (loadingFiles != null) {
	        companyProfile.setLoadinggif(uploadImages(loadingFiles));
	    }

	    return this.companyprofileService.addCompanyProfile(companyProfile);
	}
		        
		
			
		        private List<String> uploadImages(MultipartFile[] files) throws IOException {
		            List<String> imageUrls = new ArrayList<>();
			
			 Path uploadPath = Paths.get(uploadDirectory);
		        if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		        }
			
			for(MultipartFile  file : files) {
				
	// Validate file type
	if (!isValidImage(file)) {
	throw new IllegalArgumentException("File must be a JPEG or PNG image.");
	}

	

	// Generate a unique file name

	//String uniqueFilename = generateUniqueFilename(uploadDate, originalFilename, timestamp);
	String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
	//String uniqueFilename = uploadDate + "_" + originalFilename; // e.g., "14-12-2024_image.jpg"
	Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
	Files.write(fileNameAndPath, file.getBytes());



	//String originalFilename = file.getOriginalFilename();
	//Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
	//Files.write(fileNameAndPath, file.getBytes());
	String imageUrl = "/image/" +  uniqueFilename;; 
	imageUrls.add(imageUrl);
			}
			
			
			
//			companyProfile.setWebsitefooterlogo(footerImageUrls);
//	        companyProfile.setWebsiteheaderlogo(headerImageUrls);
//	        companyProfile.setApplogo(appImageUrls);
//	        companyProfile.setWebsitefavicon(faviconImageUrls);
//	        companyProfile.setLoadinggif(loadingGifUrls);
		
//	companyProfile.setWebsitefooterlogo(imageUrls);
//	companyProfile.setWebsiteheaderlogo(imageUrls);
//	companyProfile.setApplogo(imageUrls);
//	companyProfile.setWebsitefavicon(imageUrls);
//	companyProfile.setLoadinggif(imageUrls);
//	
	
	
		        
	

	//return this.companyprofileService.addCompanyProfile(companyProfile);
	return imageUrls;
	}

	//private String generateUniqueFilename(String uploadDate, String originalFilename) {
			// TODO Auto-generated method stub
			//return null;
		//}

	//private boolean isValidImage(List<MultipartFile> file) {
		
	private boolean isValidImage(MultipartFile file) {
	// Get the file content type
	String contentType = file.getContentType();
	return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
	}

		

	private String generateUniqueFilename(String originalFilename) {
	    // Extract the file extension
	    String extension = "";
	    if (originalFilename != null && originalFilename.lastIndexOf('.') > 0) {
	        extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
	    }
	    
	    // Set the current time stamp
	    
	  String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH-mm-ss"));
	  //DateTimeFormatter timestamp = DateTimeFormatter.ofPattern("HHmm");

	    // Generate a UUID
	    String uniqueID = UUID.randomUUID().toString();

	    // Create a unique filename
	    
	    //return timestamp + "_" + uniqueID + extension;
	   //return uploadDate + "_" + uniqueID + extension; // e.g., "14-12-2024_123e4567-e89b-12d3-a456-426614174000.jpg"
	    return timestamp + "_" + uniqueID + extension;
	}
	
//    @DeleteMapping("/deleteByid/{id}")
//   public void deletebyid(@PathVariable Long id)
//   {
//   	companyprofileservice.deletecompanyprobyid(id);
//   }
//   public CompanyProfile updatebyid(@PathVariable Long id,@Valid  @RequestBody CompanyProfile companyprofile)
//   {
//   	         CompanyProfile companyprofileupdate= companyprofileservice.updatebyid(id, companyprofile);
//		  return companyprofileupdate;
//   }


}



