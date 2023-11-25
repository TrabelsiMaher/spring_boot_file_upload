package org.file_upload.ws.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Attachement {

	public Attachement(String fileName, String contentType, byte[] bytes) {
		this.fileData=bytes;
		this.fileName=fileName;
		this.fileType=contentType;
	}
	@Id
	@GeneratedValue(strategy =GenerationType.UUID)
	private String id;
	private String fileName;
	private String fileType;
	@Lob
	@Column(name="data", columnDefinition = "LONGBLOB")
	private byte[] fileData;
}
