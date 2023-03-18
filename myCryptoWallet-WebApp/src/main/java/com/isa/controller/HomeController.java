package com.isa.controller;

import com.isa.control.Coin;
import com.isa.model.CoinDto;
import com.isa.service.CoinService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    private final CoinService coinService;

    public HomeController(CoinService coinService) {
        this.coinService = coinService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listCoins(
            Model model,
            @RequestParam("page")Optional<Integer> page,
            @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<CoinDto> coinPage = coinService.findPaginated(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("coins", coinPage);

        int totalPages = coinPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", coinPage.getTotalElements());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        CoinDto coin = new CoinDto();
        model.addAttribute("emptyCoin", coin);

        return "home";
    }
}
