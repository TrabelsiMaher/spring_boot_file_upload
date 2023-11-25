package org.file_upload.ws.services;

import java.util.Optional;

import org.file_upload.ws.entities.Attachement;
import org.springframework.web.multipart.MultipartFile;

public interface AttachementService {

	public Attachement saveAttachement(MultipartFile file) throws Exception ;

	Attachement getAttachement(String fileId) throws Exception;
	

}
