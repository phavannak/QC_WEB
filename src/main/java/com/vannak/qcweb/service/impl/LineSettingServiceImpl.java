package com.vannak.qcweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vannak.qcweb.entity.LineSetting;
import com.vannak.qcweb.repository.LineSettingRepository;
import com.vannak.qcweb.service.LinesSettingService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class LineSettingServiceImpl implements LinesSettingService {
	@Autowired
	LineSettingRepository linesettingRepo;
	public List<LineSetting> getFactory() {
		// TODO Auto-generated method stub
		return linesettingRepo.getLine();
	}
}
