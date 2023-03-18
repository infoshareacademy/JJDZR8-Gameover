package com.isa.service;

import com.isa.model.CoinDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class CoinService {
    final private List<CoinDto> coinDtoList = CoinUtils.buildCoins();

    public Page<CoinDto> findPaginated(Pageable pageable){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CoinDto> list;

        if (coinDtoList.size() < startItem){
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, coinDtoList.size());
            list = coinDtoList.subList(startItem, toIndex);
        }
        Page<CoinDto> coinDtoPage = new PageImpl<CoinDto>(list, PageRequest.of(currentPage, pageSize), coinDtoList.size());
        return coinDtoPage;
    }


}
