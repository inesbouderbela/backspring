package tn.ucar.enicar.info.projetspring.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.ucar.enicar.info.projetspring.entities.*;
import tn.ucar.enicar.info.projetspring.repositories.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private userRepo userRepository;

    @Autowired
    private PosteRepository posteRepository;

    private static final String CV_DIRECTORY = "C:/uploaded-cvs/";

    public Candidate applyForPoste(Integer userId, Long posteId, String experience, String coverLetter, MultipartFile cvFile) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));


        Poste poste = posteRepository.findById(posteId)
                .orElseThrow(() -> new RuntimeException("Poste non trouvé"));


        File directory = new File(CV_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }


        String fileName = userId + "_" + posteId + "_" + cvFile.getOriginalFilename();
        Path filePath = Paths.get(CV_DIRECTORY + fileName);
        Files.write(filePath, cvFile.getBytes());


        Candidate candidate = Candidate.builder()
                .user(user)
                .poste(poste)
                .experience(experience)
                .coverLetter(coverLetter)
                .cvPath(filePath.toString())
                .status(StatusCandidat.PENDING)
                .build();

        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public void acceptCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));
        candidate.setStatus(StatusCandidat.ACCEPTED);
        candidateRepository.save(candidate);
    }
}
