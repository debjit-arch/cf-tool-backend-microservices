package com.example.gap_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gap_service.model.Gaps;
import com.example.gap_service.repository.GapRepository;

@Service
public class GapService {

    @Autowired
    private GapRepository repo;

    public GapService(GapRepository repo) {
        this.repo = repo;
    }

    public Gaps createOrUpdateGap(Gaps request) {
        Optional<Gaps> existing = repo.findByClauseAndQuestion(
                request.getClause(), request.getQuestion()
        );

        if (existing.isPresent()) {
            Gaps gap = existing.get();
            gap.setDocumentNotes(
            	    request.getDocumentNotes() != null ? request.getDocumentNotes() : gap.getDocumentNotes()
            	);
            gap.setPracticeEvidence(
                    request.getPracticeEvidence() != null ? request.getPracticeEvidence() : gap.getPracticeEvidence()
            );
            gap.setPracticeNotes(
                    request.getPracticeNotes() != null ? request.getPracticeNotes() : gap.getPracticeNotes()
            );
            gap.setDocumentURL(
                    request.getDocumentURL() != null ? request.getDocumentURL() : gap.getDocumentURL()
            );
            gap.setDocScore(
                    request.getDocScore() != null ? request.getDocScore() : gap.getDocScore()
            );
            gap.setPracticeScore(
                    request.getPracticeScore() != null ? request.getPracticeScore() : gap.getPracticeScore()
            );
            gap.setTotalScore(
                    request.getTotalScore() != null ? request.getTotalScore() : gap.getTotalScore()
            );
            gap.setDocRemarks(
                    request.getDocRemarks() != null ? request.getDocRemarks() : gap.getDocRemarks()
            );
            gap.setPracticeRemarks(
                    request.getPracticeRemarks() != null ? request.getPracticeRemarks() : gap.getPracticeRemarks()
            );
            gap.setCreatedBy(
                    request.getCreatedBy() != null ? request.getCreatedBy() : gap.getCreatedBy()
            );
            gap.setOrganization(
            	    request.getOrganization() != null ? request.getOrganization() : gap.getOrganization()
            	);


            return repo.save(gap);
        }

        return repo.save(request);
    }
}
