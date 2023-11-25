package org.file_upload.ws.web;

import java.util.Optional;

import org.file_upload.ws.dtos.ResponseData;
import org.file_upload.ws.entities.Attachement;
import org.file_upload.ws.services.AttachementService;
import org.file_upload.ws.services.AttachementServiceImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest.Headers;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class ApiController {
	
	
	AttachementServiceImpl attachementServiceImpl;
	
	@PostMapping("/upload/file")
	public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		
		Attachement attachement=attachementServiceImpl.saveAttachement(file);
		String downloadURL=ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/api/download/file/")
				.path(attachement.getId())
				.toUriString();
		return new ResponseData(attachement.getFileName(), downloadURL, attachement.getFileType(), file.getSize());
	}
	
	@GetMapping("/download/file/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception{
		
		 Attachement  attachement=attachementServiceImpl.getAttachement(fileId);
		
			
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(attachement.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; File Name=\""+attachement.getFileName()+"\"")
				.body(new ByteArrayResource(attachement.getFileData()));
		
		
	}
}
