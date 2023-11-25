package org.file_upload.ws.services;

import org.file_upload.ws.entities.Attachement;
import org.file_upload.ws.repositories.AttachementRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AttachementServiceImpl implements AttachementService{

	AttachementRepository attachementRepository;
	@Override
	public Attachement saveAttachement(MultipartFile file) throws Exception {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new Exception("File Name containe invalid path sequence");
			}
			
			Attachement attachement=new Attachement(fileName,file.getContentType(),file.getBytes());
			
			return attachementRepository.save(attachement);
		}catch (Exception e) {
			throw new Exception("Could nnot save file "+fileName);
		}
		
	}
	@Override
	public Attachement getAttachement(String fileId) throws Exception {
		
		return attachementRepository.findById(fileId).orElseThrow(()->new Exception("File not found with ID : "+fileId));
	}
	

}
