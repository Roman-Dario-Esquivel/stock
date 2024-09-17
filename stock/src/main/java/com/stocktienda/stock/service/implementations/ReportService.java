package com.stocktienda.stock.service.implementations;

import com.stocktienda.stock.ModelsAuxiliary.ProductsData;
import com.stocktienda.stock.models.Report;
import com.stocktienda.stock.repositorys.IReportRepository;
import com.stocktienda.stock.service.interfaces.IManagerService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final IReportRepository reportRepository;
    private final IManagerService managerService;

    public ReportService(IReportRepository reportRepository, IManagerService managerService) {
        this.reportRepository = reportRepository;
        this.managerService = managerService;
    }

    //@Override
    public List<Report> informe() {
        List<ProductsData> data = this.managerService.listAlllManager();
        List<Report> reportNew = new ArrayList<>();

        for (ProductsData product : data) {
            Report upgrade = new Report();
            upgrade.setIdProduct(product.getIdProduct());
            upgrade.setDescription(product.getDescription());
            upgrade.setPrice(product.getPrice());
            upgrade.setStock(product.getStock());
            upgrade.setAvailable(product.getAvailable());
            upgrade.setReserve(product.getReserve());
            upgrade.setSold(product.getSold());
            upgrade.setLow(product.getLow());
            upgrade.setDamaged(product.getDamaged());
            reportNew.add(upgrade);
        }

        reportRepository.deleteAll();
        reportRepository.saveAll(reportNew);
        
        return reportNew;
    }

}
