package ku.cs.RPS.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ku.cs.RPS.DTO.NoticeSearchRequest;
import ku.cs.RPS.entities.Bill;
import ku.cs.RPS.entities.join.DeliveryCustomerNotice;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/handoff")
@SessionAttributes("data")
public class HandoffController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String handoff(HttpSession session, Model model) {
        session.removeAttribute("data");
        model.addAttribute("searchBar", new NoticeSearchRequest());

        return "handoff-view";
    }

    @PostMapping
    public String handoffWithDetail(
            @Valid @ModelAttribute("searchBar") NoticeSearchRequest searchBar,
            BindingResult result, Model model, HttpSession session
    ) {
        session.removeAttribute("data");

        if (result.hasErrors())
            return "handoff-view";

        if (!dbRepository.isExistNoticeById(searchBar.getSearchValue())) {
            result.rejectValue("searchValue", "error.notFound", "ไม่พบเลขใบแจ้งงานคนขับ");
            return "handoff-view";
        }

        String noticeId = searchBar.getSearchValue();

        DeliveryCustomerNotice data = dbRepository.findDeliveryCustomerNoticeByNoticeId(noticeId);

        if (data.getCompleteStatus().equals("COMPLETE")) {
            model.addAttribute("completeData", true);

        } else if (data.getCompleteStatus().equals("INCOMPLETE") && data.getProductCount() == 0) {
            model.addAttribute("data", data);

            model.addAttribute("products", dbRepository.findProductsByNoticeId(noticeId));

        } else {
            result.rejectValue("searchValue", "error.cannotAccess", "ยังไม่สารารถดำเนินการกับรายการดังกล่าวเนื่องจากยังจัดสรรไม่เสร็จสิ้น");
        }

        return "handoff-view";
    }

    @PostMapping("/complete")
    public String finishHandoffHandle(@ModelAttribute("data") DeliveryCustomerNotice data, HttpSession session) {
        dbRepository.updateNoticeCompleteStatusById(data.getNoticeId());

        if (!dbRepository.isExistBillById(data.getNoticeId())) {
            dbRepository.save(new Bill(data));
        }

        return "redirect:/bill-detail/" + data.getDeliveryId();
    }
}
