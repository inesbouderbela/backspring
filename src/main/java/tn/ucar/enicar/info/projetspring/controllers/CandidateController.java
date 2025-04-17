package tn.ucar.enicar.info.projetspring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import tn.ucar.enicar.info.projetspring.entities.Candidature;
import tn.ucar.enicar.info.projetspring.sevices.CandidateService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/apply")
    public ResponseEntity<Candidature> applyForPoste(
            @RequestParam Integer userId,
            @RequestParam Long posteId,
            @RequestParam String experience,
            @RequestParam String coverLetter,
            @RequestParam("cv") MultipartFile cvFile) {
        try {
            Candidature candidature = candidateService.applyForPoste(userId, posteId, experience, coverLetter, cvFile);
            return ResponseEntity.ok(candidature);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping
    public List<Candidature> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidature> getCandidateById(@PathVariable Long id) {
        Candidature candidature = candidateService.getCandidateById(id);
        return candidature != null ? ResponseEntity.ok(candidature) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<Void> acceptCandidate(@PathVariable Long id) {
        candidateService.acceptCandidate(id);
        return ResponseEntity.ok().build();
    }
}
