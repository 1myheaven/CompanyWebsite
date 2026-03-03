package in.sp.main.service;

import org.springframework.stereotype.Service;
import java.util.List;

import in.sp.main.entity.ConsultationRequest;
import in.sp.main.repository.ConsultationRepository;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    public void saveConsultation(ConsultationRequest consultationRequest) {
        consultationRepository.save(consultationRequest);
    }

    public List<ConsultationRequest> getAllConsultations() {
        return consultationRepository.findAll();
    }

    public long getTotalConsultations() {
        return consultationRepository.count();
    }

    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

	
}