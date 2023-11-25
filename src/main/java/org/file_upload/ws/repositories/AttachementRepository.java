package org.file_upload.ws.repositories;

import org.file_upload.ws.entities.Attachement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachementRepository extends JpaRepository<Attachement, String>{

}
