package com.example.document_flow.service;

import com.example.document_flow.entity.Project;
import com.example.document_flow.entity.Report;
import com.example.document_flow.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService implements Getable<Report>  {
    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report getOne(Long id) {
        return reportRepository.findById(id).get();
    }

    public Report addOne(Report report){
        return reportRepository.save(report);
    }
}
