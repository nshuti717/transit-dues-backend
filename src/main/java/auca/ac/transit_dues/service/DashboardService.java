package auca.ac.transit_dues.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.transit_dues.repository.RiderRepository;
import auca.ac.transit_dues.repository.TreasurerRepository;
import auca.ac.transit_dues.repository.StageRepository;
import auca.ac.transit_dues.repository.DuesPaymentRepository;
import auca.ac.transit_dues.repository.ReceiptRepository;
import auca.ac.transit_dues.domain.PaymentStatus;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private TreasurerRepository treasurerRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private DuesPaymentRepository duesPaymentRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary =new LinkedHashMap<>();
        summary.put("riders", riderRepository.count());
        summary.put("treasurers", treasurerRepository.count());
        summary.put("freeStages", (long) stageRepository.findFreeStages().size());
        summary.put("paymentsToday", duesPaymentRepository.countByPaymentDate(LocalDate.now()));
        summary.put("declared", duesPaymentRepository.countByStatus(PaymentStatus.DECLARED));
        summary.put("receipts", receiptRepository.count());
        return summary;
    }

}
