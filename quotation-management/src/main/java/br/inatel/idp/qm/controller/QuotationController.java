package br.inatel.idp.qm.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.inatel.idp.qm.model.dto.StockQuoteDTO;
import br.inatel.idp.qm.model.entity.Stock;
import br.inatel.idp.qm.model.exception.BusinessException;
import br.inatel.idp.qm.service.QuotationService;

/**
 * Controller principal do Quotation Manager
 * @author vitor.figueiredo
 * @since 14 jun 2022
 */
@RestController
@RequestMapping("/quotation")
public class QuotationController {
	
	
	@Autowired
	private QuotationService myService;
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public StockQuoteDTO createQuote(@RequestBody @Valid StockQuoteDTO sqDTO) {
		try {
			Stock stock = sqDTO.convertToStock();
			stock = myService.save(stock);
			
			StockQuoteDTO dto = new StockQuoteDTO(stock);
			return dto;
			
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() );
		}
	}
	
	
	
	@GetMapping("/{stockCode}")
	public StockQuoteDTO readStockQuotesByStockCode(@PathVariable("stockCode") String stockCode) {
		Optional<Stock> opStock = myService.findOneStockAndQuotesByStockCode(stockCode);

		if (opStock.isEmpty()) {
			String message = String.format("StockCode [%s] inexistente", stockCode);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
		}
		
		StockQuoteDTO dto = new StockQuoteDTO( opStock.get() );
		return dto;

	}
	
	
	
	@GetMapping
	public List<StockQuoteDTO> readAllStockQuotes() {
		List<Stock> stockList = myService.findAllStockAndQuotes();
		
		List<StockQuoteDTO> dtoList = stockList.stream()
			.map( StockQuoteDTO::new )
			.collect( Collectors.toList() )
			;
		
		return dtoList;
	}

}
