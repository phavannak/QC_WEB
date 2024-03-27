package com.vannak.qcweb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vannak.qcweb.dto.NewOrderDTO;
import com.vannak.qcweb.entity.NewOrder;
import com.vannak.qcweb.entity.NewOrderLog;
import com.vannak.qcweb.exception.ResourceNotFoundException;
import com.vannak.qcweb.repository.NewOrderLogRepository;
import com.vannak.qcweb.repository.NewOrderRepository;
import com.vannak.qcweb.service.NewOrderService;
import com.vannak.qcweb.service.util.PageUtil;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class NewOrderServiceImpl implements NewOrderService{
	
	@Autowired
	private NewOrderRepository neworderrepo;
	private NewOrderLogRepository neworderrepolog;
	@Override
	public NewOrder createNewOrder(NewOrder neworder) {
		return neworderrepo.save(neworder);
	}
	
	@Override
	public Page<NewOrder> ListNewOrder(Map<String, String> params) {
		// TODO Auto-generated method stub
		
		int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
		Page<NewOrder> page=neworderrepo.findAll(pageable);
		return page;
	}

	
	@Override
	public NewOrder FindById(Long id) {
		// TODO Auto-generated method stub
		return neworderrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Neworder", id));
	}


	@Override
	public List<NewOrder> Listquery() {
		List<NewOrder> neworder =neworderrepo.ListOrder();
		return neworder;
	}

	//udpate new order
	@Override
	public NewOrder UpdateNewOrder(Long id, NewOrder neworder) {
		String factory;
		
		NewOrder updateneworder=FindById(id);
		
		factory=neworder.getFactory();
		if(factory.equals("BCA")) {
			factory="BCA1";
		}
		
		updateneworder.setFactory(neworder.getFactory());
		updateneworder.setArticle(neworder.getArticle());
		updateneworder.setCpo(neworder.getCpo());
		updateneworder.setCreate_by(neworder.getCreate_by());
		updateneworder.setCreate_date(neworder.getCreate_date());
		updateneworder.setLineno(neworder.getLineno());
		updateneworder.setMo(neworder.getMo());
		updateneworder.setOrders(neworder.getOrders());
		updateneworder.setStatus(neworder.getStatus());
		updateneworder.setUpdate_by(neworder.getUpdate_by());
		updateneworder.setUpdate_date(neworder.getUpdate_date());
		return neworderrepo.save(updateneworder);
		
	}

	@Override
	public void DeleteById(Long id) {
		NewOrder neworder=FindById(id);
		NewOrderLog neworderlog= new NewOrderLog();
		neworderlog.setNew_order_id(neworder.getId());
		neworderlog.setFactory(neworder.getFactory());
		neworderlog.setArticle(neworder.getArticle());
		neworderrepo.deleteById(id);
		neworderrepolog.save(neworderlog);
}
}