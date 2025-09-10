package com.jora.billing.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jora.billing.dao.CategoryDao;
import com.jora.billing.dao.CommonDao;
import com.jora.billing.service.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

	private final CategoryDao categoryDao;

	private final CommonDao commonDao;

	@Override
	public List<Map<String, Object>> loadCategory() throws Exception {

		return categoryDao.loadCategory();
	}

	@Override
	public List<Map<String, Object>> loadFlagDetails(String type) throws Exception {

		return commonDao.loadFlagDetails(type);
	}

	@Override
	public Map<String, Object> loadCategoryHsnDetails(int catNo) throws Exception {

		return commonDao.loadCategoryHsnDetails(catNo);
	}

}
