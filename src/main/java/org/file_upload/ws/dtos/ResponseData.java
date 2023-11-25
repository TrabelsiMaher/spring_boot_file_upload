package org.file_upload.ws.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
	
	private String fileName;
	private String downloadURL;
	private String fileType;
	private Long fileSize;

}
